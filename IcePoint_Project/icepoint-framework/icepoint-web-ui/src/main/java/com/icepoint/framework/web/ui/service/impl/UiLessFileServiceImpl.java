package com.icepoint.framework.web.ui.service.impl;

import com.icepoint.framework.web.ui.entity.UiLessFile;
import com.icepoint.framework.web.ui.mapper.UiLessFileMapper;
import com.icepoint.framework.web.ui.service.UiLessFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UiLessFileServiceImpl implements UiLessFileService {
    @Resource
    private UiLessFileMapper mapper;

    @Override
    public boolean save(UiLessFile uiLessFile) {
        return mapper.insert(uiLessFile) > 0;
    }

    @Override
    public Boolean delete(Long id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Boolean update(UiLessFile uiLessFile) {
        return  mapper.update(uiLessFile) > 0;
    }


}
