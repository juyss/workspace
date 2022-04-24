package com.icepoint.framework.web.ui.service;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.entity.Dict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Deprecated
public interface UiDictService {
    List<TreeNode<Dict>> tree(String category, Long appId, Long ownerId);

    /**
     * 分页查询
     */
    Page page(Dict dict, Pageable pageable);

    /**
     * 新增数据字典项
     */
    Boolean save(Dict dict);

    /**
     * 根据id删除字典
     */
    Boolean delete(Long id);

    /**
     * 修改字典
     */
    Boolean update(Dict dict);

    Integer getState(Long appId, Long platformId, String categoryEn, String itemEn);
}
