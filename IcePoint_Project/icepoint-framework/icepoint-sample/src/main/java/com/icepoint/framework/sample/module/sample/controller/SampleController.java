package com.icepoint.framework.sample.module.sample.controller;

import com.icepoint.framework.sample.module.sample.entity.Sample;
import com.icepoint.framework.sample.module.sample.service.SampleService;
import com.icepoint.framework.web.core.support.EntityController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sample")
public class SampleController extends EntityController<SampleService, Sample, Long> {

}
