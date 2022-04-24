package com.icepoint.framework.web.ui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.data.util.TreeUtils;
import com.icepoint.framework.web.core.entity.Dict;
import com.icepoint.framework.web.ui.mapper.UiDictMapper;
import com.icepoint.framework.web.ui.service.UiDictService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
@Service
public class UiDictServiceImpl implements UiDictService {

    @Resource
    private UiDictMapper mapper;

    @Override
    public List<TreeNode<Dict>> tree(String category, Long appId, Long ownerId) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getAppId, appId);
        wrapper.eq(Dict::getOwnerId, ownerId);
        if (!ObjectUtils.isEmpty(category)) {
            wrapper.eq(Dict::getCategory, category);
        }
        List<Dict> all = mapper.findAll(wrapper);
        return TreeUtils.buildTreeStructure(all);
    }

    @Override
    public Page page(Dict dict, Pageable pageable) {
        Assert.isTrue(!ObjectUtils.isEmpty(dict), "参数有误");
        Assert.isTrue(!ObjectUtils.isEmpty(dict.getOwnerId()), "ownerId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(dict.getAppId()), "appId不存在");
        Map<String, Object> map = MapUtils.objectToLineMap(dict);
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        return mapper.findAll(wrapper, pageable);
    }

    @Override
    public Boolean save(Dict dict) {
        Assert.isTrue(!ObjectUtils.isEmpty(dict), "新增数据为空");
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getCategory, dict.getCategory());
        wrapper.eq(Dict::getItem, dict.getItem());
        Optional<Dict> one = this.mapper.findOne(wrapper);
        Dict thisDict = one.orElse(null);
        Assert.isTrue(ObjectUtils.isEmpty(thisDict), "字典项名称重复");
        return mapper.insert(dict) > 0;
    }

    @Override
    public Boolean delete(Long id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Boolean update(Dict dict) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getCategory, dict.getCategory());
        wrapper.eq(Dict::getItem, dict.getItem());
        List<Dict> all = this.mapper.findAll(wrapper);
        Assert.isTrue(all.size() < 1, "字典项名称重复");
        return this.mapper.update(dict) > 0;
    }

    @Override
	public Integer getState(Long appId,Long platformId,String categoryEn,String itemEn){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("appId", appId);
			map.put("platformId", platformId);
			map.put("category", categoryEn);
			map.put("item", itemEn);
			List<Dict> list = this.mapper.findAllByMap(map);
			if (null == list || list.size() != 1){
				return Integer.MIN_VALUE;
			}
			Dict obj = list.get(0);
			return Integer.valueOf(obj.getValue());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return Integer.MIN_VALUE;		
	}
}
