package com.github.tangyi.message.controller;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.introduction.entity.GeneralIntroduction;
import com.github.tangyi.message.entity.MsgCommonQa;
import com.github.tangyi.message.service.MsgCommonQaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName MsgCommonQaController
 * @description
 * @since 2021-05-20 14:54
 */
@Api("政民互动常见问题管理")
@Slf4j
@RestController
@RequestMapping("/v1/CommonQa")
public class MsgCommonQaController {

    @Autowired
    private MsgCommonQaService service;

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
    public ResponseBean<List<MsgCommonQa>> getTreeList(@RequestParam(value = "sort",required = false)String sort,
                                                       @RequestParam(value = "order",required = false)String order){
        //查询整个树形结构数据
        List<MsgCommonQa> list = service.getTreeList(sort, order);
        return new ResponseBean<>(list);
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
            @ApiImplicitParam(name = "commonQaTheme", value = "关键字", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Integer"),
            @ApiImplicitParam(name = "sort", value = "排序分类", dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方向", dataType = "String")
    })
    @GetMapping("/getChildrenListById")
    public ResponseBean<PageResult> getChildrenListById(@RequestParam(value = "id",required = false) String id,
                                                    @RequestParam(value = "commonQaTheme",required = false)String name,
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
        return new ResponseBean<>(service.updateIdxById(Long.valueOf(parentId), Long.valueOf(thisId),anotherId == null ? null : Long.valueOf(anotherId), command));
    }

    /**
     * 获取此节点的最大idx值
     * @param id
     * @return
     */
    @ApiOperation(value = "获取此节点的最大idx值", notes = "获取此节点的最大idx值")
    @ApiImplicitParam(name = "id", value = "父节点id", dataType = "String")
    @GetMapping("/getMaxIdx")
    public ResponseBean<Double> getMaxIdx(String id){
        Long parentId;
        if(id != null){
            parentId= Long.valueOf(id);
        }else {
            parentId = null;
        }
        Double maxIdx = service.getMaxIdx(parentId);
        return new ResponseBean<>(maxIdx);
    }

    /**
     * 获取此节点的最小idx值
     * @param id
     * @return
     */
    @ApiOperation(value = "获取此节点的最小idx值", notes = "获取此节点的最小idx值")
    @ApiImplicitParam(name = "id", value = "父节点id", dataType = "String")
    @GetMapping("/getMinIdx")
    public ResponseBean<Double> getMinIdx(String id){
        Long parentId;
        if(id != null){
            parentId= Long.valueOf(id);
        }else {
            parentId = null;
        }
        Double maxIdx = service.getMinIdx(parentId);
        return new ResponseBean<>(maxIdx);
    }

}
