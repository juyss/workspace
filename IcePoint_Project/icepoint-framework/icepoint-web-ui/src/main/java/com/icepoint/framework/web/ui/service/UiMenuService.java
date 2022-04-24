package com.icepoint.framework.web.ui.service;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.ui.entity.UiLessFile;
import com.icepoint.framework.web.ui.entity.UiMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 */
public interface UiMenuService {
    /**
     * 获取菜单 如果code为空查询全部
     */
    List<TreeNode<UiMenu>> getTreeList(String code, Integer termType, Integer type, Long appId, Long ownerId);

    /**
     * 修改和新增菜单
     */
    Boolean updateUiMenu(UiMenu uiMenu);

    /**
     * 删除菜单
     */
    Boolean deleteUiMenu(Long id);

    /**
     * 分页查询
     */
    Page<UiMenu> page(UiMenu uiMenu, Pageable pageable);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UiMenu queryById(Long id);

    Boolean updateIdxById(Long parentId, Long thisId, Long anotherId, String command);

    String uploadLess(MultipartFile multipartFile, HttpServletRequest request, UiLessFile uiLessFile);

    /**
     * 根据角色获取菜单
     * @param id 角色id
     * @return 菜单集合
     */
    List<UiMenu> getMenuByRole(Long id);

    /**
     * 修改角色对应的权限
     * @param roleId 权限id
     * @param menuId 菜单集合
     * @return 是否成功
     */
    Boolean updateMenuByRole(Long roleId, List<Long> menuId);
}
