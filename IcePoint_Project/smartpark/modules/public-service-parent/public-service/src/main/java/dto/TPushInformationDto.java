package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_push_information 信息推送列表
 *
 * @author jy
 * @since 2020/11/03
 */
@Data
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "TPushInformationDto", description = "推送列表返回结果")
public class TPushInformationDto implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	protected Long id;

	/**
	 * title 信息推送标题
	 */
	@ApiModelProperty(value = "信息推送标题")
	protected String title;

	/**
	 * plate_id 板块id
	 */
	@ApiModelProperty(value = "板块id")
	protected Long plateId;

	/**
	 * plate_name 模块名称
	 */
	@ApiModelProperty(value = "模块名称")
	protected String plateName;

	/**
	 * pushChannelId 推送渠道id
	 */
	@ApiModelProperty(value = "推送渠道id")
	protected String pushChannelId;

	/**
	 * push_channel_name 推送渠道名称:如门户网站、电子大屏
	 */
	@ApiModelProperty(value = "推送渠道名称:如门户网站、电子大屏")
	protected String pushChannelName;

	/**
	 * is_push 是否自动发布：0否，1是
	 */
	@ApiModelProperty(value = "是否自动发布：0否，1是")
	protected Integer isPush;

	/**
	 * regular_time 定时发布时间
	 */
	@ApiModelProperty(value = "定时发布时间",dataType = "Data")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date regularTime;

	/**
	 * push_time 发布时间
	 */
	@ApiModelProperty(value = "发布时间",dataType = "Data")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date pushTime;

	/**
	 * status 审核状态：0未审核 1审核通过,未发布 2审核失败 3已发布
	 */
	@ApiModelProperty(value = "审核状态：0未审核 1审核通过,未发布 2审核失败 3已发布")
	protected Integer status;

	/**
	 * push_content 推送内容
	 */
	@ApiModelProperty(value = "推送内容")
	protected String pushContent;

	/**
	 * policy_push_department 政策发布部门
	 */
	@ApiModelProperty(value = "政策发布部门")
	protected String policyPushDepartment;

	/**
	 * policy_number 政策文号
	 */
	@ApiModelProperty(value = "政策文号")
	protected String policyNumber;

	/**
	 * policy_title 政策名称
	 */
	@ApiModelProperty(value = "政策名称")
	protected String policyTitle;

	/**
	 * theme_image 主题图片
	 */
	@ApiModelProperty(value = "主题图片")
	protected String themeImage;

}
