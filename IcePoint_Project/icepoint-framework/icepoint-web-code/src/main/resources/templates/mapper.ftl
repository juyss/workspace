<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${code.packageName}.dao.${code.entityName}Dao">

    <sql id="lookup">
        SELECT
        <#list code.attrs as attr>
            a.${attr.jdbcName} as '${attr.javaName}'<#if attr_has_next>,</#if>
        </#list>
        FROM ${code.dataTable} a
    </sql>

    <insert id="save" parameterType="${code.packageName}.entity.${code.entityName}">
        insert into ${code.dataTable}(
        <#list code.attrs as attr>
        <if test="attr.jdbcName != null">
            ${attr.jdbcName}<#if attr_has_next>,</#if>
            </if>
            </#list>
            )
            values(
            <#list code.attrs as attr>
            <if test="attr.jdbcName != null">
                {${attr.javaName}}<#if attr_has_next>,</#if>
                </if>
                </#list>
                )
    </insert>

    <update id="update" parameterType="${code.packageName}.entity.${code.entityName}">
        update ${code.dataTable}
        set
        <#list code.attrs as attr>
        <if test="attr.jdbcName != null && attr.jdbcName != 'id'">
            ${attr.jdbcName} = {${attr.javaName}}<#if attr_has_next>,</#if>
            </if>
            </#list>
            where id = {id}
    </update>

    <delete id="delete" parameterType="Long">
        delete from ${code.dataTable}
        where id = {id}
    </delete>

    <select id="findById" parameterType="Long" resultType="${code.packageName}.entity.${code.entityName}">
        <include refid="lookup"/>
        where a.id = {id}
    </select>

    <select id="find" parameterType="Long" resultType="${code.packageName}.entity.${code.entityName}">
        <include refid="lookup"/>
        <where>
            <if test="entity != null">
                <#list code.attrs as attr>
                <if test="entity.${attr.javaName} != null and entity.${attr.javaName} != '' ">
                    and ${attr.jdbcName} = {entity.${attr.javaName}}
                </if>
                </#list>
            </if>
        </where>
    </select>

</mapper>