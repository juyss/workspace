package com.icepoint.base.web.resource.service.complex;

import com.github.tangyi.common.core.constant.ApiMsg;
import com.icepoint.base.api.util.StreamUtils;
import com.icepoint.base.api.domain.TreeEntity;
import com.icepoint.base.api.util.Condition;
import com.icepoint.base.api.util.TreeEntityUtils;
import com.icepoint.base.api.entity.Layer;
import com.icepoint.base.api.entity.Resource;
import com.icepoint.base.web.resource.service.simple.LayerService;
import com.icepoint.base.web.resource.service.simple.ResourceService;
import com.icepoint.base.web.resource.util.ResourceUtils;
import com.icepoint.base.web.sys.entity.BaseParam;
import com.icepoint.base.web.sys.entity.Dict;
import com.icepoint.base.web.sys.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResourceLayerServiceImpl implements ResourceLayerService {

    private final ResourceService resourceService;
    private final LayerService layerService;
    private final DictService dictService;

    @Override
    public List<Layer> getLayerConfig(BaseParam para) {
        Layer lyr = new Layer();
        lyr.setOwnerId(para.getOwnerId());
        lyr.setAppId(para.getAppId());
        return layerService.list(Example.of(lyr));

//        Resource resource = new Resource();
//        resource.setOwnerId(para.getOwnerId());
//        resource.setAppId(para.getAppId());
//        //获取资源描述
//        List<Resource> resources = resourceService.list(Example.of(resource));
//
//        StreamUtils.parallelStreamIfAvailable(layers)
//                .sorted()
//                .forEach(layer ->
//                        StreamUtils.parallelStreamIfAvailable(resources)
//                                .filter(it -> Objects.equals(it.getId(), layer.getResId()))
//                                .findAny()
//                                .ifPresent(layer::setResource)
//                );
//        return layers;
    }

    @Override
    public Boolean putLayerConfig(List<Map<String, Object>> setting) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer layerTop(Long resId) {
        Layer lyr = new Layer();
        lyr.setResId(resId);
        List<Layer> list = layerService.list(Example.of(lyr));
        Condition.isEmpty(list).throwException(ApiMsg.NOT_FOUND);

        Layer thisLyr = list.get(0);

        lyr = new Layer();
        lyr.setOwnerId(thisLyr.getOwnerId());
        lyr.setAppId(thisLyr.getAppId());
        list = layerService.list(Example.of(lyr));

        List<Map<String, Object>> upds = StreamUtils.parallelStreamIfAvailable(list)
                .filter(item -> item.getIdx() > thisLyr.getIdx())
                .map(item -> {
                    Map<String, Object> mp = new HashMap<>();
                    mp.put("id", item.getId());
                    mp.put("idx", item.getIdx() - 1);
                    return mp;
                }).collect(Collectors.toList());

        Map<String, Object> mp = new HashMap<>();
        mp.put("id", thisLyr.getId());
        mp.put("idx", list.size() - 1);
        upds.add(mp);

        return layerService.batchUpdate(upds);
    }

    @Override
    public Integer layerBottom(Long resId) {
        Layer lyr = new Layer();
        lyr.setResId(resId);
        List<Layer> list = layerService.list(Example.of(lyr));
        Condition.isEmpty(list).throwException(ApiMsg.NOT_FOUND);
        Layer thisLyr = list.get(0);

        lyr = new Layer();
        lyr.setOwnerId(thisLyr.getOwnerId());
        lyr.setAppId(thisLyr.getAppId());
        list = layerService.list(Example.of(lyr));

        List<Map<String, Object>> upds = StreamUtils.parallelStreamIfAvailable(list)
                .filter(item -> item.getIdx() > thisLyr.getIdx())
                .map(item -> {
                    Map<String, Object> mp = new HashMap<>();
                    mp.put("id", item.getId());
                    mp.put("idx", item.getIdx() + 1);
                    return mp;
                }).collect(Collectors.toList());

        Map<String, Object> mp = new HashMap<>();
        mp.put("id", thisLyr.getId());
        mp.put("idx", 0);
        upds.add(mp);

        return layerService.batchUpdate(upds);
    }

    @Override
    public Integer layerUp(Long resId) {
        Layer lyr = new Layer();
        lyr.setResId(resId);
        List<Layer> list = layerService.list(Example.of(lyr));
        Condition.isEmpty(list).throwException(ApiMsg.NOT_FOUND);
        Layer thisLyr = list.get(0);

        lyr = new Layer();
        lyr.setOwnerId(thisLyr.getOwnerId());
        lyr.setAppId(thisLyr.getAppId());
        lyr.setIdx(thisLyr.getIdx() + 1);
        list = layerService.list(Example.of(lyr));
        if (list.size() > 0) {
            Layer uLyr = list.get(0);

            List<Map<String, Object>> upds = new ArrayList<>();
            Map<String, Object> mp = new HashMap<>();
            mp.put("id", uLyr.getId());
            mp.put("idx", uLyr.getIdx() - 1);
            upds.add(mp);

            mp = new HashMap<>();
            mp.put("id", thisLyr.getId());
            mp.put("idx", thisLyr.getIdx() + 1);
            upds.add(mp);

            return layerService.batchUpdate(upds);
        } else {
            return 0;
        }
    }

    @Override
    public Integer layerDown(Long resId) {
        Layer lyr = new Layer();
        lyr.setResId(resId);
        List<Layer> list = layerService.list(Example.of(lyr));
        Condition.isEmpty(list).throwException(ApiMsg.NOT_FOUND);
        Layer thisLyr = list.get(0);

        lyr = new Layer();
        lyr.setOwnerId(thisLyr.getOwnerId());
        lyr.setAppId(thisLyr.getAppId());
        lyr.setIdx(thisLyr.getIdx() - 1);
        list = layerService.list(Example.of(lyr));
        if (list.size() > 0) {
            Layer uLyr = list.get(0);

            List<Map<String, Object>> upds = new ArrayList<>();
            Map<String, Object> mp = new HashMap<>();
            mp.put("id", uLyr.getId());
            mp.put("idx", uLyr.getIdx() + 1);
            upds.add(mp);

            mp = new HashMap<>();
            mp.put("id", thisLyr.getId());
            mp.put("idx", thisLyr.getIdx() - 1);
            upds.add(mp);

            return layerService.batchUpdate(upds);
        } else {
            return 0;
        }
    }

    @Override
    public Dict getResourceDictionary(String key) {
        Dict query = Dict.builder()
                .categoryEn(ResourceUtils.getDictionaryCategory())
                .itemEn(key)
                .deleted(0)
                .build();
        return dictService.get(Example.of(query));
    }

    @Override
    public List<TreeEntity<Resource>> getResourceTree() {
        //获取资源描述
        List<Resource> resources = resourceService.list(Example.of(Resource.builder().bizCode("RES_LIST").build()));
        if (CollectionUtils.isEmpty(resources))
            return null;

        // 设置ResourceKey
        StreamUtils.parallelStreamIfAvailable(resources).forEach(
                resource -> resource.setResKey(ResourceUtils.getResourceKey(resource.getResCode()))
        );

        //查询图层列表
        //获取图层属性
        List<Layer> layers = layerService.list();

        //将图层是否显示整合到资源列表中
        StreamUtils.parallelStreamIfAvailable(resources).forEach(res ->
                StreamUtils.parallelStreamIfAvailable(layers)
                        .filter(layer -> Objects.equals(layer.getResId(), res.getId()))
                        .findAny()
                        .map(Layer::getShow)
                        .ifPresent(res::setShow)
        );

        //生成树状结构
        return TreeEntityUtils.buildTreeStructure(resources);
    }
}
