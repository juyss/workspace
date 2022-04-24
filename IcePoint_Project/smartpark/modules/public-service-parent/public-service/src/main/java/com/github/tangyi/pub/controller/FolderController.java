package com.github.tangyi.pub.controller;


import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.pub.service.IFolderService;
import com.icepoint.base.api.domain.TreeEntity;
import entity.TreeFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/treefolder")
@RequiredArgsConstructor
public class FolderController {

    private final IFolderService folderService;

    @PostMapping("/folder")
    @Log("查询文件夹树")
    public ResponseBean<List<TreeEntity<TreeFolder>>> getTreeFolder(@RequestBody TreeFolder treeFolder){

        return ResponseBeanUtils.queryMany(folderService.folderService(treeFolder));
    }

}
