package com.icepoint.framework.web.core.service;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.entity.Dict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
public interface DictService {

    String CACHE_NAME = "DictService";

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

    List<TreeNode<Dict>> tree(Long appId, Long platformId, @Nullable String category);

    Optional<Dict> findByCategoryAndValue(Long appId, Long platformId, String category, String value);

    Optional<Dict> findByCategoryAndItem(Long appId, Long platformId, String category, String item);
}
