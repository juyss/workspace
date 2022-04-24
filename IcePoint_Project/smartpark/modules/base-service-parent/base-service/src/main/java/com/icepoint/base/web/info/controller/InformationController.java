package com.icepoint.base.web.info.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.icepoint.base.web.sys.entity.File;
import com.icepoint.base.bean.ItemPicture;
import com.icepoint.base.bean.StaticParam;
import com.icepoint.base.util.UploadManager;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.web.info.entity.Information;
import com.icepoint.base.web.info.entity.InformationAddDto;
import com.icepoint.base.web.info.service.InformationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@RestController
@RequestMapping("info/inform")
public class InformationController extends CrudController<InformationService, Information, Long> {

    @PostMapping(value = "addEx")
    @ResponseBody
    public ResponseBean<Long> add(@RequestBody InformationAddDto entity, @RequestParam(value = "pics") MultipartFile[] pics, @RequestParam(value = "videos") MultipartFile[] videos) {
        //图片处理如何做

        Information sysInformation = new Information(entity);
        List<ItemPicture> lt = UploadManager.uploadPic(pics, StaticParam.SOURCE_IMG, entity.getAuthorCustId());
        if (lt != null && lt.size() > 0) {
            sysInformation.setTopicPhoto(lt.get(0).getUrl());
        }
        List<File> videlt = UploadManager.uploadFileLogs(videos, StaticParam.SOURCE_VIDEO, entity.getAuthorCustId());
        if (videlt != null && videlt.size() > 0) {
            sysInformation.setVideo(videlt.get(0).getFileKey());
            sysInformation.setDuration(videlt.get(0).getDuration());
        }

        return ResponseBeanUtils.addNewData(service.add(sysInformation));
    }
}
