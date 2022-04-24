package com.icepoint.base.web.resource.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.log.annotation.Log;
import com.icepoint.base.web.resource.task.CertificateStaffTask;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "持证人员到期提醒")
@RequestMapping("certificateStaff")
@RestController
@RequiredArgsConstructor
public class CertificateStaffTaskController {

    @Autowired
    private CertificateStaffTask certificateStaffTask;

    @GetMapping("check")
    @Log("持证人员到期提醒")
    public ResponseBean<Boolean> check() {
        certificateStaffTask.check();
        return new ResponseBean();
    }

}
