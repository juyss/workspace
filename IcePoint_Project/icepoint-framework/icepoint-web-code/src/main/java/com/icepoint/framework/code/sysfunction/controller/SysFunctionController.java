package com.icepoint.framework.code.sysfunction.controller;

import com.icepoint.framework.code.response.dto.FunctionDTO;
import com.icepoint.framework.code.response.dto.JavaFunctionDTO;
import com.icepoint.framework.code.sysfunction.entity.SysFunction;
import com.icepoint.framework.code.sysfunction.service.SysFunctionService;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (SysFunction)函数控制层
 *
 * @author Juyss
 * @version 1.0
 * @since 2021-06-10 10:49
 */
@RestController
@RequestMapping("sysFunction")
public class SysFunctionController {

    @Resource
    private SysFunctionService sysFunctionService;




    /**
     * 分页查询所有数据
     *
     * @param pageable    分页对象
     * @param sysFunction 查询实体
     * @return 所有数据
     */
    @GetMapping("page")
    public PageResponse<SysFunction> selectAll(SysFunction sysFunction, Pageable pageable) {
        Page<SysFunction> page = null;
        Map<String, Object> map = MapUtils.objectToLineMap(sysFunction);
        page = sysFunctionService.findAll(map, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<SysFunction> selectOne(@PathVariable Long id) {
        return ResponseUtils.any(this.sysFunctionService.findOne(id));
    }

    /**
     * 新增数据
     *
     * @param sysFunction 实体对象
     * @return 新增结果
     */
    @PostMapping("save")
    public Response<Boolean> insert(@RequestBody SysFunction sysFunction) {
        return ResponseUtils.any(this.sysFunctionService.insert(sysFunction));
    }

    /**
     * 修改数据
     *
     * @param sysFunction 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public Response<Boolean> update(@RequestBody SysFunction sysFunction) {
        return ResponseUtils.any(this.sysFunctionService.update(sysFunction));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return DefaultResponse<Integer>
     * @author Juyss
     */
    @DeleteMapping("delete")
    public Response<Boolean> delete(@RequestParam("id") Long id) {
        return ResponseUtils.any(this.sysFunctionService.delete(id));
    }

    /**
     * 添加函数，来自工程
     *
     * @param functionDTO 数据对象对象
     * @return 新增结果
     */
    @PostMapping("fromProject")
    public Response<Boolean> fromProject(@RequestBody FunctionDTO functionDTO) {
        SysFunction sysFunction = this.sysFunctionService.fromProject(functionDTO);
        return ResponseUtils.any(this.sysFunctionService.insert(sysFunction));
    }

    /**
     * 添加函数，来自用户自定义
     *
     * @param javaFunctionDTO 数据对象对象
     * @return 新增结果
     */
    @PostMapping("fromUserDefine")
    public Response<Boolean> fromUserDefine(@RequestBody JavaFunctionDTO javaFunctionDTO) {
        SysFunction sysFunction = sysFunctionService.fromUserDefine(javaFunctionDTO);
        return ResponseUtils.any(this.sysFunctionService.insert(sysFunction));
    }

    /**
     * 查询函数
     */
    @GetMapping("getFunction/{id}")
    public Response<SysFunction> getFunction(@PathVariable("id") Long id){
        return ResponseUtils.any(this.sysFunctionService.getFunction(id));
    }

    /**
     * 修改函数
     */
   @PutMapping("updateFunction")
   public Response<Boolean> updateFunction(@RequestBody JavaFunctionDTO javaFunctionDTO){
       return ResponseUtils.any(this.sysFunctionService.updateFunction(javaFunctionDTO));
   }

    /**
     * 删除函数
     */
    @DeleteMapping("deleteProcessFunction")
    public Response<Boolean> deleteProcessFunction(@RequestParam("id") Long id){
        return ResponseUtils.any(this.sysFunctionService.deleteProcessFunction(id));
    }

}
