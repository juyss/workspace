package com.juyss.service;

import com.juyss.vo.resp.HomeRespVO;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: HomeService
 * @Desc:
 * @package com.juyss.service
 * @project manager
 * @date 2021/1/20 16:28
 */
public interface HomeService {

    /**
     * 获取首页信息
     *
     * @param userId userId
     * @return HomeRespVO
     */
    HomeRespVO getHomeInfo(String userId);
}