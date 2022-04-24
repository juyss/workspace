package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@ApiModel( description = "班次列表返回结果")
public class Shifts {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 班次名称
     */
    @ApiModelProperty(value="班次名称")
    private String shiftsName;

    @ApiModelProperty(value="颜色编码")
    private String color;

    /**
     * 班次开始时间
     */
    @ApiModelProperty(value="班次开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 班次结束时间
     */
    @ApiModelProperty(value="班次结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer orderNo;

    /**
     * 备注/描述
     */
    @ApiModelProperty(value = "备注/描述")
    private String note;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    /**
     * 数据状态 0:正常  -1：删除
     */
    @ApiModelProperty(value = "数据状态")
    private Integer delFlag;


    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "维护公司id")
    private Integer companyId;

}
