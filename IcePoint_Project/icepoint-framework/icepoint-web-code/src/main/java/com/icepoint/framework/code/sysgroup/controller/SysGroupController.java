package com.icepoint.framework.code.sysgroup.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.icepoint.framework.code.sysgroup.entity.SysGroup;
import com.icepoint.framework.code.sysgroup.service.SysGroupService;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 分组表(SysGroup)表控制层
 *
 * @author makejava
 * @since 2021-06-05 11:04:11
 */
@RestController
@RequestMapping("sysGroup")
public class SysGroupController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysGroupService sysGroupService;

    /**
     * 分页查询所有数据
     *
     * @param sysGroup 查询实体
     * @param pageable     分页对象
     * @return 所有数据
     */
    @GetMapping("page")
    public PageResponse<SysGroup> selectAll(SysGroup sysGroup, Pageable pageable) {
        Map<String, Object> map = null;
        map = MapUtils.objectToLineMap(sysGroup);
        return ResponseUtils.page(sysGroupService.findAll(map,pageable));
    }

    /**
     * 获取树形结构
     * @return List<TreeNode<SysGroup>>
     */
    @GetMapping("tree")
    public CollectionResponse<TreeNode<SysGroup>> getTreeList(@RequestParam("rootId")Long rootId){
        List<TreeNode<SysGroup>> treeList = sysGroupService.getTreeList(rootId);
        return ResponseUtils.many(treeList);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<SysGroup> selectOne(@PathVariable Long id) {
        return ResponseUtils.one(sysGroupService.findOne(id));
    }

    /**
     * 新增数据
     *
     * @param sysGroup 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public Response<Boolean> insert(@RequestBody SysGroup sysGroup) {
        return ResponseUtils.any(sysGroupService.save(sysGroup));
    }

    /**
     * 修改数据
     *
     * @param sysGroup 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public Response<Boolean> update(@RequestBody SysGroup sysGroup) {
        return ResponseUtils.any(sysGroupService.update(sysGroup));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public Response<Integer> delete(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(this.sysGroupService.delete(ids));
    }

    /**
     * 移动节点，命令（置顶 :top  置底 :end 移动 :move）
     * @param parentId 父节点ID
     * @param thisId 此节点ID
     * @param anotherId 另一个需要修改的节点ID，上移时传入上一个节点ID，下移时传入下一节点ID，置顶和置底时不需要
     * @param command 移动命令
     * @return 消息封装
     */
    @ApiOperation(value = "移动节点", notes = "移动节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId",required = true, value = "父节点ID", dataType = "String"),
            @ApiImplicitParam(name = "thisId", required = true, value = "此节点ID", dataType = "String"),
            @ApiImplicitParam(name = "anotherId" ,value = "另一个需要修改的节点ID，上移时传入上一个节点ID，下移时传入下一节点ID，置顶和置底时不需要，", dataType = "String"),
            @ApiImplicitParam(name = "command", required = true, value = "移动命令，置顶 :top  置底 :end 移动 :move", dataType = "String")
    })
    @PutMapping("moveIdx/{command}")
    public Response<Boolean> updateIdxById(@RequestParam("parentId")Long parentId,
                                           @RequestParam("thisId") Long thisId,
                                           @RequestParam(value = "anotherId",required =false) Long anotherId,
                                           @PathVariable("command")String command){
        return ResponseUtils.any(sysGroupService.updateIdxById(parentId, thisId,anotherId, command));
    }

    /**
     *
     * @param file 函数文件
     * @param id 用户id
     */
    @PostMapping("updateDescription/{id}")
    public Response updateDescription(@RequestBody MultipartFile file, @PathVariable("id") Long id){
        return ResponseUtils.any(this.sysGroupService.updateDescription(file,id));
    }


}
