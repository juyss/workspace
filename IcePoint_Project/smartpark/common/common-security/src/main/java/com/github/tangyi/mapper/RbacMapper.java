package com.github.tangyi.mapper;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.security.core.RbacServiceImpl;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

@Mapper
public interface RbacMapper {


    @Results(value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name"),
            @Result(column = "permission", property = "permission"),
            @Result(column = "url", property = "url"),
            @Result(column = "redirect", property = "redirect"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "icon", property = "icon"),
            @Result(column = "sort", property = "sort"),
            @Result(column = "type", property = "type"),
            @Result(column = "component", property = "component"),
            @Result(column = "path", property = "path"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "creator", property = "creator"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "modifier", property = "modifier"),
            @Result(column = "modify_date", property = "modifyDate"),
            @Result(column = "del_flag", property = "delFlag"),
            @Result(column = "application_code", property = "applicationCode"),
            @Result(column = "tenant_code", property = "tenantCode"),
    }, id = "baseMap")
    @Select({
            "select \n" +
                    "m.*\n" +
                    "from \n" +
                    "sys_user_auths a\n" +
                    "inner join sys_user_role r on a.user_id=r.user_id\n" +
                    "inner join sys_role_menu rm on r.role_id=rm.role_id\n" +
                    "inner join sys_menu m on rm.menu_id=m.id and m.del_flag=0\n" +
                    "where a.identifier=#{username} and a.del_flag=0"
    })
    List<RbacServiceImpl.Menu> getMenuByUsername(@Param("username") String username);

    @Cacheable(value = "menu#" + CommonConstant.CACHE_EXPIRE, key = "'all'")
    @ResultMap("baseMap")
    @Select({
            "select \n" +
                    "m.*\n" +
                    "from \n" +
                    "sys_menu m\n" +
                    "where m.del_flag=0"
    })
    List<RbacServiceImpl.Menu> getAllMenu();

    @Cacheable(value = "menu#" + CommonConstant.CACHE_EXPIRE, keyGenerator = "myKeyGenerator")
    @ResultMap("baseMap")
    @Select({
            "<script>select \n" +
                    "m.*\n" +
                    "from \n" +
                    "sys_role r\n" +
                    "inner join sys_role_menu rm on r.id=rm.role_id\n" +
                    "inner join sys_menu m on rm.menu_id=m.id and m.del_flag=0\n" +
                    "where r.del_flag=0\n" +
                    " and r.role_code in  " +
                    "<foreach collection=\"roles\"  item=\"item\" open=\"(\" separator=\",\" close=\")\">\n" +
                    "            #{item}\n" +
                    " </foreach>" +
                    "</script>"
    })
    List<RbacServiceImpl.Menu> getAllMenuByRoleNames(@Param("roles") Set<String> roles);

    @ResultType(String.class)
    @Select({
            "select \n" +
                    "s.role_code\n" +
                    "from \n" +
                    "sys_user_auths a\n" +
                    "inner join sys_user_role r on a.user_id=r.user_id\n" +
                    "inner join sys_role s on r.role_id=s.id and s.del_flag=0\n" +
                    "where a.identifier=#{username} and a.del_flag=0"
    })
    List<String> getRolesByUsername(@Param("username") String username);


    @Insert({
            "insert into sys_user_role (id,user_id,role_id)  values (#{id}, (select user_id from  sys_user_auths where identifier=#{username} and del_flag=0 limit 1)," +
                    " (select id from sys_role where role_code=#{roleCode} and del_flag=0 limit 1))  "
    })
    int addUserRole(@Param("username") String username, @Param("roleCode") String roleCode, @Param("id") long id);

    @Delete({ "delete from  sys_user_role where user_id=(select user_id from  sys_user_auths where identifier=#{username} and del_flag=0 limit 1) " +
            " and role_id=(select id from sys_role where role_code=#{roleCode} and del_flag=0 limit 1)  "})
    int delUserRole(String username, String roleCode);


    @ResultType(String.class)
    @Select({
            "select \n" +
            "s.role_code\n" +
            "from \n" +
            "sys_role s where s.del_flag=0\n"
    })
    List<String> getAllRoles();
}
