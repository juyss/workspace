package com.github.tangyi.pub.service;

import com.icepoint.base.api.domain.TreeEntity;
import entity.TreeFolder;

import java.util.List;

public interface IFolderService {

    List<TreeEntity<TreeFolder>> folderService(TreeFolder treeFolder);
}
