package com.github.tangyi.webcast.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 异步裁剪直播录制文件请求实体
 *
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("异步裁剪直播录制文件请求实体")
public class LiveClipChannelVideoAsyncRequest extends LiveCommonRequest {

    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;

    /**
     * 要裁剪的录制视频文件ID
     */
    @ApiModelProperty(name = "fileId", value = "要裁剪的录制视频文件ID", required = true)
    @NotNull(message = "属性fileId不能为空")
    private String fileId;

    /**
     *
     */
    @ApiModelProperty(name = "deleteTimeFrame", value = "要裁剪移除的时间区间\n" +
            "误差说明：\n" +
            "（1）裁剪区间存在一定的时间误差；\n" +
            "（2）误差原因：回放默认为m3u8格式裁剪，由于m3u8的视频裁剪是精确到每片ts, 所以裁剪时会判断裁剪要移除的时间区间所包含的ts片，进行裁剪移除，如需更精准的裁剪，可联系保利威客服人员开通mp4裁剪；\n" +
            "格式要求：\n" +
            "（1）start、end值是单位为秒的数值，格式为：[{\"start\":xx, \"end\":xx},{\"start\":xx, \"end\":xx}]；\n" +
            "（2）多个区间请按照时间顺序从小到大排列；\n" +
            "（3）时间区间不允许重叠；\n" +
            "（4）时间区间不得超过100个限制；\n" +
            "【示例：要裁剪移除的是第1s到第14s, 以及第25s到第30s的区间，则传入的字符串为 [{\"start\":1, \"end\":14},{\"start\":25, \"end\":30}] 】", required = true)
    private String deleteTimeFrame;

    /**
     * 裁剪成功或失败回调的url（回调详细请查看文档最后 " 回调说明 " 一栏）
     */
    @ApiModelProperty(name = "callbackUrl", value = "裁剪成功或失败回调的url（回调详细请查看文档最后 \" 回调说明 \" 一栏）", required = false)
    private String callbackUrl;

    /**
     * 传入Y，自动转存到对应点播分类下(直播回放-频道号-场次)
     */
    @ApiModelProperty(name = "autoConvert", value = "是否自动转存回放（Y:是，N:否）不传默认为N", required = false)
    private String autoConvert;

    /**
     * 合并后的视频的文件名
     */
    @ApiModelProperty(name = "fileName", value = "裁剪后的视频的文件名", required = false)
    private String fileName;

}