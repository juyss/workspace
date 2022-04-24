package com.icepoint.framework.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import com.icepoint.framework.data.domain.ParentIdHierarchy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 资产定义表(assets_define)实体类
 *
 * @author juyss
 * @since 2021-07-07 09:36:55
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_asset_define")
@Table(name = "sys_asset_define")
public class AssetDefine extends LongStdEntity implements ParentIdHierarchy<Long>, Ordered {

	/**
	 * 用户ids，多个id用逗号分隔
	 */
	@Transient
	@TableField(exist = false)
//	@ApiModelProperty(value = "")
	private String ids;

	/**
	 * 资产类型
	 */
	@Column(name = "`asset_type`")
	private Integer assetType;

	/**
	 * 资产编码
	 */
	@Column(name = "`asset_code`")
	private String assetCode;

	/**
	 * 资产名称
	 */
	@Column(name = "`asset_name`")
	private String assetName;

	/**
	 * 交易属性
	 */
	@Column(name = "`trading_property`")
	private Integer tradingProperty;

	/**
	 * 资源编码
	 */
	@Column(name = "`resource_id`")
	private Long resourceId;

	/**
	 * 资源名称字段
	 */
	@Column(name = "`resource_name_field`")
	private String resourceNameField;

	/**
	 * 资源编码字段
	 */
	@Column(name = "`resource_code_field`")
	private String resourceCodeField;

	/**
	 * 父资源编码字段
	 */
	@Column(name = "`parent_resource_code_field`")
	private String parentResourceCodeField;

	/**
	 * 级别
	 */
	@Column(name = "`level`")
	private Integer level;

	/**
	 * 父节点
	 */
	@Column(name = "`parent_id`")
	private Long parentId;

	@Override
	public int getOrder() {
		return this.getLevel();
	}
}
