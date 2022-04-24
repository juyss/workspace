package com.github.tangyi.pub.service;

import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.model.ExamCourse;
import com.github.tangyi.model.TPushInformation;
import dto.TPushInformationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformationService{
    PageResult list(Integer pageNum, Integer pageSize, String sort, String order, String name, Long plate);

    int del(Long id);

    ExamCourse get(Long id);

    int save(TPushInformationDto tPushInformationDto);
}
