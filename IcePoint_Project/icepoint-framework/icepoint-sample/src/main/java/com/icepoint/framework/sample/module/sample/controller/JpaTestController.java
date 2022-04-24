package com.icepoint.framework.sample.module.sample.controller;

import com.icepoint.framework.sample.module.sample.dao.SampleRepository;
import com.icepoint.framework.sample.module.sample.entity.Sample;
import com.icepoint.framework.web.core.response.CollectionResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import com.icepoint.framework.web.system.dao.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiawei Zhao
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa")
public class JpaTestController {

    private final SampleRepository repository;

    private final ResourceRepository resourceRepository;

    @GetMapping
    public CollectionResponse<Sample> list() {
        return ResponseUtils.many(repository.findAll());
    }

    @PostMapping
    public Response<Sample> save() {

        Sample sample = new Sample();
        sample.setText("jpa save");

        repository.save(sample);

        return ResponseUtils.one(sample);
    }

    @GetMapping("/lazy")
    public Response<Object> lazy() {
        log.info("开始查询");
        Object data = resourceRepository.findById(6L).orElse(null);
        log.info("查询完毕，封装Response");
        Response<Object> response = ResponseUtils.any(data);
        log.info("封装完毕，返回数据");
        return response;
    }
}
