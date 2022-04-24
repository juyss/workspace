package com.icepoint.base.web.resource.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.exceptions.ServiceException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.util.StringUtils;
import com.icepoint.base.api.dto.PlanDto;
import com.icepoint.base.api.entity.Plan;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.web.resource.service.simple.PlanService;
import com.icepoint.base.web.resource.util.ZipUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
public class PlanController extends CrudController<PlanService, Plan, Long> {

    @GetMapping("planPage")
    @Log("查询规划管理")
    public ResponseBean<PageInfo<Plan>> page(PlanDto planDto) {
        Page<Plan> planPage = service.pageByHelper(planDto.getFileName(), planDto.getIntelligence(), planDto.getDeptIdList(), planDto.getPage(), planDto.getSize());
        PageInfo<Plan> objectPageInfo = new PageInfo<>();
        objectPageInfo.setList(planPage.getContent());
        objectPageInfo.setTotal(planPage.getTotalElements());
        objectPageInfo.setPageNum(planPage.getNumber() + 1);
        objectPageInfo.setPageSize(planPage.getSize());
        return ResponseBeanUtils.queryPageInfo(objectPageInfo);
    }

    @DeleteMapping("deleteByIdList")
    @Log("删除规划管理")
    public ResponseBean<Boolean> deleteByIdList(@RequestParam("idList") List<Long> idList) {
        service.deleteByIdList(idList);
        return ResponseBean.success(Boolean.TRUE);
    }

    @GetMapping(value = "zip")
    @Log("下载规划管理")
    public void downLoadZipFile(@RequestParam("idList") List<Long> idList, HttpServletResponse response) throws IOException {
        String zipName = "规划文件.zip";
        List<Plan> planList = service.listByIdList(idList);
        if (CollectionUtil.isEmpty(planList)) {
            return;
        }

        if (planList.size() >= 2) {
            zipName = planList.get(0).getFileName() + ".zip";
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
