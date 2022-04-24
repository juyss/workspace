package com.icepoint.framework.sample.module.sample.controller;

import com.icepoint.framework.sample.module.sample.dao.SampleMapper;
import com.icepoint.framework.sample.module.sample.dao.SampleRepository;
import com.icepoint.framework.sample.module.sample.dao.TeacherMapper;
import com.icepoint.framework.sample.module.sample.dao.TeacherRepository;
import com.icepoint.framework.sample.module.sample.entity.Sample;
import com.icepoint.framework.sample.module.sample.entity.Teacher;
import com.icepoint.framework.web.core.response.PageResponse;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@RequestMapping("/mybatis")
@RestController
public class MybatisTestController {

    private final SampleMapper mapper;

    private final SampleRepository repository;

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    @GetMapping("/{id}")
    public Response<Sample> findById(@PathVariable("id") Long id) {
        return ResponseUtils.any(mapper.findById(id).orElse(null));
    }

    @GetMapping
    public PageResponse<Sample> findAll(Pageable pageable) {
        return ResponseUtils.page(mapper.findAll(pageable));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @PostMapping("/trans")
    public Response<Sample> trans() {

        Sample sample = Sample.builder()
                .text("trans")
                .build();

        repository.save(sample);
        assert sample.getId() != null;

        Sample result = mapper.findById(sample.getId())
                .orElseThrow(IllegalStateException::new);

        return ResponseUtils.any(result);
    }

    @GetMapping("/security/{id}")
    public Response<Teacher> findByIdForSecurity(@PathVariable("id") Long id) {
        return ResponseUtils.any(teacherMapper.findById(id).orElse(null));
    }
}
