package com.icepoint.framework.web.ui.controller;

import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.ui.entity.UiLessFile;
import com.icepoint.framework.web.ui.entity.UiMenu;
import com.icepoint.framework.web.ui.service.UiMenuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单配置
 *
 * @author Administrator
 */
@RestController
@RequestMapping("uiMenu")
public class UiMenuController {

    @Resource
    private UiMenuService service;

    /**
     * 获取UiMenu树
     *
     * @param code     代码 非必填
     * @param termType 端类型。PC、安卓、苹果、微信公众号、小程序，字典定义，可扩充 非必填
     * @param type     类型 非必填
     * @param appId    必填
     * @param ownerId  必填
     */
    @GetMapping("tree")
    public CollectionResponse<TreeNode<UiMenu>> get(@RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "termType", required = false) Integer termType,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "appId") Long appId,
            @RequestParam(value = "ownerId") Long ownerId) {
        return ResponseUtils.many(service.getTreeList(code, termType, type, appId, ownerId));
    }


    /**
     * 修改UiMenu
     */
    @PostMapping("update")
    public Response<Boolean> updateUiMenu(UiMenu uiMenu) {
        return ResponseUtils.any(service.updateUiMenu(uiMenu));
    }

    /**
     * 根据id删除
     */
    @DeleteMapping("delete/{id}")
    public Response<Boolean> deleteUiMenu(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.deleteUiMenu(id));
    }

    /**
     * 分页
     */
    @GetMapping("page")
    public PageResponse<UiMenu> page(UiMenu uiMenu, Pageable pageable) {
        return ResponseUtils.page(service.page(uiMenu, pageable));
    }

    /**
     * 根据id获取UiMenu
     */
    @GetMapping("getById/{id}")
    public Response<UiMenu> getById(@PathVariable("id") Long id) {
        return ResponseUtils.any(service.queryById(id));
    }

    /**
     * 移动节点，命令（置顶 :top  置底 :end 移动 :move）
     *
     * @param parentId  父节点ID
     * @param thisId    此节点ID
     * @param anotherId 另一个需要修改的节点ID，上移时传入上一个节点ID，下移时传入下一节点ID，置顶和置底时不需要
     * @param command   移动命令
     * @return 消息封装
     */
    @ApiOperation(value = "移动节点", notes = "移动节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", required = true, value = "父节点ID", dataType = "String"),
            @ApiImplicitParam(name = "thisId", required = true, value = "此节点ID", dataType = "String"),
            @ApiImplicitParam(name = "anotherId", value = "另一个需要修改的节点ID，上移时传入上一个节点ID，下移时传入下一节点ID，置顶和置底时不需要，", dataType = "String"),
            @ApiImplicitParam(name = "command", required = true, value = "移动命令，置顶 :top  置底 :end 移动 :move", dataType = "String")
    })
    @PutMapping("moveIdx/{command}")
    public Response<Boolean> updateIdxById(@RequestParam("parentId") Long parentId,
            @RequestParam("thisId") Long thisId,
            @RequestParam(value = "anotherId", required = false) Long anotherId,
            @PathVariable("command") String command) {
        return ResponseUtils.any(service.updateIdxById(parentId, thisId, anotherId, command));
    }

    /**
     * 风格模板文件上传修改
     */
    @PostMapping("uploadLess")
    public Response<String> uploadLess(MultipartFile file, UiLessFile uiLessFile, HttpServletRequest request) {
        return ResponseUtils.any(service.uploadLess(file, request, uiLessFile));
    }

    /**
     *人员对应菜单
     */
    @GetMapping("/getMenuByRole/{id}")
    public CollectionResponse<UiMenu> getMenuByRole(@PathVariable("id") Long id){
        return ResponseUtils.many(service.getMenuByRole(id));
    }
    /**
     * 修改角色对应的权限
     */
    @PutMapping("updateMenuByRole")
    public Response<Boolean> updateMenuByRole(@RequestParam(value = "roleId") Long roleId,@RequestParam(value = "menuId") List<Long> menuId){
        return ResponseUtils.one(service.updateMenuByRole(roleId,menuId));
    }

}
