package com.juyss.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: BaseEntity
 * @Desc: 实体类基类
 * @package com.juyss.entity
 * @project manager
 * @date 2021/1/12 17:38
 */
@Data
public class BaseEntity {
    @JSONField(serialize = false)
    @TableField(exist = false)
    private int page = 1;

    @JSONField(serialize = false)
    @TableField(exist = false)
    private int limit = 10;

    /**
     * 数据权限：用户id
     */
    @TableField(exist = false)
    private List<String> createIds;
}
