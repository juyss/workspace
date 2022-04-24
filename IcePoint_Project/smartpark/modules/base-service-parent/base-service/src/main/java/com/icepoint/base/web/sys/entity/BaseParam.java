package com.icepoint.base.web.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Deprecated // TODO: 无用的类，代码清理完毕后删除
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseParam implements Serializable {
    @NotEmpty(message = "请输入所有人")
    private Long ownerId;//
    @NotEmpty(message = "请输入应用编号")
    private Long appId;// 

    public BaseParam(java.util.Map<String, Object> map) {
        if (map.containsKey("ownerId")) {
            setOwnerId((Long) map.get("ownerId"));
        }
        if (map.containsKey("appId")) {
            setAppId((Long) map.get("appId"));
        }
    }
}
