package com.icepoint.base.web.info.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.icepoint.base.bean.ItemPicture;
import com.icepoint.base.bean.StaticParam;
import com.icepoint.base.util.UploadManager;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.config.web.ExcludedHandler;
import com.icepoint.base.web.info.entity.Banner;
import com.icepoint.base.web.info.service.BannerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


//@RestController
@RequestMapping("info/banner")
@ExcludedHandler(methodName = "add", parameterTypes = BaseEntity.class)
public class BannerController extends CrudController<BannerService, Banner, Long> {

    //    @PostMapping("add")
    public ResponseBean<Long> add(
            @RequestBody Banner entity,
            @RequestParam(value = "pics") MultipartFile[] pics,
            @RequestParam(value = "videos", required = false) MultipartFile[] videos) {
        Banner mp = new Banner();
        mp.setBizCode(entity.getBizCode());
        mp.setOwnerId(entity.getOwnerId());
        mp.setAppId(entity.getAppId());
        entity.setIdx(service.maxIndex(mp) + 1);//序号

        List<ItemPicture> lt = UploadManager.uploadPic(pics, StaticParam.SOURCE_IMG, entity.getCreateUser());
        if (lt != null && lt.size() > 0) {
            entity.setFileUrl(lt.get(0).getUrl());
        }

        return ResponseBeanUtils.addNewData(service.add(entity));
    }

    @PostMapping("up/{id}")
    public ResponseBean<Boolean> up(@PathVariable(value = "id") Long id) {
        return ResponseBeanUtils.operate(service.up(id) > 0);
    }

    @PostMapping("down/{id}")
    public ResponseBean<Boolean> down(@PathVariable(value = "id") Long id) {
        return ResponseBeanUtils.operate(service.down(id) > 0);
    }
}
