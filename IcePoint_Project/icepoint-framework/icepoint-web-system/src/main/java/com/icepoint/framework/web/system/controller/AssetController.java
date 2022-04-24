package com.icepoint.framework.web.system.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.core.service.DictService;
import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.web.system.service.AssetService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 资产管理控制层
 *
 * @since 2021-07-20 11:18:27
 */
@RestController
@RequestMapping("asset")
public class AssetController extends ApiController{
    /**
     * 服务对象
     */
    @Autowired
    private AssetService assetService;
    @Autowired
    private DictService dictService;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-资产定义
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * 新增资产定义
     * 
     * @param entity 资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetDefine}
     * @return 返回新增后的资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetDefine}
     */
    @PostMapping("addAssetDef")
    public Response<AssetDefine> addAssetDef(@RequestBody AssetDefine entity) {
        return ResponseUtils.any(assetService.addAssetDef(entity));
    }

    
    /**
     * 修改资产定义
     * 
     * @param entity 资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetDefine}
     * @return 返回修改后的资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetDefine}
     */
    @PutMapping("updateAssetDef")
    public Response<AssetDefine> updateAssetDef(@RequestBody AssetDefine entity) {
        return ResponseUtils.any(assetService.updateAssetDef(entity));
    }
    
    /**
     * 删除资产定义
     * 
     * @param ids 要删除的数据主键
     * @return 返回删除个数
     */
    @DeleteMapping("deleteAssetDef")
    public Response<Integer> deleteAssetDef(@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(assetService.deleteAssetDef(ids));
    }
    
    /**
     * 资产定义分页查询
     * 
     * @param filter 查询条件  {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetDefine}
     * @param pageable 分页参数  {@linkplain org.springframework.data.domain.Pageable Pageable}
     * @return 资产定义分页列表 List<{@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetDefine}>
     */
    @GetMapping("assetDefList")
    public PageResponse<AssetDefine> assetDefList(AssetDefine filter, Pageable pageable) {
        return ResponseUtils.page(assetService.assetDefList(filter, pageable));
    }

    /**
     * 资产定义树形结构
     * 
     * @param rootId 树形结构根节点
     * @return 树形结构
     */
    @GetMapping("assetDefTree")
    public CollectionResponse<TreeNode<AssetDefine>> assetDefTree(@RequestParam(value = "rootId",required = false,defaultValue = "1")Long rootId){
        List<TreeNode<AssetDefine>> treeList = assetService.assetDefTree(rootId);
        return ResponseUtils.many(treeList);
    }
    
    /**
     * 资产定义详情查询
     * 
     * @param id 资产定义ID
     * @return 返回资产定义 {@linkplain com.icepoint.framework.web.system.entity.AssetDefine AssetDefine}
     */
    @GetMapping("getAssetDefById")
    public Response<AssetDefine> getAssetDefById(@RequestParam Long id) {
        return ResponseUtils.any(assetService.getAssetDefById(id));
    }
    
    /**
     * 资产定义顺序设置
     * 
     * @param parentId  父节点
     * @param thisId 当前节点
     * @param command 设置命令
     * @return 是否成功
     */
    @ApiOperation(value = "移动节点", notes = "移动节点")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "parentId",required = true, value = "父节点ID", dataType = "String"),
    	@ApiImplicitParam(name = "thisId", required = true, value = "此节点ID", dataType = "String"),
        @ApiImplicitParam(name = "anotherId" ,value = "另一个需要修改的节点ID，上移时传入上一个节点ID，下移时传入下一节点ID，置顶和置底时不需要，", dataType = "String"),
        @ApiImplicitParam(name = "command", required = true, value = "移动命令，置顶 :top  置底 :end 上移 :up 下移:down", dataType = "String")
    })
    @PutMapping("moveAssetDef/{command}")
    public Response<Boolean> moveAssetDefById(@RequestParam("parentId")Long parentId,
                                              @RequestParam("thisId") Long thisId,
                                              @RequestParam(value = "anotherId",required =false) Long anotherId,
                                              @PathVariable("command")String command){
        return ResponseUtils.any(assetService.moveAssetDefById(parentId, thisId, anotherId, command));
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //  PC-资产对象
    //////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 新增资产对象
     * 
     * @param assetDefId 资产定义ID
     * @param entity 资产对象
     * @return 返回新增后的资产对象
     */
    @PostMapping("addAssetObj")
    public Response<Map<String,Object>> addAssetObj(@RequestParam Long assetDefId,@RequestBody Map<String,Object> entity) {
        return ResponseUtils.any(assetService.addAssetObj(assetDefId,entity));
    }
    
    /**
     * 修改资产对象
     * 
     * @param assetDefId 资产定义ID
     * @param entity 资产对象
     * @return 返回修改后的资产对象
     */
    @PutMapping("updateAssetObj")
    public Response<Map<String,Object>> updateAssetObj(@RequestParam Long assetDefId,@RequestBody Map<String,Object> entity) {
        return ResponseUtils.any(assetService.updateAssetObj(assetDefId,entity));
    }
    
    /**
     * 删除资产对象
     * 
     * @param assetDefId 资产定义ID
     * @param ids 要删除的数据主键
     * @return 返回删除个数
     */
    @DeleteMapping("deleteAssetObj")
    public Response<Integer> deleteAssetObj(@RequestParam Long assetDefId,@RequestParam("ids") List<Long> ids) {
        return ResponseUtils.any(assetService.deleteAssetObj(assetDefId,ids));
    }
    
    /**
     * 资产对象分页查询
     * 
     * @param assetDefId 资产定义ID
     * @param filter 查询条件
     * @param pageable 分页参数  {@linkplain org.springframework.data.domain.Pageable Pageable}
     * @return 资产对象分页列表 
     */
    @GetMapping("assetObjList")
    public PageResponse<Map<String,Object>> assetObjList(@RequestParam Long assetDefId,Map<String,Object> filter, Pageable pageable) {
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final Integer IS_VALID_YES = Integer.valueOf(dictService.findByCategoryAndItem(user.getAppId(),user.getPlatformId(),"IS_VALID", "VALID_YES").get().getValue());
        Integer IS_VALID_YES = 1;
        return ResponseUtils.page(assetService.objList(assetDefId,IS_VALID_YES,pageable,filter));
    }
    
    /**
     * 资产定义详情查询
     * 
     * @param assetDefId 资产定义ID
     * @param id 资产对象ID
     * @return 返回资产对象
     */
    @GetMapping("getAssetObjById")
    public Response<Map<String,Object>> getAssetObjById(@RequestParam Long assetDefId,@RequestParam Long id) {
//    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		final Integer IS_VALID_YES = Integer.valueOf(dictService.findByCategoryAndItem(user.getAppId(),user.getPlatformId(),"IS_VALID", "VALID_YES").get().getValue());
        Integer IS_VALID_YES = 1;
        return ResponseUtils.any(assetService.getObjById(assetDefId,IS_VALID_YES,id));
    }

    /**
     * 资产绑定
     */
    @PostMapping("bingDing")
    public Response<Map<String,Object>> binDing(@RequestBody Map<String,Object> map){
       return ResponseUtils.map(assetService.binDing(map));
    }

    /**
     * 资产解绑
     */
    @DeleteMapping("unBingDing/{assestid}")
    public Response<Boolean> unBingDing(@PathVariable("assestid") Long assestid){
        return ResponseUtils.any(assetService.unBingDing(assestid));
    }

    /**
     * 根据userId 查询绑定的资产
     */
    @GetMapping("queryAssetByUserid/{userId}")
    public Response<List<AssetDefine>> queryAssetByUserid(@PathVariable(name = "userId") Long userId){
        return ResponseUtils.any(assetService.queryAssetByUserid(userId));
    }

    /**
     * 查询未绑定的资产
     */
    @GetMapping("queryUnBingDing")
    public Response<List<AssetDefine>> queryUnBingDing(){
        return ResponseUtils.any(assetService.queryUnBingDing());
    }


}
