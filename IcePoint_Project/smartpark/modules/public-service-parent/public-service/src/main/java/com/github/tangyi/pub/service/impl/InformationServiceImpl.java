package com.github.tangyi.pub.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.tangyi.common.core.utils.IdGen;
import com.github.tangyi.core.common.web.PageInfo;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.mapper.TPushInformationBaseMapper;
import com.github.tangyi.model.ExamCourse;
import com.github.tangyi.model.TPushInformation;
import com.github.tangyi.pub.service.InformationService;
import dto.TPushInformationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class InformationServiceImpl implements InformationService {

    private TPushInformationBaseMapper tPushInformationBaseMapper;

    @Override
    public PageResult list(Integer pageNum, Integer pageSize, String sort, String order, String title, Long plate) {

        Example example = new Example(TPushInformation.class);
        Page page = PageHelper.startPage(pageSize, pageNum); // 每次查询20条
        PageResult pageResult = new PageResult(pageNum,pageSize);
        List<TPushInformation> list = tPushInformationBaseMapper.selectByExample(example);
        pageResult.setRows(list);
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @Override
    public int del(Long id) {
        TPushInformation tPushInformation = new TPushInformation();
        tPushInformation.setId(id);
        return tPushInformationBaseMapper.delete(tPushInformation);
    }

    @Override
    public ExamCourse get(Long id) {
        return null;
    }

    @Override
    public int save(TPushInformationDto tPushInformationDto) {
        Date date = new Date();
        System.out.println(tPushInformationDto);
        TPushInformation tPushInformation = new TPushInformation();
        tPushInformation.setTitle(tPushInformationDto.getTitle());
        tPushInformation.setPlateId(tPushInformationDto.getPlateId());
        tPushInformation.setPlateName(tPushInformationDto.getPlateName());
        tPushInformation.setPushChannelId(tPushInformationDto.getPushChannelId());
        tPushInformation.setPushChannelName(tPushInformationDto.getPushChannelName());
        tPushInformation.setIsPush(tPushInformationDto.getIsPush());
        tPushInformation.setRegularTime(tPushInformationDto.getRegularTime());
        tPushInformation.setPushTime(tPushInformationDto.getPushTime());
        tPushInformation.setStatus(tPushInformationDto.getStatus());
        tPushInformation.setPushContent(tPushInformationDto.getPushContent());
        tPushInformation.setPolicyPushDepartment(tPushInformationDto.getPolicyPushDepartment());
        tPushInformation.setPolicyNumber(tPushInformationDto.getPolicyNumber());
        tPushInformation.setPolicyTitle(tPushInformationDto.getPolicyTitle());
        tPushInformation.setThemeImage(tPushInformationDto.getThemeImage());
        tPushInformation.setCreateData(date);
        System.out.println(tPushInformation);
        if (tPushInformationDto.getId() == null)
        {
            tPushInformation.setId(IdGen.snowflakeId());
            tPushInformationBaseMapper.insert(tPushInformation);
        }else{
            tPushInformationBaseMapper.updateByPrimaryKey(tPushInformation);
        }

        return 0;
    }
}
