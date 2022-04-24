package com.github.tangyi.office.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.config.LongToStringJsonConfig;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.office.service.IOfficeServie;
import com.github.tangyi.pub.api.dto.office.MoveIdxDto;
import com.github.tangyi.pub.api.module.office.SysCntLink;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ck
 * @Desc:办事大厅
 */
@RestController
@ApiModel(value = "办事大厅")
@RequestMapping("/v1/office")
public class OfficeHallController {

    @Autowired
    private IOfficeServie officeServie;

    @ApiOperation(value = "获取办事大厅列表 构建成树形结构", notes = "获取办事大厅列表 构建成树形结构")
    @GetMapping("officeList")
    public ResponseBean<List<SysCntLink>> officeList() {
        return new ResponseBean<>(this.officeServie.officeList());
    }

    /**
     * 根据id查询下级节点列表 序号升序 修改时间降序
     * @param id
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "根据id查询下级节点列表", notes = "根据id查询下级节点列表")
    @GetMapping("getLinkById/{id}")
    public ResponseBean<PageInfo<SysCntLink>> getLinkById(@PathVariable(value = "id") Long id,
                                                          @RequestParam(value = "name", required = false) String name,
                                                          @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                                          @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysCntLink> sysCntLinks = this.officeServie.getLinkById(id,name);
        PageInfo<SysCntLink> pageInfo = new PageInfo<>(sysCntLinks);
        return new ResponseBean<>(pageInfo);
    }

    @ApiOperation(value = "根据id 修改名称和排序", notes = "根据id 修改名称和排序")
    @PutMapping("updateBySort/{id}")
    public ResponseBean<Boolean> updateBySort(@PathVariable("id") Long id,
                                              @RequestParam("name") String name,
                                              @RequestParam("idx") Integer idx) {
        return new ResponseBean<>(this.officeServie.updateBySort(id, name, idx) > 0);
    }

    @ApiOperation(value = "删除节点 及其子节点", notes = "根据id 修改名称和排序")
    @DeleteMapping("deleteTree/{id}")
    public ResponseBean<Boolean> deleteTree(@PathVariable("id") Long id) {
        return new ResponseBean<>(this.officeServie.deleteTree(id) > 0);
    }

    @ApiOperation(value = "删除节点 及其子节点", notes = "根据id 修改名称和排序")
    @PutMapping("updateById/{id}")
    public ResponseBean<Boolean> updateById(@PathVariable("id") Long id,
                                            @RequestBody SysCntLink sysCntLink) {
        return new ResponseBean<>(this.officeServie.updateByid(id, sysCntLink) > 0);
    }

    @ApiOperation(value = "新增子节点", notes = "新增子节点")
    @PostMapping("addNode")
    public ResponseBean<Boolean> addNode(@RequestBody SysCntLink sysCntLink) {
        return new ResponseBean<>(this.officeServie.addNode(sysCntLink) > 0);
    }

    /**
     *根据id查询实体
     */
    @ApiOperation(value = "根据id查询实体", notes = "根据id查询实体")
    @GetMapping("selectById/{id}")
    public ResponseBean<SysCntLink> selectByid(@PathVariable("id") Long id){
        return new ResponseBean<>(this.officeServie.selectById(id));
    }

    /**
     * 置顶 :top  置底 :floor 上移 :up 下移 :down
     */

    @ApiOperation(value = "顶 :top  置底 :floor 上移 :up 下移 :down",
            notes = "顶 :top  置底 :floor 上移 :up 下移 :down")
    @PutMapping("moveIdx")
    public ResponseBean<Boolean> moveIdx(@RequestBody MoveIdxDto moveIdxDto){
        return new ResponseBean<>(this.officeServie.moveIdx(moveIdxDto));
    }

    /**
     * 前台门户查询接口
     */
    @ApiOperation(value = "前台查询接口",notes = "前台查询接口")
    @GetMapping("queryAllList")
    public ResponseBean<List<SysCntLink>> queryAllList(){
        return new ResponseBean<>(this.officeServie.queryAllList());
    }



}
