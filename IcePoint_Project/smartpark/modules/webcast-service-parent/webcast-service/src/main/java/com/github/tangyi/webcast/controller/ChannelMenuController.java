package com.github.tangyi.webcast.controller;


import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.web.BaseController;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.webcast.service.ChannelMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.account.LiveAccountSwitchResponse;
import net.polyv.live.v1.entity.web.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 页面菜单
 *
 * @author Xiang Longfei
 * @date 2021/01/31
 */
@Slf4j
@Api("页面菜单")
@RestController
@RequestMapping(value = "/v1/webcast/menu")
public class ChannelMenuController extends BaseController {

    @Autowired
    private ChannelMenuService channelMenuService;

    @ApiOperation(value = "添加一个频道菜单", notes = "添加一个频道菜单")
    @PostMapping("channelMenu")
    @Log("添加一个频道菜单")
    public ResponseBean<LiveAddChannelMenuResponse> addChannelMenu(
            @ApiParam(name = "liveAddChannelMenuRequest", value = "添加频道菜单请求实体", required = true) @RequestBody LiveAddChannelMenuRequest liveAddChannelMenuRequest) {
        return new ResponseBean<>(channelMenuService.addChannelMenu(liveAddChannelMenuRequest));
    }

    @ApiOperation(value = "设置直播频道的菜单的顺序", notes = "设置直播频道的菜单的顺序")
    @PutMapping("channelMenu")
    @Log("设置直播频道的菜单的顺序")
    public ResponseBean<Boolean> updateChannelMenuSort(
            @ApiParam(name = "liveUpdateChannelMenuSortRequest", value = "设置频道菜单排序请求实体") @RequestBody LiveUpdateChannelMenuSortRequest liveUpdateChannelMenuSortRequest) {
        if (!Boolean.TRUE.equals(channelMenuService.updateChannelMenuSort(liveUpdateChannelMenuSortRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "更新指定菜单id的频道菜单信息", notes = "更新指定菜单id的频道菜单信息")
    @PutMapping("channelMenuInfo")
    @Log("更新指定菜单id的频道菜单信息")
    public ResponseBean<Boolean> updateChannelMenuInfo(
            @ApiParam(name = "liveUpdateChannelMenuInfoRequest", value = "更新指定菜单id的频道菜单信息请求实体", required = true) @RequestBody LiveUpdateChannelMenuInfoRequest liveUpdateChannelMenuInfoRequest) {
        if (!Boolean.TRUE.equals(channelMenuService.updateChannelMenuInfo(liveUpdateChannelMenuInfoRequest))) {
            throw new CommonException("更新失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "删除指定的频道菜单，支持批量", notes = "删除指定的频道菜单，支持批量")
    @DeleteMapping("channelMenu")
    @Log("删除指定的频道菜单，支持批量")
    public ResponseBean<Boolean> deleteChannelMenu(
            @ApiParam(name = "menuIds", value = "菜单id，指定多个以英文逗号,分隔", required = true) String menuIds) {
        /*保利威SDK BUG，它接口返回false实际删除成功
        if (!Boolean.TRUE.equals(channelMenuService.deleteChannelMenu(menuIds))) {
            throw new CommonException("删除失败");
        }*/
        channelMenuService.deleteChannelMenu(menuIds);
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "可以开启或关闭咨询提问功能菜单", notes = "可以开启或关闭咨询提问功能菜单")
    @PutMapping("consulting")
    @Log("可以开启或关闭咨询提问功能菜单")
    public ResponseBean<Boolean> setConsultingEnabled(
            @ApiParam(name = "liveSetConsultingEnabledRequest", value = "设置提问功能显示开关请求实体", required = true) @RequestBody LiveSetConsultingEnabledRequest liveSetConsultingEnabledRequest) {
        if (!Boolean.TRUE.equals(channelMenuService.setConsultingEnabled(liveSetConsultingEnabledRequest))) {
            throw new CommonException("设置失败");
        }
        return new ResponseBean<>(Boolean.TRUE);
    }

    @ApiOperation(value = "查询频道图文内容列表", notes = "查询频道图文内容列表")
    @PostMapping("channelImageText")
    @Log("查询频道图文内容列表")
    public ResponseBean<LiveGetChannelImageTextResponse> getChannelImageText(
            @ApiParam(name = "liveGetChannelImageTextRequest", value = "查询频道图文内容列表请求实体", required = true) @RequestBody LiveGetChannelImageTextRequest liveGetChannelImageTextRequest) {
        return new ResponseBean<>(channelMenuService.getChannelImageText(liveGetChannelImageTextRequest));
    }

}
