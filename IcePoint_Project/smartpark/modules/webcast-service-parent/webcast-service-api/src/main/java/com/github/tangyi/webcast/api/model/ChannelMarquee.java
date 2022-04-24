package com.github.tangyi.webcast.api.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 直播频道跑马灯的配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelMarquee extends BaseEntity<ChannelMarquee> {


    private String channelId;//频道ID

    private String marquee;//跑马灯配置JSON字符串

}
