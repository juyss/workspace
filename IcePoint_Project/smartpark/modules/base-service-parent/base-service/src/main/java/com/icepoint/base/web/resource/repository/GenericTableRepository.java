package com.icepoint.base.web.resource.repository;

import com.github.tangyi.common.core.constant.MqConstant;
import com.github.tangyi.common.core.exceptions.ServiceException;
import com.github.tangyi.common.core.model.req.ParkNewsReq;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.GenericProperty;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.entity.Approval;
import com.icepoint.base.api.entity.Head;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.entity.TabSeq;
import com.icepoint.base.api.excel.DisposalMethodExcel;
import com.icepoint.base.api.excel.UnitFunctionExcel;
import com.icepoint.base.api.util.StreamUtils;
import com.icepoint.base.util.ServletUtils;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.metadata.ResourceType;
import com.icepoint.base.web.resource.component.query.DirectionComparator;
import com.icepoint.base.web.resource.component.query.GenericTableMatch;
import com.icepoint.base.web.resource.component.query.Match;
import com.icepoint.base.web.resource.component.query.QueryParameter;
import com.icepoint.base.web.resource.mapper.TabSeqMapper;
import com.icepoint.base.web.resource.service.simple.ApprovalService;
import com.icepoint.base.web.resource.service.simple.HeadService;
import com.icepoint.base.web.resource.util.ExcelUtil;
import com.icepoint.base.web.resource.util.GenericUtils;
import com.icepoint.base.web.resource.util.ResourceUtils;
import com.icepoint.base.web.resource.util.ZipUtil;
import com.icepoint.base.web.sys.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 基于通用表的${@link GenericRepository}
 *
 * @author Jiawei Zhao
 * @see ResourceType#GENERIC_TABLE
 */
@RequiredArgsConstructor
@Component
public class GenericTableRepository extends GenericRepositoryTemplate {

    private final HeadService headService;
    private final TabSeqMapper tabSeqMapper;
    private final FileProperties fileProperties;

    private final ApprovalService approvalService;

    private final AmqpTemplate amqpTemplate;

    /**
     * 微信推送文章跳转链接
     */
    @Value("${wechat.jumpUrl}")
    private String jumpUrl;

    @Override
    public ResourceType getResourceType() {
        return ResourceType.GENERIC_TABLE;
    }

    @Override
    public int approval(Map<String, Object> entity, ResourceMetadata metadata) {
        doUpdate(entity, metadata);

        Approval approval = new Approval();
        approval.setDataId(new Long(entity.get("id").toString()));
        approval.setOperateTime(new Date());
        approval.setOperate(entity.get("operate").toString());
        approval.setOperator(entity.get("operator").toString());
        approval.setAuditOpinion(entity.get("auditOpinion").toString());
        switch (metadata.getKey()) {
            // 消息推送
            case "affairOpen":
                approval.setTabId(74L);
                break;
            // 招商政策
            case "cboPolicy":
                approval.setTabId(24L);
                break;
            // 招商项目
            case "cboItem":
                approval.setTabId(25L);
                break;
            // 招商动态
            case "cboDynamic":
                approval.setTabId(26L);
                break;
            default:
                throw new ServiceException("找不到Key值对应的表");
        }
        approvalService.add(approval);
        return 1;
    }

    @Override
    public GenericEntity doGet(QueryParameter queryParameter, Object objectId, ResourceMetadata metadata, SerializeType serializeType) {
        List<Head> headList = headService.listBy(
                parseRequiredPrimitive(metadata.getDict().getCval(), Integer.class),
                parseRequiredPrimitive(objectId, Long.class)
        );

        // 如果返回结果不为1的情况抛出异常
        return CollectionUtils.isEmpty(headList)
                ? null
                : getRequiredSingleElement(GenericUtils.toEntitiesFromProperties(metadata, headList, serializeType, Head::getDocNo));
    }

