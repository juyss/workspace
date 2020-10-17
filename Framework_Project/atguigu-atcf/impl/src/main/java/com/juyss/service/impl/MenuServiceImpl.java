package com.juyss.service.impl;

import com.juyss.bean.TMenu;
import com.juyss.bean.TMenuExample;
import com.juyss.mapper.TMenuMapper;
import com.juyss.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MenuServiceImpl
 * @Desc: 菜单 业务层实现类
 * @package com.juyss.service.impl
 * @project atguigu-atcf
 * @date 2020/10/13 18:24
 */
@Service
public class MenuServiceImpl implements MenuService {

    private TMenuMapper menuMapper;

    private TMenuExample example;

    @Autowired
    public void setMenuMapper(TMenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Autowired
    public void setExample(TMenuExample example) {
        this.example = example;
    }

    /**
     * 获取菜单树，包含父菜单项及其子菜单项
     *
     * @return 父菜单集合
     */
    @Override
    public List<TMenu> getMenuList() {
        example.createCriteria();
        return menuMapper.selectByExample(example);
    }
}
