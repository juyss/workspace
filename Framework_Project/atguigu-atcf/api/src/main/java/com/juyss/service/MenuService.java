package com.juyss.service;

import com.juyss.bean.TMenu;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MenuService
 * @Desc: 菜单 业务层接口
 * @package com.juyss.service
 * @project atguigu-atcf
 * @date 2020/10/13 18:23
 */
public interface MenuService {

    /**
     * 获取菜单树，包含父菜单和子菜单
     * @return List<TMenu> 父菜单集合
     */
    List<TMenu> getMenuList();

}