    @Override
    public List<GenericEntity> doList(@Nullable QueryParameter queryParameter,
                                      ResourceMetadata metadata, SerializeType serializeType) {
        Head queryHead = Head.builder()
                .docType(parseRequiredPrimitive(metadata.getDict().getCval(), Integer.class))
                .deleted(0)
                .build();
        List<Head> headList = headService.list(Example.of(queryHead));

        return CollectionUtils.isEmpty(headList)
                ? Collections.emptyList()
                : GenericUtils.toEntitiesFromProperties(metadata, headList, serializeType, Head::getDocNo);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<GenericEntity> doPage(QueryParameter queryParameter, ResourceMetadata metadata,
                                      SerializeType serializeType, Pageable pageable) {

        Integer docType = ResourceUtils.getDocType(metadata.getDict());
        Sort sort = pageable.getSort();

        Match match = queryParameter.getParameter(Match.class);

        Page<String> docNoPage = match == null && sort.isUnsorted()
                ? headService.getDocNoPage(null, docType, pageable)
                : headService.getDocNoPage(new GenericTableMatch(
                        match == null ? Collections.emptyMap() : match.getFieldOps(), metadata, pageable),
                docType, pageable);

        if (docNoPage.isEmpty())
            return Page.empty();

        // 这里查出来的数据会丢失排序
        List<Head> headList = headService.listByDocNos(docType, docNoPage.getContent());

        List<GenericEntity> entities = GenericUtils.toEntitiesFromProperties(metadata, headList, serializeType, Head::getDocNo);
        // 如果有必要的话，进行排序操作
        if (sort.isSorted() && !CollectionUtils.isEmpty(entities)) {
            List<Sort.Order> orderList = sort.toList();

            // 后面排序的顺序会覆盖前面的，先排后面的排序字段
            for (int i = orderList.size() - 1; i >= 0; i--) {
                Sort.Order order = orderList.get(i);
                String property = order.getProperty();
                Sort.Direction direction = order.getDirection();
                entities.sort(Comparator.comparing(
                        entity -> {
                            GenericProperty p = entity.getProperty(property);
                            return (Comparable<Object>) p.getValue();
                        },
                        new DirectionComparator(direction))
                );
            }
        }

        return CollectionUtils.isEmpty(entities)
                ? Page.empty()
                : PageableExecutionUtils.getPage(entities, pageable, docNoPage::getTotalElements);
    }

    @Override
    protected void beforeAdd(Map<String, Object> entity, ResourceMetadata metadata) {
        Integer docType = parseRequiredPrimitive(metadata.getDict().getCval(), Integer.class);

        // 生成一个id
        Long newId = headService.getNextId(docType);
        // 生成一个docNo
        String newDocNo = headService.getNextDocNo(docType, metadata.getResource().getResCode());

        // 向新增的实体设置id和docNo
        entity.put("id", newId);
        entity.put("docNo", newDocNo);
    }

    @Override
    public GenericEntity doAdd(Map<String, Object> entity, ResourceMetadata metadata) {
        // 准备一些共用属性
        Long now = parseRequiredPrimitive(entity.get("createTime"), Long.class);
        Long appId = parseRequiredPrimitive(entity.get("appId"), Long.class);
        Long ownerId = parseRequiredPrimitive(entity.get("ownerId"), Long.class);
        Integer docType = parseRequiredPrimitive(metadata.getDict().getCval(), Integer.class);
        Integer deleted = parseRequiredPrimitive(entity.get("deleted"), Integer.class);
        String docNo = parseRequiredPrimitive(entity.get("docNo"), String.class);

        // 构建Head对象的函数
        Function<MetaField, Head> headMapper = metaField -> Head.builder()
                .appId(appId)
                .ownerId(ownerId)
                .deleted(deleted)
                .createTime(now)
                .docType(docType)
                .docNo(docNo)
                .name(metaField.getNameEn())
                .value(parseRequiredPrimitive(entity.get(metaField.getNameEn()), String.class))
                .build();

        // 将map转换成Head的列表（小字段）
        List<Head> headList = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> entity.containsKey(metaField.getNameEn()) && !"TEXT".equalsIgnoreCase(metaField.getNativeType()))
                .map(headMapper)
                .collect(Collectors.toList());

        // 将map转换成Head的列表（大字段）
        List<Head> bigHeadList = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> entity.containsKey(metaField.getNameEn()) && "TEXT".equalsIgnoreCase(metaField.getNativeType()))
                .map(headMapper)
                .collect(Collectors.toList());

        // 批量新增所有属性
        List<Head> newHeadList = headService.addAll(headList);
        if (!CollectionUtils.isEmpty(bigHeadList)) {
            List<Head> newBigHeadList = headService.addAllBig(bigHeadList);
            newHeadList.addAll(newBigHeadList);
        }
        List<GenericEntity> genericEntities = GenericUtils.toEntitiesFromProperties(metadata, newHeadList, SerializeType.VALUE, Head::getDocNo);
        Assert.notEmpty(genericEntities, "数据保存失败");

