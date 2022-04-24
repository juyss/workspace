package ${code.packageName}.entity;

import org.hibernate.validator.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.icepoint.framework.data.domain.LongBaseEntity;
import javax.persistence.*;



/**
* ${code.entityName}
*/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ${code.entityName} extends LongBasicEntity {
<#list code.attrs as attr>
<#--	<#if attr.optional != 1 && attr.javaType != "Integer" && attr.javaType != "Long" && attr.javaType != "Double" && attr.javaType != "Float" && attr.javaType != "Boolean">@NotEmpty(message = "${attr.title}不能为空")</#if>-->
<#--	<#if attr.maxlen &gt; 0>@Length(max = ${attr.maxlen}, message = "${attr.title}长度不能超过${attr.maxlen}")</#if>-->
		/**
		* ${attr.title}
		*/
    <#if attr.javaName != "id" && attr.javaName != "deleted" && attr.javaName != "create_user_id" && attr.javaName != "create_time" && attr.javaName != "update_user_id" && attr.javaName != "update_time">
		@Column(name = "${attr.jdbcName}")
		private ${attr.javaType} ${attr.javaName};
	</#if>
</#list>


}