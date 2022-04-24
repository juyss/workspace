package ${code.packageName}.entity;

import org.hibernate.validator.constraints.*;
import com.ucsmy.core.bean.BaseNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ${code.entityName} extends BaseNode {
<#list code.attrs as attr>
	<#if attr.optional != 1 && attr.javaType != "Integer" && attr.javaType != "Long" && attr.javaType != "Double" && attr.javaType != "Float" && attr.javaType != "Boolean">@NotEmpty(message = "${attr.title}不能为空")</#if>
	<#if attr.maxlen &gt; 0>@Length(max = ${attr.maxlen}, message = "${attr.title}长度不能超过${attr.maxlen}")</#if>
    <#if attr.javaName != "id">private ${attr.javaType} ${attr.javaName};// ${attr.des}</#if>
</#list>

    public ${code.entityName}(
<#assign x=0>
<#list code.attrs as attr>
	<#if x &gt; 0 && attr.queryField == 1>,</#if>
	<#if attr.queryField == 1>${attr.javaType} ${attr.javaName}
		<#assign x++>
	</#if>
</#list>
	){
<#list code.attrs as attr>
	    <#if attr.queryField == 1>set${attr.javaName?cap_first}(${attr.javaName});</#if>
</#list>
	}
	
    public ${code.entityName}(java.util.Map<String,Object> map){
<#list code.attrs as attr>
	    if (map.containsKey("${attr.javaName}")){
    		set${attr.javaName?cap_first}((${attr.javaType})map.get("${attr.javaName}"));
    	}
</#list>
	}
}