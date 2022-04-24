package com.icepoint.base.web.sys.service;

import com.icepoint.base.api.domain.TreeEntity;
import com.icepoint.base.web.basic.service.CrudService;
import com.icepoint.base.web.sys.entity.Dict;
import com.icepoint.base.web.sys.entity.DictListExDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS)
public interface DictService extends CrudService<Dict, Long> {

    /**
     * 数据字典树
     * 给定字典类，返回字典及其下级所有树节点
     */
    List<TreeEntity<Dict>> tree(DictListExDto param);

    String getState(Long ownerId, Long appId, String categoryEn, String itemEn);
}
