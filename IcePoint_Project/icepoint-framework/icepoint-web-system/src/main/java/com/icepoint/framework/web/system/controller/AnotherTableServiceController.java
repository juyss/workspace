package com.icepoint.framework.web.system.controller;

import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.entity.TableService;
import com.icepoint.framework.web.system.entity.TableServiceProcess;
import com.icepoint.framework.web.system.service.TableServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-16 11:57
 */
@RestController
@RequestMapping("ats")
public class AnotherTableServiceController {

    /**
     * 服务对象
     */
    @Resource
    private TableServiceService service;

    /**
     * 根据资源ID查询表服务
     * @author Juyss
     * @param resId 资源id
     * @param pageable 分页参数
     * @return PageResponse<TableService>
     */
    @GetMapping("getByResId")
    public PageResponse<TableService> pageByResourceId(@RequestParam("resId") Long resId, Pageable pageable){
        Page<TableService> page = service.findAllByResourceId(resId, pageable);
        return ResponseUtils.page(page);
    }

    /**
     * 根据表ID查询process
     *
     * @author Juyss
     * @param tabId 表ID
     * @return CollectionResponse<TableServiceProcess>
     */
    @GetMapping("getByTabId")
    public CollectionResponse<TableServiceProcess> getByTabId(@RequestParam("tabId") Long tabId){
        List<TableServiceProcess> processByTableId = service.getProcessByTableId(tabId);
        return ResponseUtils.many(processByTableId);
    }
}
