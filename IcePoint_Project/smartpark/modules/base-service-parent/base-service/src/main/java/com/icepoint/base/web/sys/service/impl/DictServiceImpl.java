package com.icepoint.base.web.sys.service.impl;

import com.icepoint.base.api.domain.TreeEntity;
import com.icepoint.base.api.util.TreeEntityUtils;
import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.sys.entity.Dict;
import com.icepoint.base.web.sys.entity.DictListExDto;
import com.icepoint.base.web.sys.mapper.DictMapper;
import com.icepoint.base.web.sys.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends AntdPageService<DictMapper, Dict, Long> implements DictService {

    @Override
    public List<TreeEntity<Dict>> tree(DictListExDto param) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(param, dict);
        List<Dict> rslt2 = list(Example.of(dict));
        return TreeEntityUtils.buildTreeStructure(rslt2);
    }

    @Override
    public String getState(Long ownerId, Long appId, String categoryEn, String itemEn) {
        Dict dict = new Dict();
        dict.setCategoryEn(categoryEn);
        dict.setItemEn(itemEn);
        if (null != ownerId && ownerId > 0) {
            dict.setOwnerId(ownerId);
        }
        if (null != appId && appId > 0) {
            dict.setAppId(appId);
        }
        dict.setDeleted(0);

        List<Dict> list = list(Example.of(dict));
        if (list.size() != 1) {
            return null;
        }
        dict = list.get(0);
        return dict.getCval();
    }

}

