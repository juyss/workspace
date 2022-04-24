package com.github.tangyi.webcast.api.dto.req;

import com.github.tangyi.webcast.api.model.ChannelMarquee;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.Min;

/**
 * 播放器自定义url跑马灯参数请求对象
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MarqueeParameterRequest {

    @ApiModelProperty(name = "channelId", value = "频道ID", required = true)
    private String channelId;

    @ApiModelProperty(name = "marquee", value = "跑马灯配置JSON字符串；username 跑马灯显示的内容 String" +
            "fontSize 跑马灯文字字体大小 整型" +
            "fontColor 跑马灯文字字体颜色，包含三个8位RGB颜色成分的数字；例如，0x000000为黑色 String" +
            "setting 跑马灯样式 1：自屏幕右方至左方一直滚动 2：屏幕内随机位置闪烁 3：自屏幕右方至左方一直滚动，渐隐渐现 4：上下15%的视频区域之间滚动 5：上下15%的视频区域随机闪现文字 6：样式 1 的增强型，加密效果更好，推荐使用 7：样式 2 的增强型，加密效果更好，推荐使用 整型\n" +
            "alpha 跑马灯文本透明度范围：0~1.0 float" +
            "show 是否显示跑马灯，“on” ：描边 “off”：不描边 String", required = true)
    private String marquee;

}
