package com.icepoint.base.web.resource.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tangyi.common.core.exceptions.ServiceException;
import com.icepoint.base.api.domain.*;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.web.resource.component.ShiJiaZhuangResultMapper;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.metadata.ResourceType;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.service.complex.ResourceMetadataService;
import com.icepoint.base.web.resource.util.GenericUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;

/**
 * 第三方服务资源仓储
 *
 * @author Jiawei Zhao
 * @see ResourceType#THIRD_PARTY
 */
@Component
@PropertySource("classpath:generic-resource.yml")
public class ThirdPartyRepository extends GenericRepositoryTemplate {

    private static final ParameterizedTypeReference<Map<String, Object>> RESPONSE_TYPE =
            new ParameterizedTypeReference<Map<String, Object>>() {
            };

    private final RestTemplate restTemplate;
    private final ResourceMetadataService resourceMetadataService;

    public ThirdPartyRepository(
            RestTemplate restTemplate, ResourceMetadataService resourceMetadataService) {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Nullable
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = (hostname, session) -> true;

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new IllegalStateException(e);
        }

        this.restTemplate = restTemplate;
        this.resourceMetadataService = resourceMetadataService;
    }

    private RequestEntity<Void> getRequestEntity(QueryParameter queryParameter, ResourceMetadata metadata) {
        String url = metadata.getMetaTab().getServiceUrl();
        // 适配参数
        if ("PARK_ENVIRONMENTAL".equals(metadata.getResource().getResCode())
                || "SEWAGE_MONITORING".equals(metadata.getResource().getResCode())
                || "SWHW_MANAGE".equals(metadata.getResource().getResCode())
                || "RES_FAR_EP_POLLUTANT_MONITORING".equals(metadata.getResource().getResCode()) //特征污染物在线监测数据
                || "SMOKE_MONITORING".equals(metadata.getResource().getResCode())) {
            String queryParams = "?dataTime=" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            url += queryParams;
        }
        if ("ENERGY_VALUE".equals(metadata.getResource().getResCode())) {
            // 测试使用：?startTime=2021-01-31&endTime=2021-02-01
            String startTime = "?startTime=" + LocalDate.now().minusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String endTime = "&endTime=" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            url += startTime+endTime;
        }
        if ("WHGYTJ".equals(metadata.getResource().getResCode())
                || "WHPTJ".equals(metadata.getResource().getResCode())) {
            Match match = ((GenericQueryParameter) queryParameter).getMatch();
            if (match == null) {
                throw new ServiceException("缺少企业编号（org_code）参数");
            }
            Collection<Object> orgCodeList = match.getFieldOps().get("org_code").getOps().values();
            if (CollectionUtils.isEmpty(orgCodeList)) {
                throw new ServiceException("缺少企业编号（org_code）参数");
            }
            String orgCode = orgCodeList.toArray()[0].toString();
            String queryParams = "?org_code=" + orgCode;
            url += queryParams;
        }
        if ("EXHAUST_EMISSION".equals(metadata.getResource().getResCode())) {
            String queryParams = "?field=smoke";
            url += queryParams;
        }
        if ("SEWAGE_DISCHARGE".equals(metadata.getResource().getResCode())) {
            String queryParams = "?field=totalAmmoniaNitrogen";
            url += queryParams;
        }
        if ("FIRE_STATION".equals(metadata.getResource().getResCode())) {
            String queryParams = "?resourceIdentify=squadron";
            url += queryParams;
        }

        ObjectMapper objectMapper = getRequiredBean(ObjectMapper.class);

        JsonNode params;
        try {
            params = objectMapper.readTree(metadata.getMetaTab().getReqParam());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }

        JsonNode query = params.get("query");
        if (query != null && !query.isNull() && !query.isEmpty()) {
            List<String> queryStrings = new ArrayList<>();
            query.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                String fieldValue = entry.getValue().asText();

                if (StringUtils.hasText(fieldValue)) {
                    queryStrings.add(fieldName + "=" + fieldValue);
                }
            });

            url = "?" + String.join("&", queryStrings);
        }

        RequestEntity.HeadersBuilder<?> builder = RequestEntity.get(URI.create(url))
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8);

        JsonNode headers = params.get("header");
        if (headers != null && !headers.isNull() && !headers.isEmpty()) {
            headers.fields().forEachRemaining(entry -> {
                String headerName = entry.getKey();
                String headerValue = entry.getValue().asText();
                builder.header(headerName, headerValue);
            });
        }

        return builder.build();
    }

    private RequestEntity<Void> getRequestEntity(ResourceMetadata metadata, Integer geomType) {
        if (geomType == null) {
            throw new ServiceException("geomType不能为空");
        }

        String url = metadata.getMetaTab().getServiceUrl();
        // 适配参数
        String queryParams = "?poi_type=" + geomType;
        url += queryParams;

        ObjectMapper objectMapper = getRequiredBean(ObjectMapper.class);

        JsonNode params;
        try {
            params = objectMapper.readTree(metadata.getMetaTab().getReqParam());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }

        JsonNode query = params.get("query");
        if (query != null && !query.isNull() && !query.isEmpty()) {
            List<String> queryStrings = new ArrayList<>();
            query.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                String fieldValue = entry.getValue().asText();

                if (StringUtils.hasText(fieldValue)) {
                    queryStrings.add(fieldName + "=" + fieldValue);
                }
            });

            url = "?" + String.join("&", queryStrings);
        }

        RequestEntity.HeadersBuilder<?> builder = RequestEntity.get(URI.create(url))
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8);

        JsonNode headers = params.get("header");
        if (headers != null && !headers.isNull() && !headers.isEmpty()) {
            headers.fields().forEachRemaining(entry -> {
                String headerName = entry.getKey();
                String headerValue = entry.getValue().asText();
                builder.header(headerName, headerValue);
            });
        }

        return builder.build();
    }

    /**
     * 针对于需要做分页的中台接口
     * @param metadata 元数据
     * @param pageable 分页参数
     * @return RequestEntity
     */
    private RequestEntity<Void> getRequestEntity(ResourceMetadata metadata, Pageable pageable) {

        String url = metadata.getMetaTab().getServiceUrl();

        //获取分页参数
        int number = pageable.getPageNumber();
        int size = pageable.getPageSize();

        String pageNum = "?pageNum="+number;
        String pageSize = "&pageSize="+size;

        //生成带有分页参数的的url
        url = url + pageNum + pageSize;

        ObjectMapper objectMapper = getRequiredBean(ObjectMapper.class);

        JsonNode params;
        try {
            params = objectMapper.readTree(metadata.getMetaTab().getReqParam());
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }

        JsonNode query = params.get("query");
        if (query != null && !query.isNull() && !query.isEmpty()) {
            List<String> queryStrings = new ArrayList<>();
            query.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                String fieldValue = entry.getValue().asText();

                if (StringUtils.hasText(fieldValue)) {
                    queryStrings.add(fieldName + "=" + fieldValue);
                }
            });

            url = "?" + String.join("&", queryStrings);
        }

        RequestEntity.HeadersBuilder<?> builder = RequestEntity.get(URI.create(url))
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8);

        JsonNode headers = params.get("header");
        if (headers != null && !headers.isNull() && !headers.isEmpty()) {
            headers.fields().forEachRemaining(entry -> {
                String headerName = entry.getKey();
                String headerValue = entry.getValue().asText();
                builder.header(headerName, headerValue);
            });
        }

        return builder.build();
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.THIRD_PARTY;
    }

    @Override
    protected GenericEntity doGet(QueryParameter queryParameter, Object objectId, ResourceMetadata metadata, SerializeType serializeType) {
        List<GenericEntity> resultList = list(queryParameter, metadata, serializeType);
        return resultList.stream()
                .filter(entity -> entity.getProperty("id").getValue() != null && objectId.equals(entity.getProperty("id").getValue()))
                .findAny()
                .orElse(null);
    }

    @Override
    protected GenericEntity afterGet(
            @Nullable GenericEntity entity, Object objectId,
            ResourceMetadata metadata, SerializeType serializeType) {
        if (entity == null) {
            return null;
        }

        List<MetaField> metaFields = metadata.getMetaFields();
        MetaField xField = metaFields.stream()
                .filter(metaField -> metaField.getNameEn().equals("x"))
                .findAny()
                .orElse(null);
        if (xField == null)
            return entity;

        MetaField yField = metaFields.stream()
                .filter(metaField -> metaField.getNameEn().equals("y"))
                .findAny()
                .orElse(null);
        if (yField == null)
            return entity;

        GenericProperty companyName = entity.getProperty("organizationName");
        if (companyName.getValue() == null
                && (!entity.hasProperty("x") || !entity.hasProperty("y"))) {
            return entity;
        }

        ResourceMetadata entpMeta = resourceMetadataService.get("entp");
        if (entpMeta == null)
            return entity;

        GenericQueryParameter queryParameter = new GenericQueryParameter();
        Map<String, FieldOperation> fieldOps = new HashMap<>();
        fieldOps.put("companyName", new FieldOperation("companyName", Collections.singletonMap(Operation.EQ, companyName.getValue())));
        Match match = new Match(fieldOps);
        queryParameter.setMatch(match);

        GenericRepository repository = getRequiredBean(
                GenericRepositoryAdapter.class).get(ResourceType.valueOf(entpMeta.getResource().getResType()));
        List<GenericEntity> entpResultList = repository.list(queryParameter, entpMeta, serializeType);
        if (CollectionUtils.isEmpty(entpResultList))
            return entity;

        GenericEntity entp = getRequiredSingleElement(entpResultList);
        if (!(entp.hasProperty("x") && entp.hasProperty("y")))
            return entity;

        GenericProperty x = entp.getProperty("x");
        GenericProperty y = entp.getProperty("y");
        if (x.getValue() == null || x.getValue() == null)
            return entity;

        Integer xOrder = xField.getIdx();
        Integer yOrder = yField.getIdx();

        Collection<GenericProperty> oldProperties = entity.getProperties();
        JsonAnyProperties<GenericProperty> newProperties = new JsonAnyProperties<>();

        int i = 0;
        for (GenericProperty oldProperty : oldProperties) {
            if (i == xOrder) {
                newProperties.setProperty(xField.getNameEn(), x);
            }
            if (i == yOrder) {
                newProperties.setProperty(yField.getNameEn(), y);
            }

            newProperties.setProperty(oldProperty.getName(), oldProperty);
            i++;
        }

        ReflectionUtils.doWithFields(
                entity.getClass(),
                field -> {
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, entity, newProperties);
                },
                field -> field.getName().equals("properties")
        );

        GenericProperty newX = entity.getProperty("x");
        GenericProperty newY = entity.getProperty("y");
        if (!StringUtils.hasText(parsePrimitive(newX.getValue(), String.class))) {
            entity.setProperty(x);
        }
        if (!StringUtils.hasText(parsePrimitive(newY.getValue(), String.class))) {
            entity.setProperty(y);
        }

        return entity;
    }

    @Override
    protected List<GenericEntity> doList(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType) {
        try {
            ResponseEntity<Map<String, Object>> response = StringUtils.hasText(metadata.getMetaTab().getServiceUrl()) ? restTemplate.exchange(getRequestEntity(queryParameter, metadata), RESPONSE_TYPE) : null;
            if (response == null) {
                return Collections.emptyList();
            }
            List<Map<String, Object>> resultList;
            if ("HIDDEN_DANGER".equals(metadata.getResource().getResCode())
                    || "HAZARDOUS_AREHOUSE".equals(metadata.getResource().getResCode())
                    || "SAFETY_EVALUATION".equals(metadata.getResource().getResCode())
                    || "SAFETY_PRODUCTION".equals(metadata.getResource().getResCode())
                    || "SAFETY_STANDARDS".equals(metadata.getResource().getResCode())
                    || "SMOKE_MONITORING".equals(metadata.getResource().getResCode())
                    || "POI_POINT".equals(metadata.getResource().getResCode())
                    || "HIDDEN_DANGER_ENTERPRISE".equals(metadata.getResource().getResCode())
                    || "HIDDEN_DANGER_COMMON".equals(metadata.getResource().getResCode())
                    || "HIDDEN_DANGER_IMPORTANT".equals(metadata.getResource().getResCode())
                    || "HIDDEN_DANGER_RATE".equals(metadata.getResource().getResCode())
                    || "HIDDEN_DANGER_PENDING".equals(metadata.getResource().getResCode())
                    || "HIDDEN_DANGER_REPAIRING".equals(metadata.getResource().getResCode())
                    || "HIDDEN_DANGER_REVIEWING".equals(metadata.getResource().getResCode())
                    || "HIDDEN_DANGER_DELAYED".equals(metadata.getResource().getResCode())
                    || "WHGYTJ".equals(metadata.getResource().getResCode()) //危险化学品统计(企业)
                    || "WHPTJ".equals(metadata.getResource().getResCode())  //危化工艺统计(企业)
                    || "DANGEROUS_CHEMICAL".equals(metadata.getResource().getResCode())  //危化工艺统计(园区)
                    || "ENTERPRISE_RISK_UNIT".equals(metadata.getResource().getResCode())
                    || "RES_FAR_EP_POLLUTANT_MONITORING".equals(metadata.getResource().getResCode()) //特征污染物
                    || "ETSU".equals(metadata.getResource().getResCode()) //技术支持机构
                    || "SCHOOL".equals(metadata.getResource().getResCode()) //周边敏感目标-学校
                    || "HOUSING".equals(metadata.getResource().getResCode()) //周边敏感目标-小区
                    || "SAFETY_CHANGE_TREND".equals(metadata.getResource().getResCode()) //园区风险指数
                    || "SAFETY_SUPERVISION_ALARMS".equals(metadata.getResource().getResCode()) //安全监管报警数量
                    || "SEWAGE_MONITORING".equals(metadata.getResource().getResCode())) {
                resultList = ShiJiaZhuangResultMapper.SECURITY.getMapper().apply(response.getBody());
            } else if ("PARK_ENVIRONMENTAL".equals(metadata.getResource().getResCode())
                    || "EXHAUST_EMISSION".equals(metadata.getResource().getResCode())
                    || "SEWAGE_DISCHARGE".equals(metadata.getResource().getResCode())
                    || "ROUTINE_TESTING".equals(metadata.getResource().getResCode())
            ) {
                resultList = ShiJiaZhuangResultMapper.ENVIRONMENTAL.getMapper().apply(response.getBody());
            } else if ("EMERGENCY_PLANS".equals(metadata.getResource().getResCode())) {
                resultList = ShiJiaZhuangResultMapper.EMERGENCY.getMapper().apply(response.getBody());
                Match match = ((GenericQueryParameter) queryParameter).getMatch();
                Collection<Object> qiyemingchengList = match.getFieldOps().get("qiyemingcheng").getOps().values();
                if (CollectionUtils.isEmpty(qiyemingchengList)) {
                    throw new ServiceException("缺少企业名称（qiyemingcheng）参数");
                }
                if (resultList == null) {
                    return Collections.emptyList();
                }
                String qiyemingcheng = qiyemingchengList.toArray()[0].toString();
                Map<String, Object> resultItem = resultList.stream().filter(stringObjectMap -> qiyemingcheng.equals(stringObjectMap.get("qiyemingcheng"))).findFirst().orElse(new HashMap<>());
                resultList = new ArrayList<>();
                resultList.add(resultItem);
            } else if ("ENERGY_VALUE".equals(metadata.getResource().getResCode())) {
                resultList = ShiJiaZhuangResultMapper.EMERGENCY.getMapper().apply(response.getBody());
                Match match = ((GenericQueryParameter) queryParameter).getMatch();
                Collection<Object> qiyemingchengList = match.getFieldOps().get("corporateAbb").getOps().values();
                if (CollectionUtils.isEmpty(qiyemingchengList)) {
                    throw new ServiceException("缺少企业名称（corporateAbb）参数");
                }
                if (resultList == null) {
                    return Collections.emptyList();
                }
                String qiyemingcheng = qiyemingchengList.toArray()[0].toString();
                Map<String, Object> resultItem = resultList.stream().filter(stringObjectMap -> qiyemingcheng.equals(stringObjectMap.get("corporateAbb"))).findFirst().orElse(new HashMap<>());
                // FIXME 为了测试写死，待修复
                resultItem.put("date", "2021-01-31");
                resultList = new ArrayList<>();
                resultList.add(resultItem);
            }else if("TOTAL_PLAN".equals(metadata.getResource().getResCode())
                    || "SPECIAL_PLAN".equals(metadata.getResource().getResCode())
                    || "LIVE_PLAN".equals(metadata.getResource().getResCode())){ //应急预案下属三个子节点返回数据特殊处理

                if("TOTAL_PLAN".equals(metadata.getResource().getResCode())){ //总体应急预案
                    resultList = ShiJiaZhuangResultMapper.EMERGENCY_TOTAL_PLAN.getMapper().apply(response.getBody());

                } else if("SPECIAL_PLAN".equals(metadata.getResource().getResCode())){ //专项应急预案
                    resultList = ShiJiaZhuangResultMapper.EMERGENCY_SPECIAL_PLAN.getMapper().apply(response.getBody());

                } else{ //现场处置方案
                    resultList = ShiJiaZhuangResultMapper.EMERGENCY_LIVE_PLAN.getMapper().apply(response.getBody());
                }

            }else {
                resultList = ShiJiaZhuangResultMapper.EMERGENCY.getMapper().apply(response.getBody());
            }
            if (CollectionUtils.isEmpty(resultList))
                return Collections.emptyList();

            List<GenericEntity> entities = GenericUtils.toEntitiesFromMaps(metadata, resultList, serializeType);

            Match match = queryParameter.getParameter(Match.class);
            if (match == null)
                return entities;

            MatchOption option = match.getOption();
            Collection<FieldOperation> fops = match.getOps();

            return entities.stream()
                    .filter(entity -> {
                        boolean opResult = true;

                        for (FieldOperation fop : fops) {
                            String field = fop.getField();
                            Map<Operation, Object> ops = fop.getOps();

                            GenericProperty property = entity.getProperty(field);
                            for (Map.Entry<Operation, Object> entry : ops.entrySet()) {
                                Operation op = entry.getKey();
                                Object value = entry.getValue();

                                if ((option.isIgnoreNullValue() && value == null)
                                        || (option.isIgnoreEmptyString() && !StringUtils.hasText(value.toString().trim()))) {
                                    continue;
                                }

                                Object propertyValue = property.getValue();
                                if (propertyValue == null) {
                                    opResult = false;

                                } else {
                                    switch (op) {
                                        case EQ:
                                            opResult = Objects.equals(
                                                    parseRequiredPrimitive(propertyValue, String.class),
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        case NEQ:
                                            opResult = !Objects.equals(
                                                    parseRequiredPrimitive(propertyValue, String.class),
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        case GT:
                                            opResult = Objects.compare(
                                                    parseRequiredPrimitive(propertyValue, Long.class),
                                                    parseRequiredPrimitive(value, Long.class),
                                                    Comparators.comparable()
                                            ) >= 1;
                                            break;
                                        case GE:
                                            opResult = Objects.compare(
                                                    parseRequiredPrimitive(propertyValue, Long.class),
                                                    parseRequiredPrimitive(value, Long.class),
                                                    Comparators.comparable()
                                            ) >= 0;
                                            break;
                                        case LT:
                                            opResult = Objects.compare(
                                                    parseRequiredPrimitive(propertyValue, Long.class),
                                                    parseRequiredPrimitive(value, Long.class),
                                                    Comparators.comparable()
                                            ) <= -1;
                                            break;
                                        case LE:
                                            opResult = Objects.compare(
                                                    parseRequiredPrimitive(propertyValue, Long.class),
                                                    parseRequiredPrimitive(value, Long.class),
                                                    Comparators.comparable()
                                            ) <= 0;
                                            break;
                                        case STARTS_WITH:
                                            opResult = parseRequiredPrimitive(propertyValue, String.class).startsWith(
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        case ENDS_WITH:
                                            opResult = parseRequiredPrimitive(propertyValue, String.class).endsWith(
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        case CONTAINS:
                                            opResult = parseRequiredPrimitive(propertyValue, String.class).contains(
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        default:
                                            // FIXME: 还没有BTW和REG和IN条件没做
                                            throw new UnsupportedOperationException();
                                    }
                                }
                                if (!opResult) break;
                            }
                            if (!opResult) break;
                        }
                        return opResult;
                    })
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            throw new ServiceException("第三方接口访问超时");
        }
    }

    @Override
    public List<GenericEntity> geom(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType, Integer geomType) {
        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(getRequestEntity(metadata, geomType), RESPONSE_TYPE);
            List<Map<String, Object>> resultList;
            resultList = ShiJiaZhuangResultMapper.SECURITY.getMapper().apply(response.getBody());
            if (CollectionUtils.isEmpty(resultList))
                return Collections.emptyList();

            List<GenericEntity> entities = GenericUtils.toEntitiesFromMaps(metadata, resultList, serializeType);

            Match match = queryParameter.getParameter(Match.class);
            if (match == null)
                return entities;

            MatchOption option = match.getOption();
            Collection<FieldOperation> fops = match.getOps();

            return entities.stream()
                    .filter(entity -> {
                        boolean opResult = true;

                        for (FieldOperation fop : fops) {
                            String field = fop.getField();
                            Map<Operation, Object> ops = fop.getOps();

                            GenericProperty property = entity.getProperty(field);
                            for (Map.Entry<Operation, Object> entry : ops.entrySet()) {
                                Operation op = entry.getKey();
                                Object value = entry.getValue();

                                if ((option.isIgnoreNullValue() && value == null)
                                        || (option.isIgnoreEmptyString() && !StringUtils.hasText(value.toString().trim()))) {
                                    continue;
                                }

                                Object propertyValue = property.getValue();
                                if (propertyValue == null) {
                                    opResult = false;

                                } else {
                                    switch (op) {
                                        case EQ:
                                            opResult = Objects.equals(
                                                    parseRequiredPrimitive(propertyValue, String.class),
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        case NEQ:
                                            opResult = !Objects.equals(
                                                    parseRequiredPrimitive(propertyValue, String.class),
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        case GT:
                                            opResult = Objects.compare(
                                                    parseRequiredPrimitive(propertyValue, Long.class),
                                                    parseRequiredPrimitive(value, Long.class),
                                                    Comparators.comparable()
                                            ) >= 1;
                                            break;
                                        case GE:
                                            opResult = Objects.compare(
                                                    parseRequiredPrimitive(propertyValue, Long.class),
                                                    parseRequiredPrimitive(value, Long.class),
                                                    Comparators.comparable()
                                            ) >= 0;
                                            break;
                                        case LT:
                                            opResult = Objects.compare(
                                                    parseRequiredPrimitive(propertyValue, Long.class),
                                                    parseRequiredPrimitive(value, Long.class),
                                                    Comparators.comparable()
                                            ) <= -1;
                                            break;
                                        case LE:
                                            opResult = Objects.compare(
                                                    parseRequiredPrimitive(propertyValue, Long.class),
                                                    parseRequiredPrimitive(value, Long.class),
                                                    Comparators.comparable()
                                            ) <= 0;
                                            break;
                                        case STARTS_WITH:
                                            opResult = parseRequiredPrimitive(propertyValue, String.class).startsWith(
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        case ENDS_WITH:
                                            opResult = parseRequiredPrimitive(propertyValue, String.class).endsWith(
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        case CONTAINS:
                                            opResult = parseRequiredPrimitive(propertyValue, String.class).contains(
                                                    parseRequiredPrimitive(value, String.class)
                                            );
                                            break;
                                        default:
                                            // FIXME: 还没有BTW和REG和IN条件没做
                                            throw new UnsupportedOperationException();
                                    }
                                }
                                if (!opResult) break;
                            }
                            if (!opResult) break;
                        }
                        return opResult;
                    })
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            throw new ServiceException("第三方接口访问超时");
        }
    }

    @Override
    protected List<GenericEntity> doGeom(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType) {
        return null;
    }

    @Override
    protected List<GenericEntity> afterList(List<GenericEntity> entities, ResourceMetadata metadata, SerializeType serializeType) {
        if (CollectionUtils.isEmpty(entities))
            return entities;

        //对没有id字段的接口，手动为字段赋值，用于查询详情
        if ("ETSU".equals(metadata.getResource().getResCode()) //技术支持机构
                || "SCHOOL".equals(metadata.getResource().getResCode()) //周边敏感目标-学校
                || "HOUSING".equals(metadata.getResource().getResCode()) //周边敏感目标-小区
                || "SAFETY_CHANGE_TREND".equals(metadata.getResource().getResCode()) //园区风险指数
                || "ENERGY_CONSUMPTION_TREND".equals(metadata.getResource().getResCode()) //能耗使用趋势(园区)
                || "SAFETY_SUPERVISION_ALARMS".equals(metadata.getResource().getResCode())){ //安全监管报警数量
            Iterator<GenericEntity> iterator = entities.iterator();
            int index = 1;
            while(iterator.hasNext()){
                GenericEntity entity = iterator.next();
                if(entity.getProperty("id").getValue() == null || entity.getProperty("id").getValue() == ""){
                    String indexStr = String.valueOf(index);
                    ValueGenericProperty id = new ValueGenericProperty("id", indexStr);
                    entity.setProperty(id);
                    index++;
                }
            }
        }

        entities.forEach(entity -> {
            GenericProperty idProperty = entity.getProperty("id");
            Object entityId = idProperty.getValue();
            if (entityId == null)
                throw new IllegalStateException("实体主键缺失");

            afterGet(entity, entityId, metadata, serializeType);
        });

        return entities;
    }

    @Override
    protected Page<GenericEntity> doPage(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType, Pageable pageable) {
        try {
            //获取中台接口响应数据
            ResponseEntity<Map<String, Object>> response = StringUtils.hasText(metadata.getMetaTab().getServiceUrl()) ? restTemplate.exchange(getRequestEntity(metadata, pageable), RESPONSE_TYPE) : null;

            //响应数据为空
            if (response == null) {
                return Page.empty();
            }

            List<Map<String, Object>> resultList;

            if("KEY_HAZARDOUS_CHEMICAL".equals(metadata.getResource().getResCode()) //重点监管危险化学品（园区）
                    || "MAJOR_DANGER".equals(metadata.getResource().getResCode()) //重大危险源
            ){

                resultList = ShiJiaZhuangResultMapper.PAGE.getMapper().apply(response.getBody());

                Map<String, Object> map = resultList.get(resultList.size() - 1);
                long total = Long.parseLong((String) map.get("total"));
                resultList.remove(resultList.size() - 1);

                if (CollectionUtils.isEmpty(resultList))
                    return Page.empty();

                List<GenericEntity> entities = GenericUtils.toEntitiesFromMaps(metadata, resultList, serializeType);

                Page<GenericEntity> page = new PageImpl<>(entities, pageable, total);
                return page;
            } else{
                throw new UnsupportedOperationException();
            }
        } catch (UnsupportedOperationException e) {
            throw new ServiceException("此中台接口不支持分页操作");
        } catch (RestClientException e) {
            throw new ServiceException("第三方接口访问超时");
        }
    }

    @Override
    protected GenericEntity doAdd(Map<String, Object> entity, ResourceMetadata metadata) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int doUpdate(Map<String, Object> entity, ResourceMetadata metadata) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int doDelete(Object id, ResourceMetadata metadata) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int doDeleteAll(List<Object> id, ResourceMetadata metadata) {
        throw new UnsupportedOperationException();
    }


}
