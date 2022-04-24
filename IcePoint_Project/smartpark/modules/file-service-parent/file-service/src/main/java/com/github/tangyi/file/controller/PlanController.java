package com.github.tangyi.file.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.exceptions.ServiceException;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.util.StringUtils;
import com.github.tangyi.file.api.model.Plan;
import com.github.tangyi.file.service.impl.PlanService;
import com.github.tangyi.file.util.ZipUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.zip.ZipOutputStream;

@SuppressWarnings("rawtypes")
@Api(tags = "规划管理")
@RequestMapping("plan")
@RestController
@RequiredArgsConstructor
public class PlanController extends BaseController {

    @Autowired
    private PlanService planService;

    @GetMapping(value = "zip")
    public void downLoadZipFile(@RequestParam("idList") List<Long> idList, HttpServletResponse response) throws IOException {
        String zipName = "规划文件_" + System.currentTimeMillis() + ".zip";
        List<Plan> planList = planService.listByIdList(idList);
        if (CollectionUtil.isEmpty(planList)) {
            return;
        }

        // 复制文件
        String baseFileRealDirectory = SpringContextHolder.getApplicationContext().getBean(SysProperties.class).getAttachPath() + File.separator
                + SysUtil.getSysCode() + File.separator;
        String tempFileRealDirectory = baseFileRealDirectory + "temp";
        String commonFileRealDirectory = baseFileRealDirectory + "0";
        File tempDirFile = new File(tempFileRealDirectory);
        if (!tempDirFile.exists()) {
            tempDirFile.mkdirs();
        }
        for (Plan plan : planList) {
            // 复制附件
            if (StringUtils.isNotEmpty(plan.getAnnexName())) {
                String annexNameSuffix = "-附件" + plan.getAnnexName().substring(plan.getAnnexName().lastIndexOf("."));
                copyFileUsingFileChannels(new File(commonFileRealDirectory, plan.getAnnex() + File.separator
                                + plan.getAnnexName()),
                        new File(tempFileRealDirectory, plan.getFileName() + annexNameSuffix));
            }


            // 复制规划地图
            if (StringUtils.isNotEmpty(plan.getPlanMapName())) {
                String planMapNameSuffix = "-规划地图" + plan.getPlanMapName().substring(plan.getPlanMapName().lastIndexOf("."));
                copyFileUsingFileChannels(new File(commonFileRealDirectory, plan.getPlanMap() + File.separator
                                + plan.getPlanMapName()),
                        new File(tempFileRealDirectory, plan.getFileName() + planMapNameSuffix));
            }

            // 复制实际用地地图
            if (StringUtils.isNotEmpty(plan.getRealityMapName())) {
                String realityMapNameSuffix = "-实际用地地图" + plan.getRealityMapName().substring(plan.getRealityMapName().lastIndexOf("."));
                copyFileUsingFileChannels(new File(commonFileRealDirectory, plan.getRealityMap() + File.separator
                                + plan.getRealityMapName()),
                        new File(tempFileRealDirectory, plan.getFileName() + realityMapNameSuffix));
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
