package com.icepoint.base.web.info.service;

import com.icepoint.base.web.basic.service.CrudService;
import com.icepoint.base.web.info.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService extends CrudService<Banner, Long> {

    int batchUpdate(List<Map<String, Object>> list);

    Integer maxIndex(Banner banner);

    int up(Long id);

    int down(Long id);
}
