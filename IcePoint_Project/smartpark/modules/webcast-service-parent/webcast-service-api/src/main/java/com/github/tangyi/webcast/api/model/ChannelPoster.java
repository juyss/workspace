package com.github.tangyi.webcast.api.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 直播频道-海报关系
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChannelPoster extends BaseEntity<ChannelPoster> {


    private String channelId;//频道ID

    private String posterUrl;//海报URL

}
