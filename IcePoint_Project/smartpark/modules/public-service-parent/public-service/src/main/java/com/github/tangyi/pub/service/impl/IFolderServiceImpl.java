package com.github.tangyi.pub.service.impl;

import com.github.tangyi.pub.mapper.FolderMapper;
import com.github.tangyi.pub.service.IFolderService;
import com.icepoint.base.api.domain.TreeEntity;
import com.icepoint.base.api.util.TreeEntityUtils;
import entity.TreeFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IFolderServiceImpl implements IFolderService {

    private final FolderMapper mapper;

    @Override
    public List<TreeEntity<TreeFolder>> folderService(TreeFolder treeFolder) {
        List<TreeFolder> folderList = mapper.folderService(treeFolder);
        if (CollectionUtils.isEmpty(folderList))
            return Collections.emptyList();

        return TreeEntityUtils.buildTreeStructure(folderList);

    }
}
