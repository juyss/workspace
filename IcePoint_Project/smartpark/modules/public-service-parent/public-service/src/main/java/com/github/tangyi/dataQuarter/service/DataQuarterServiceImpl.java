package com.github.tangyi.dataQuarter.service;

import com.github.tangyi.dataQuarter.mapper.DataQuarterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataQuarterServiceImpl {

    @Autowired
    private DataQuarterMapper dataQuarterMapper;


    public Boolean delete(String ids) {
        String[] split = ids.split(",");
        ArrayList<Long> list = new ArrayList<>();
        for (String id : split) {
            list.add(Long.valueOf(id));
        }
        Integer flag = 0;
        for (Long id : list) {
            flag += dataQuarterMapper.delete(id);
        }
        if(flag==list.size()) return true;
        return false;
    }
}
