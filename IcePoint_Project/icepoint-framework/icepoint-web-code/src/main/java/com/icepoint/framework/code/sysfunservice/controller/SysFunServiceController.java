package com.icepoint.framework.code.sysfunservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.icepoint.framework.code.sysfunservice.entity.SysFunService;
import com.icepoint.framework.code.sysfunservice.service.SysFunServiceService;
import com.icepoint.framework.code.xml.entity.Definitions;
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
 * (SysFunService)表控制层
 *
 * @author ck
 * @since 2021-06-04 17:44:23
 */
@RestController
@RequestMapping("sysFunService")
public class SysFunServiceController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysFunServiceService sysFunServiceService;

    /**
     * 分页查询所有数据
     *
     * @param pageable 分页对象
     * @param sysFunService 查询实体
     * @return 所有数据
     */
    @GetMapping("page")
    public PageResponse<SysFunService> selectAll(SysFunService sysFunService,Pageable pageable) {
        Page<SysFunService> page = null;
        Map<String, Object> map = MapUtils.objectToLineMap(sysFunService);
        QueryWrapper<SysFunService> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map);
        page  = sysFunServiceService.pageList(queryWrapper,pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<SysFunService> selectOne(@PathVariable Long id) {
        return ResponseUtils.any(this.sysFunServiceService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysFunService 实体对象
     * @return 新增结果
     */
    @PostMapping("save")
    public Response<Integer> insert(@RequestBody SysFunService sysFunService) {
        return ResponseUtils.any(this.sysFunServiceService.save(sysFunService));
    }

    /**
     * 修改数据
     *
     * @param sysFunService 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public Response<Integer> update(@RequestBody SysFunService sysFunService) {
        return ResponseUtils.any(this.sysFunServiceService.updateById(sysFunService));
    }

    /**
     * 删除数据
     * @author Juyss
     * @param id 主键
     * @return DefaultResponse<Integer>
     */
    @DeleteMapping("delete")
    public Response<Integer> delete(@RequestParam("id") Long id){
        return ResponseUtils.any(this.sysFunServiceService.delete(id));
    }

    /**
     * 查询用户的函数列表
     * @return DefaultResponse<Definitions>
     */
    @GetMapping("getProcess")
    public Response<Definitions> getProcess(){
        return ResponseUtils.any(sysFunServiceService.getProcessList());
    }

//    /**
//     * 保存函数流程图
//     * @author Juyss
//     * @param functionFlow 数据对象
//     * @param id 主键
//     * @return FunctionFlow
//     */
//    @PostMapping("putFunctionFlow/{id}")
//    public Response<FunctionFlow> putFunctionFlow(@RequestBody FunctionFlow functionFlow, @PathVariable("id") Long id){
//        sysFunServiceService.putFunctionFlow(functionFlow,id);
//        return ResponseUtils.any(functionFlow);
//    }
//
//    /**
//     * 读取函数流程图
//     * @author Juyss
//     * @param id 主键
//     * @return DefaultResponse<FunctionFlow>
//     */
//    @GetMapping("getFunctionFlow/{id}")
//    public Response<FunctionFlow> getFunctionFlow(@PathVariable("id") Long id){
//        FunctionFlow functionFlow = sysFunServiceService.getFunctionFlow(id);
//        return ResponseUtils.any(functionFlow);
//    }

    /**
     * 保存函数流程图
     * @author Juyss
     * @param json 数据对象
     * @param id 主键
     * @return FunctionFlow
     */
    @PostMapping("putFunctionFlow/{id}")
    public Response<Void> putFunctionFlow(@RequestBody String json, @PathVariable("id") Long id){
        Boolean flag = sysFunServiceService.saveFunctionFlow(json, id);
        return ResponseUtils.operate(flag);
    }

    /**
     * 读取函数流程图
     * @author Juyss
     * @param id 主键
     * @return DefaultResponse<FunctionFlow>
     */
    @GetMapping("getFunctionFlow/{id}")
    public Response<Object> getFunctionFlow(@PathVariable("id") Long id){
        Object functionFlow = sysFunServiceService.readFunctionFlow(id);
        return ResponseUtils.one(functionFlow);
    }
}
