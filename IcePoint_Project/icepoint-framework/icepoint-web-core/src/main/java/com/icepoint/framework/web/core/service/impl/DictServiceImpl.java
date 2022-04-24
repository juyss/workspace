package com.icepoint.framework.web.core.service.impl;


import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.data.util.TreeUtils;
import com.icepoint.framework.web.core.dao.DictRepository;
import com.icepoint.framework.web.core.entity.Dict;
import com.icepoint.framework.web.core.entity.QDict;
import com.icepoint.framework.web.core.message.DataNotFoundMessageException;
import com.icepoint.framework.web.core.service.DictService;
import com.icepoint.framework.web.core.util.MessageAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@Cacheable(DictService.CACHE_NAME)
@RequiredArgsConstructor
@Service
public class DictServiceImpl implements DictService {

    private final DictRepository repository;

    @Override
    public Page page(Dict dict, Pageable pageable) {
        Assert.isTrue(!ObjectUtils.isEmpty(dict), "参数有误");
        Assert.isTrue(!ObjectUtils.isEmpty(dict.getPlatformId()), "platformId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(dict.getAppId()), "appId不存在");
        return repository.findAll(Example.of(dict), pageable);
    }

    @Override
    public Boolean save(Dict dict) {
        Assert.isTrue(!ObjectUtils.isEmpty(dict), "新增数据为空");
        Assert.isTrue(!ObjectUtils.isEmpty(dict.getPlatformId()), "platformId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(dict.getAppId()), "appId不存在");
        Dict wrapper = new Dict();
        wrapper.setCategory(dict.getCategory());
        wrapper.setItem(dict.getItem());
        wrapper.setAppId(dict.getAppId());
        wrapper.setPlatformId(dict.getPlatformId());
        Optional<Dict> one = this.repository.findOne(Example.of(wrapper));
        Dict thisDict = one.orElse(null);
        Assert.isTrue(ObjectUtils.isEmpty(thisDict), "字典项名称重复");
        return repository.save(dict) != null;
    }

    /**
     * 为确保数据正确，控制层需要校验appId、platformId
     */
    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Boolean update(Dict dict) {
    	Assert.isTrue(!ObjectUtils.isEmpty(dict.getId()), "id不存在");
    	Assert.isTrue(!ObjectUtils.isEmpty(dict.getPlatformId()), "platformId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(dict.getAppId()), "appId不存在");

        Dict wrapper = new Dict();
        wrapper.setCategory(dict.getCategory());
        wrapper.setItem(dict.getItem());
        wrapper.setAppId(dict.getAppId());
        wrapper.setPlatformId(dict.getPlatformId());
        Optional<Dict> one = this.repository.findOne(Example.of(wrapper));
        Dict thisDict = one.orElse(null);
        Assert.isTrue(ObjectUtils.isEmpty(thisDict), "字典项不存在");
        Assert.isTrue(thisDict.getId() != dict.getId(), "字典项名称重复");

        return this.repository.save(dict) != null;
    }
	
    @Override
    public List<TreeNode<Dict>> tree(Long appId,Long platformId,String category) {
        MessageAssert.hasText(category);

        Dict root = repository.findOne(Example.of(Dict.builder()
        		.appId(appId)
        		.platformId(platformId)
                .category(category)
                .deleted(false)
                .build()))
                .orElseThrow(DataNotFoundMessageException::new);

        QDict qdict = QDict.dict;
        return TreeUtils.buildTreeStructure(root, parentId -> repository
                .findAll(qdict.parentId.eq(parentId), false));
    }

    @Override
    public Optional<Dict> findByCategoryAndValue(Long appId,Long platformId,String category, String value) {
        QDict qdict = QDict.dict;
        return repository.findOne(qdict.category.eq(category)
                .and(qdict.value.eq(value)).and(qdict.appId.eq(appId)).and(qdict.platformId.eq(platformId)), false);
    }

    @Override
    public Optional<Dict> findByCategoryAndItem(Long appId,Long platformId,String category, String item) {
        QDict qdict = QDict.dict;
        return repository.findOne(qdict.category.eq(category)
                .and(qdict.item.eq(item)).and(qdict.appId.eq(appId)).and(qdict.platformId.eq(platformId)), false);
    }


}