        return getRequiredSingleElement(genericEntities);
    }

    @Override
    public int doUpdate(Map<String, Object> entity, ResourceMetadata metadata) {
        // 准备一些共用属性
        Long now = Long.valueOf(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()));

        MetaField pkMetaField = metadata.getMetaFields().stream()
                .filter(metaField -> metaField.getPrimaryKey() == 1)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("缺少主键字段元数据信息"));
        Object pk = entity.get(pkMetaField.getNameEn());
        Integer docType = parseRequiredPrimitive(metadata.getDict().getCval(), Integer.class);

        TabSeq tabSeq = tabSeqMapper.get(parseRequiredPrimitive(pk, Long.class), docType);
        Assert.notNull(tabSeq, "该id的数据不存在: " + pk);

        String docNo = tabSeq.getDocNo();
        // 将map转换成Head的列表
        List<Head> headList = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> entity.containsKey(metaField.getNameEn()) && !"TEXT".equalsIgnoreCase(metaField.getNativeType()))
                .map(metaField -> Head.builder()
                        .docNo(docNo)
                        .name(metaField.getNameEn())
                        .value(String.valueOf(entity.get(metaField.getNameEn())))
                        .updateTime(now)
                        .deleted(0)
                        .build()
                ).collect(Collectors.toList());

        // 将map转换成Head的列表（大字段）
        List<Head> bigHeadList = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> entity.containsKey(metaField.getNameEn()) && "TEXT".equalsIgnoreCase(metaField.getNativeType()))
                .map(metaField -> Head.builder()
                        .docNo(docNo)
                        .name(metaField.getNameEn())
                        .value(String.valueOf(entity.get(metaField.getNameEn())))
                        .updateTime(now)
                        .deleted(0)
                        .build()
                ).collect(Collectors.toList());

        // 批量更新所有属性
        headList.forEach(headService::update);
        bigHeadList.forEach(headService::updateForBig);

        // 发送消息
        String releaseStatus = entity.get("releaseStatus") != null ? entity.get("releaseStatus").toString() : "";
        if ("affairOpen".equals(metadata.getKey()) && StringUtils.hasText(releaseStatus) && "1".equals(releaseStatus)) {
            if ("微信推送".equals(entity.get("pushChannel").toString())) {
                String plate = entity.get("plate").toString();
                ParkNewsReq parkNewsReq = new ParkNewsReq();
                parkNewsReq.setTitle(entity.get("title").toString());
                parkNewsReq.setProgram(plate);
                String url = String.format(jumpUrl+"#/PolicyOpenDetail?data=%s&num=0", entity.get("id").toString());
                //String url = String.format("http://10.131.231.233/reception/#/PolicyOpenDetail?data=%s&num=0", entity.get("id").toString())
                parkNewsReq.setUrl(url);
                amqpTemplate.convertAndSend(MqConstant.PUSH_NEWS_WX_QUEUE, parkNewsReq);
            }
        }
        return headList.size();
    }

    @Override
    protected int doDelete(Object id, ResourceMetadata metadata) {
        headService.deleteAll(
                parseRequiredPrimitive(id, Long.class),
                parseRequiredPrimitive(metadata.getDict().getCval(), Integer.class));
        return 1;
    }

    @Override
    protected int doDeleteAll(List<Object> ids, ResourceMetadata metadata) {
        ids.forEach(id -> headService.deleteAll(
                parseRequiredPrimitive(id, Long.class),
                parseRequiredPrimitive(metadata.getDict().getCval(), Integer.class)
        ));
        return ids.size();
    }

    @Override
    protected List<GenericEntity> doGeom(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType) {
        return null;
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, String key, List<String> docNoList) {
        List<String> fileNameList = null;
        String zipName = "附件";
        if ("plan".equals(key)) {
            fileNameList = headService.listPlanAnnex(docNoList);
            zipName = "规划管理附件";
        }
        if ("mgtInstutution".equals(key)) {
            fileNameList = headService.listMgtInstututionAnnex(docNoList);
            zipName = "管理制度附件";
        }

        ZipUtil.downZip(fileNameList, zipName, request, response, fileProperties);
    }

    @Override
    public List<Object> excel(ResourceMetadata metadata, String key, MultipartFile excel) {
        if ("disposalMethod".equals(key)) {
            return ExcelUtil.readExcel(excel, new DisposalMethodExcel(), 1, 1);
        }
        if ("unitFunction".equals(key)) {
            return ExcelUtil.readExcel(excel, new UnitFunctionExcel(), 1, 1);
        }
        return null;
    }
}
