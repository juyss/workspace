package com.github.tangyi.webcast.api.dto.resp;
import lombok.Data;
import net.polyv.live.v1.entity.channel.operate.LiveChannelBasicInfoResponse;
/**
 * @author ck
 * @version 1.0
 * @description: TODO
 * @date 2021/5/6 11:11
 */
@Data
public class ChannelBasicInfo  extends LiveChannelBasicInfoResponse {
    private String scene;
    private String deptName;
}
