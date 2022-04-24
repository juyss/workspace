package com.icepoint.framework.code.utils.create;

import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 生成代码请求
 * @author Administrator
 */
@RestController
@RequestMapping("create")
public class CreateCodeController {
    @Resource
    private AbsCreateProjectTemplate absCreateProjectTemplate ;

    /**
     * 生成代码
     */
    @PostMapping("createCode/{id}")
    public Response<Boolean> createCode(@PathVariable("id") Long id){
        absCreateProjectTemplate.creat(id);
        return ResponseUtils.any(true);
    }
}
