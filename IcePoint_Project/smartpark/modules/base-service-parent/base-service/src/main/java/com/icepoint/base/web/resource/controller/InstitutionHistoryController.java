package com.icepoint.base.web.resource.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.exceptions.ServiceException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.utils.SysUtil;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.entity.InstitutionHistory;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.web.resource.component.query.FieldOperation;
import com.icepoint.base.web.resource.component.query.GenericQueryParameter;
import com.icepoint.base.web.resource.component.query.Match;
import com.icepoint.base.web.resource.component.query.Operation;
import com.icepoint.base.web.resource.service.complex.upper.GenericEntityService;
import com.icepoint.base.web.resource.service.simple.InstitutionHistoryService;
import com.icepoint.base.web.resource.util.ZipUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

@SuppressWarnings("rawtypes")
@Api(tags = "管理制度历史版本")
@RequestMapping("institutionHistory")
@RestController
@RequiredArgsConstructor
public class InstitutionHistoryController extends CrudController<InstitutionHistoryService, InstitutionHistory, Long> {

    private final GenericEntityService genericEntityService;

    @PostMapping("add")
    @Log("新增管理制度历史版本")
    public ResponseBean<Long> add(@RequestBody InstitutionHistory institutionHistory) {
        return ResponseBeanUtils.addNewData(service.add(institutionHistory));
    }

    @GetMapping("list")
    @Log("查询管理制度历史版本")
    public ResponseBean<List<InstitutionHistory>> list(InstitutionHistory institutionHistory) {
        institutionHistory.setDeleted(0);
        return ResponseBeanUtils.queryMany(service.list(Example.of(institutionHistory)));
    }

    @DeleteMapping("deleteByIdList")
    @Log("删除管理制度历史版本")
    public ResponseBean<Boolean> deleteByIdList(@RequestParam("idList") List<Long> idList) {
        service.deleteByIdList(idList);
        return ResponseBean.success(Boolean.TRUE);
    }

    @GetMapping(value = "zip")
    @Log("下载管理制度历史版本")
    public void downLoadZipFile(@RequestParam("idList") List<Long> idList, @RequestParam("docNoList") List<String> docNoList, HttpServletResponse response) throws IOException {
        // 去除空字符串
        docNoList = docNoList.stream().filter(deptId -> !deptId.isEmpty()).collect(Collectors.toList());
        String zipName = "管理制度.zip";
        // 查两个表
        List<InstitutionHistory> institutionHistoryList = service.listInstitutionHistory(idList);

        if (CollectionUtil.isNotEmpty(docNoList)) {
            Page<GenericEntity> genericEntityList = getGenericEntities(docNoList);

            for (GenericEntity genericProperties : genericEntityList) {
                InstitutionHistory institutionHistory = new InstitutionHistory();
                institutionHistory.setName(genericProperties.getPropertyValue("name").toString());
                institutionHistory.setAnnex(genericProperties.getPropertyValue("annex").toString());
                institutionHistory.setAnnexName(genericProperties.getPropertyValue("annexName").toString());
                institutionHistory.setVersion(Integer.valueOf(genericProperties.getPropertyValue("version").toString()));
                if (StringUtils.hasText(institutionHistory.getAnnex()) && StringUtils.hasText(institutionHistory.getAnnex())) {
                    institutionHistoryList.add(0, institutionHistory);// 头插法
                }
            }
        }

        if (CollectionUtil.isEmpty(institutionHistoryList)) {
            return;
        }

        if (institutionHistoryList.size() >= 2) {
            zipName = institutionHistoryList.get(0).getName() + ".zip";
        }

        // 复制文件
        String baseFileRealDirectory = SpringContextHolder.getApplicationContext().getBean(SysProperties.class).getAttachPath() + File.separator
                + SysUtil.getSysCode() + File.separator;
        String tempFileRealDirectory = baseFileRealDirectory + "institutionHistoryTemp";
        String commonFileRealDirectory = baseFileRealDirectory + "0";
        File tempDirFile = new File(tempFileRealDirectory);
        if (!tempDirFile.exists()) {
            tempDirFile.mkdirs();
        }
        for (InstitutionHistory institutionHistory : institutionHistoryList) {
            // 复制附件
            if (StringUtils.hasText(institutionHistory.getAnnexName())) {
                String annexName = institutionHistory.getName() + "-版本" + institutionHistory.getVersion() + "-" + institutionHistory.getAnnexName();

                copyFileUsingFileChannels(new File(commonFileRealDirectory, institutionHistory.getAnnex() + File.separator
                                + institutionHistory.getAnnexName()),
                        new File(tempFileRealDirectory, annexName));
            }
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(zipName.getBytes("gb2312"), "ISO8859-1"));
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());

        try {
            ZipUtils.toZip(tempDirFile.getPath(), response.getOutputStream(), false);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            File[] listFiles = tempDirFile.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                listFiles[i].delete();
            }
            tempDirFile.delete();
        }
    }

    @GetMapping(value = "genericInstitutionHistory")
    @Log("获取管理制度历史版本")
    public Page<GenericEntity> getGenericEntities(@RequestParam List<String> docNoList) {
        GenericQueryParameter queryParameter = new GenericQueryParameter();

        Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
        Match match = new Match(fieldOps);
        Map<Operation, Object> ops = new LinkedHashMap<>();

        ops.put(Operation.IN, docNoList.toArray());
        FieldOperation fieldOperation = new FieldOperation("docNo", ops);
        fieldOps.put("docNo", fieldOperation);
        queryParameter.setMatch(match);

        Page<GenericEntity> genericEntityList = genericEntityService.page(queryParameter, "mgtInstutution", SerializeType.VALUE, PageRequest.of(0, 999999));
        return genericEntityList;
    }

    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (FileNotFoundException fileNotFoundException) {
            throw new ServiceException("找不到" + source.getName() + "文件，可能是文件丢失。");
        } finally {
            if (inputChannel != null) {
                inputChannel.close();
            }
            if (outputChannel != null) {
                outputChannel.close();
            }
        }
    }

}
