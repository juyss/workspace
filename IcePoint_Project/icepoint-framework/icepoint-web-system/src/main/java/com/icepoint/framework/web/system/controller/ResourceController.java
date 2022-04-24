package com.icepoint.framework.web.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.service.ResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 资源表(Resource)表控制层
 *
 * @author makejava
 * @since 2021-05-27 10:41:27
 */
@RestController
@RequestMapping("genericResource")
public class ResourceController extends ApiController {

    /**
     * 服务对象
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * 分页查询,可以按条件查询
     * @author Juyss
     * @param genericResource 实体对象
     * @param pageable 分页参数
     * @return 所有数据
     */
    @GetMapping("page")
    public PageResponse<Resource> selectAll(Resource genericResource, Pageable pageable) {
        Page<Resource> page = null;
        //对象转map,属性名驼峰转下划线
        Map<String, Object> columnMap = MapUtils.objectToLineMap(genericResource);

        page = resourceService.findAll(columnMap, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 查询全部树形结构
     * @author Juyss
     * @return List<TreeNode<Resource>>
     */
    @GetMapping("tree")
    public CollectionResponse<TreeNode<Resource>> getTreeList(@RequestParam("rootId")Long rootId){
        List<TreeNode<Resource>> treeList = resourceService.getTreeList(rootId);
        return ResponseUtils.many(treeList);
    }

    /**
     * 通过id查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<Resource> selectOne(@PathVariable Long id) {
        Resource genericResource = resourceService.findOne(id);
        return ResponseUtils.one(genericResource);
    }

    /**
     * 新增数据
     *
     * @param genericResource 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public Response<Boolean> insert(@RequestBody Resource genericResource) {
        this.resourceService.save(genericResource);
        return ResponseUtils.any(Boolean.TRUE);
    }

    /**
     * 修改数据
     *
     * @param genericResource 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public Response<Boolean> update(@RequestBody Resource genericResource) {
        boolean update = this.resourceService.update(genericResource);
        return ResponseUtils.any(update);
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public Response<Boolean> delete(@RequestParam("ids") List<Long> ids) {
        boolean remove = this.resourceService.delete(ids);
        return ResponseUtils.any(remove);
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
        return ResponseUtils.any(resourceService.updateIdxById(parentId, thisId,anotherId, command));
    }
}
