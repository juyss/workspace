package com.github.tangyi.file.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.exceptions.ServiceException;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.file.api.feign.BaseServiceClient;
import com.github.tangyi.file.api.model.InstitutionHistory;
import com.github.tangyi.file.service.impl.InstitutionHistoryService;
import com.github.tangyi.file.util.ZipUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

@SuppressWarnings("rawtypes")
@Api(tags = "管理制度历史版本")
@RequestMapping("institutionHistory")
@RestController
@RequiredArgsConstructor
public class InstitutionHistoryController extends BaseController {

    @Autowired
    private InstitutionHistoryService institutionHistoryService;

    @Autowired
    private BaseServiceClient baseServiceClient;

    @GetMapping(value = "zip")
    public void downLoadZipFile(@RequestParam("idList") List<Long> idList, @RequestParam("docNoList") List<String> docNoList, HttpServletResponse response) throws IOException {
        // 去除空字符串
        docNoList = docNoList.stream().filter(deptId -> !deptId.isEmpty()).collect(Collectors.toList());
        String zipName = "管理制度.zip";
        // 查两个表
        List<InstitutionHistory> institutionHistoryList = institutionHistoryService.listInstitutionHistory(idList);

        String genericEntityList = baseServiceClient.institutionHistoryPage(docNoList);
        JSONObject genericEntityListJsonObject = JSON.parseObject(genericEntityList);
        JSONArray jsonArray = genericEntityListJsonObject.getJSONArray("content");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject institutionHistoryJsonObject = jsonArray.getJSONObject(i);
            InstitutionHistory institutionHistory = new InstitutionHistory();
            institutionHistory.setName(institutionHistoryJsonObject.get("name").toString());
            institutionHistory.setAnnex(institutionHistoryJsonObject.get("annex").toString());
            institutionHistory.setAnnexName(institutionHistoryJsonObject.get("annexName").toString());
            institutionHistory.setVersion(Integer.valueOf(institutionHistoryJsonObject.get("version").toString()));
            if (StringUtils.hasText(institutionHistory.getAnnex()) && StringUtils.hasText(institutionHistory.getAnnex())) {
                institutionHistoryList.add(0, institutionHistory);// 头插法
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
