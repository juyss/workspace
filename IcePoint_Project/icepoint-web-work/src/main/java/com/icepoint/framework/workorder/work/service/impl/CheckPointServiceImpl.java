package com.icepoint.framework.workorder.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.system.dao.AssetMapper;
import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.web.system.service.AssetService;
import com.icepoint.framework.workorder.work.dao.CheckPointMapper;
import com.icepoint.framework.workorder.work.entity.CheckPoint;
import com.icepoint.framework.workorder.work.service.CheckPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.nio.file.OpenOption;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
@RequiredArgsConstructor
public class CheckPointServiceImpl implements CheckPointService {
    private final AssetService assetService;

    private final CheckPointMapper mapper;

    private final AssetMapper assetMapper;


    @Override
    public CheckPoint findCheckPoints(Long assetDefId, Long objId) {
        LambdaQueryWrapper<CheckPoint> lambdaQueryWrapper = Wrappers.lambdaQuery(CheckPoint.class);
        lambdaQueryWrapper.eq(CheckPoint::getObjAssetDefId, assetDefId);
        lambdaQueryWrapper.eq(CheckPoint::getObjId, objId);
        Optional<CheckPoint> checkPointOptional = mapper.findOne(lambdaQueryWrapper);
        if (checkPointOptional.isPresent()) {
            return checkPointOptional.get();
        }
        Collection<AssetDefine> list = new ArrayList();
        list = assetService.assetDefUpTree(assetDefId, list);
        //根据资产向上查询所有资产
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }
        for (AssetDefine assetDefine : list) {
            List<Map<String, Object>> list1 = assetService.objList(assetDefine.getId(), null, null);
            List<Object> id = list1.stream().map(item -> item.get("id")).collect(Collectors.toList());
            lambdaQueryWrapper.clear();
            lambdaQueryWrapper.eq(CheckPoint::getObjAssetDefId,assetDefine.getId());
            lambdaQueryWrapper.in(CheckPoint::getObjId);
            lambdaQueryWrapper.orderByAsc(CheckPoint::getObjId);
            List<CheckPoint> all = mapper.findAll(lambdaQueryWrapper);
            return Optional.ofNullable(all).orElseThrow(()->new IllegalArgumentException("未找到打卡点")).get(0);
        }
        return null;
    }

    @Override
    public Page<CheckPoint> pageList(CheckPoint checkPoint, Pageable pageable) {
        Map<String, Object> map = MapUtils.objectToLineMap(checkPoint);
        QueryWrapper<CheckPoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map);
        return mapper.findAll(queryWrapper, pageable);
    }

    @Override
    public Boolean updateCheckPoint(CheckPoint checkPoint) {
        CheckPoint entity = Optional.ofNullable(checkPoint).orElseThrow(() -> new IllegalArgumentException(""));
        return mapper.update(entity) > 0;
    }

    @Override
    public Boolean deleteCheckPoint(Long id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Boolean addCheckPoint(CheckPoint checkPoint) {
        //获取资产id
        Long objId = checkPoint.getObjId();
        LambdaQueryWrapper<CheckPoint> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CheckPoint::getObjId, objId);
        Optional<CheckPoint> one = mapper.findOne(lambdaQueryWrapper);
        if (!one.isPresent()) {
            return mapper.insert(checkPoint) > 0;
        }
        throw new IllegalArgumentException("此对象已绑定打卡点");
    }

    @Override
    public List<AssetDefine> queryCheckPointByAsset() {
        List<CheckPoint> all = mapper.findAll();
        if (ObjectUtils.isEmpty(all)) {
            return assetMapper.findAll();
        }
        List<Long> assetObjIds = all.stream().map(CheckPoint::getObjAssetDefId).collect(Collectors.toList());
        LambdaQueryWrapper<AssetDefine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.notIn(AssetDefine::getId, assetObjIds);
        return assetMapper.findAll(queryWrapper);
    }

    @Override
    public List<CheckPoint> queryCheckPointByAssetId(Long assetId) {
        Optional.ofNullable(assetId).orElseThrow(() -> new IllegalArgumentException("资产id不能为空"));
        LambdaQueryWrapper<CheckPoint> lambdaQueryWrapper = Wrappers.lambdaQuery(CheckPoint.class);
        lambdaQueryWrapper.eq(CheckPoint::getObjAssetDefId, assetId);
        return mapper.findAll(lambdaQueryWrapper);
    }

}
