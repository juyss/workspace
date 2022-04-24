package com.icepoint.base.web.info.service.impl;

import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.info.entity.Banner;
import com.icepoint.base.web.info.mapper.BannerMapper;
import com.icepoint.base.web.info.service.BannerService;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Service
public class BannerServiceImpl extends AntdPageService<BannerMapper, Banner, Long> implements BannerService {

    @Transactional
    @Override
    public int batchUpdate(List<Map<String, Object>> list) {
        return getRepository().batchUpdate(list);
    }

    @Override
    public Integer maxIndex(Banner banner) {
        Integer idx = getRepository().maxIndex(banner);
        if (null == idx || idx <= 0) {
            idx = 1;
        }
        return idx;
    }

    @Override
    public int up(Long id) {
        Banner thisBanner = this.get(id);
        Assert.notNull(thisBanner, "查询无数据: " + id);

        Banner banner = new Banner();
        banner.setOwnerId(thisBanner.getOwnerId());
        banner.setAppId(thisBanner.getAppId());
        banner.setIdx(thisBanner.getIdx() + 1);
        banner.setDeleted(0);
        List<Banner> list = this.list(Example.of(banner));
        if (list.size() > 0) {
            Banner uBanner = list.get(0);

            List<Map<String, Object>> upds = new ArrayList<>();
            Map<String, Object> mp = new HashMap<>();
            mp.put("id", uBanner.getId());
            mp.put("idx", uBanner.getIdx() - 1);
            upds.add(mp);

            mp = new HashMap<>();
            mp.put("id", thisBanner.getId());
            mp.put("idx", thisBanner.getIdx() + 1);
            upds.add(mp);

            return batchUpdate(upds);
        }

        return 0;
    }

    @Override
    public int down(Long id) {
        Banner thisBanner = this.get(id);
        Assert.notNull(thisBanner, "查询无数据: " + id);

        Banner banner = new Banner();
        banner.setOwnerId(thisBanner.getOwnerId());
        banner.setAppId(thisBanner.getAppId());
        banner.setIdx(thisBanner.getIdx() - 1);
        banner.setDeleted(0);
        List<Banner> list = this.list(Example.of(banner));
        if (list.size() > 0) {
            Banner uBanner = list.get(0);

            List<Map<String, Object>> upds = new ArrayList<>();
            Map<String, Object> mp = new HashMap<>();
            mp.put("id", uBanner.getId());
            mp.put("idx", uBanner.getIdx() + 1);
            upds.add(mp);

            mp = new HashMap<>();
            mp.put("id", thisBanner.getId());
            mp.put("idx", thisBanner.getIdx() - 1);
            upds.add(mp);

            return batchUpdate(upds);
        }

        return 0;
    }

}