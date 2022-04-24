package com.juyss.controller;

import com.juyss.common.utils.DataResult;
import com.juyss.service.HomeService;
import com.juyss.service.HttpSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: HomeController
 * @Desc:
 * @package com.juyss.controller
 * @project manager
 * @date 2021/1/21 14:32
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "首页数据")
public class HomeController {
    @Resource
    private HomeService homeService;
    @Resource
    private HttpSessionService httpSessionService;

    @GetMapping("/home")
    @ApiOperation(value = "获取首页数据接口")
    public DataResult getHomeInfo() {
        //通过access_token拿userId
        String userId = httpSessionService.getCurrentUserId();
        DataResult result = DataResult.success();
        result.setData(homeService.getHomeInfo(userId));
        return result;
    }
}
