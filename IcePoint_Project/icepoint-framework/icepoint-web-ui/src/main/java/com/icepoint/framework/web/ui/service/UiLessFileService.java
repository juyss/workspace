package com.icepoint.framework.web.ui.service;

import com.icepoint.framework.web.ui.entity.UiLessFile;

public interface UiLessFileService {

    /**
     * 新增less文件
     */
    boolean save(UiLessFile uiLessFile);

    /**
     * 删除文件
     */
    Boolean delete(Long id);

    /**
     * 修改
     */
    Boolean update(UiLessFile uiLessFile);
}
