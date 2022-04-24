package com.github.tangyi.dataYear.service;

import com.github.tangyi.dataYear.mapper.DateYearMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class DateYearServiceImpl {
    @Autowired
    private DateYearMapper dateYearMapper;

    public Integer deleteByid(Long id) {
        return this.dateYearMapper.deleteByid(id);
    }
}
