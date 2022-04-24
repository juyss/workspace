package com.icepoint.framework.web.ui.mapper;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.ui.entity.UiMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface UiMenuMapper extends RepositoryMapper<UiMenu,Long> {
    /**
     * 根据角色id 查询菜单
     * @param roleId 角色id
     * @return 菜单
     */
    List<UiMenu> getMenuByRole(@Param("roleId") Long roleId);

    /**
     * 删除角色对应的所有的菜单
     * @param roleId
     * @return
     */
    Integer deleteMenuByRoleId(@Param("roleId") Long roleId);

    /**
     * 新增 角色和菜单的绑定
     * @param roleId
     * @param item
     * @return
     */
    Integer insertMenId(@Param("roleId") Long roleId, @Param("menuId") Long item);
}
