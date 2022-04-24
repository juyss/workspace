package com.github.tangyi.introduction.controller;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.IdGen;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.introduction.entity.GeneralIntroduction;
import com.github.tangyi.introduction.service.GeneralIntroductionService;
import com.github.tangyi.user.api.feign.UserServiceClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: GeneralIntroductionController
 * @Desc:
 * @Author Juyss
 * @Date: 2021-04-16 15:09
 * @Version 1.0
 */
@Api("通用简介管理")
@Slf4j
@RestController
@RequestMapping("/v1/genericIntroduce")
public class GeneralIntroductionController {

    @Autowired
    private GeneralIntroductionService service;

    @Autowired
    private UserServiceClient userServiceClient;

    /**
     * 获取树形结构列表
     * @param sort 排序分类
     * @param order 排序方向
     * @return 树形结构
     */
    @ApiOperation(value = "获取树形结构列表", notes = "获取树形结构列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sort", value = "排序类别", dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方向", dataType = "String")}
    )
    @GetMapping("/getTreeList")
    public ResponseBean<List<GeneralIntroduction>> getTreeList(@RequestParam(value = "sort",required = false)String sort,
                                                               @RequestParam(value = "order",required = false)String order){
        //查询整个树形结构数据
        List<GeneralIntroduction> list = service.getList(sort, order);
        return new ResponseBean<>(list);
    }

    /**
     * 新增节点
     * @param pojo 节点封装对象
     * @return 结果封装
     */
    @ApiOperation(value = "新增节点", notes = "新增节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父节点ID", dataType = "String"),
            @ApiImplicitParam(name = "pojo", value = "节点数据")}
    )
    @PostMapping("/insertNode")
    public ResponseBean<Object> insertNode(@RequestBody GeneralIntroduction pojo){

        //设置自身ID
        pojo.setId(IdGen.snowflakeId());

        //设置创建者和更新者名字
        String username = SysUtil.getUser();
        pojo.setCreateUser(username);
        pojo.setUpdateUser(username);

        //设置同一级显示排序
        Double maxIdx = service.getMaxIdx(pojo.getParentId());
        pojo.setIdx(maxIdx == null ? 1.0 : maxIdx+1.0);

        Boolean flag = service.insert(pojo);
        return new ResponseBean<>(flag);
    }

    /**
     * 更新节点数据
     * @param pojo 数据封装实体
     * @return 响应封装
     */
    @ApiOperation(value = "更新节点", notes = "更新节点")
    @ApiImplicitParam(name = "pojo", value = "节点数据")
    @PutMapping("/updateNode")
    public ResponseBean<Object> updateNode(@RequestBody GeneralIntroduction pojo){

        //设置更新者名字
        String username = SysUtil.getUser();
        pojo.setUpdateUser(username);

        //更新
        Boolean flag = service.update(pojo);
        return new ResponseBean<>(flag);
    }

    /**
     * 根据传入的节点ID，删除此节点及其子节点
     * @param id 节点ID
     * @return 数据封装
     */
    @ApiOperation(value = "删除节点数据",notes = "根据传入的节点ID，删除此节点及其子节点")
    @ApiImplicitParam(name = "id",required = true, value = "节点ID")
    @DeleteMapping("/deleteNode")
    public ResponseBean<Object> deleteNode(@RequestParam("id") String id){

        Long parentId = Long.valueOf(id);
        //查询下级节点ID集合
        List<GeneralIntroduction> childrenList = (List<GeneralIntroduction>) service.getChildrenList(parentId, null,1, Integer.MAX_VALUE,null,null).getRows();

        //删除下级节点
        for (GeneralIntroduction introduction : childrenList) {
            Long introductionId = introduction.getId();
            service.delete(introductionId);
        }

        //删除此节点
        Boolean parentDelete = service.delete(parentId);

        return new ResponseBean<>(parentDelete);
    }

    /**
     * 根据ID查询节点数据
     * @param id 节点主键ID
     * @return 数据封装
     */
    @ApiOperation("根据节点ID查询数据")
    @ApiImplicitParam(value = "主键ID",required = true,name = "id")
    @GetMapping("/getNode")
    public ResponseBean<GeneralIntroduction>  getIntroById(@RequestParam("id") String id){
        GeneralIntroduction generalIntroduction = service.getIntroById(Long.valueOf(id));
        return new ResponseBean<>(generalIntroduction);
    }

    /**
     * 根据ID获取子节点信息
     * @param id 节点ID
     * @param sort 排序分类
     * @param order 排序方向
     * @return 数据封装
     */
    @ApiOperation(value = "根据ID获取子节点信息", notes = "根据ID获取子节点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点ID", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Integer"),
            @ApiImplicitParam(name = "sort", value = "排序分类", dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方向", dataType = "String")
    })
    @GetMapping("/getTreeListById")
    public ResponseBean<PageResult> getTreeListById(@RequestParam(value = "id",required = false) String id,
                                                    @RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                                    @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                                    @RequestParam(value = "sort" ,required = false, defaultValue = "idx") String sort ,
                                                    @RequestParam(value = "order",required = false, defaultValue = "asc") String order){
        Long parentId;
        if(id != null){
             parentId= Long.valueOf(id);
        }else {
            parentId = null;
        }
        //查询下级节点集合
        PageResult list = service.getChildrenList(parentId, name, pageNum, pageSize, sort, order);

        return new ResponseBean<>(list);
    }

    /**
     * 移动节点，命令（置顶 :top  置底 :end 移动 :move）
     * @param parentId 父节点ID
     * @param thisId 此节点ID
     * @param anotherId 另一个需要修改的节点ID
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
    public ResponseBean<Boolean> updateIdxById(@RequestParam("parentId")String parentId,
                                               @RequestParam("thisId") String thisId,
                                               @RequestParam(value = "anotherId",required =false) String anotherId,
                                               @PathVariable("command")String command){
        return new ResponseBean<>(service.updateIdxById(Long.valueOf(parentId), Long.valueOf(thisId),Long.valueOf(anotherId), command));
    }

    /**
     * 根据名称查询
     * @param name 节点名称
     * @param sort 排序分类
     * @param order 排序方向
     * @return 结果封装
     */
    @ApiOperation(value = "根据名称查询", notes = "根据名称查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名字", dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序分类", dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方向", dataType = "String")
    })
    @GetMapping("/selectByName")
    public ResponseBean<List<GeneralIntroduction>> selectIntroByName(@RequestParam("name")String name,
                                                                     @RequestParam("sort")String sort,
                                                                     @RequestParam("order")String order){
        return new ResponseBean<>(service.selectIntroByName(name, sort, order));
    }

}