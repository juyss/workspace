package com.github.tangyi.webcast.service;

import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.webcast.api.model.ChannelDept;
import com.github.tangyi.webcast.api.model.Dept;
import com.github.tangyi.webcast.mapper.ChannelDeptMapper;
import com.github.tangyi.webcast.mapper.DeptMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class DeptService extends CrudService<DeptMapper, Dept> {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private ChannelDeptMapper channelDeptMapper;

    /**
     * 查出id对应的下级部门
     * @param depetId
     * @return
     */
    List<Long> deptParentList(Long depetId){
      return  deptMapper.deptParentList(depetId);
    }

    public List<ChannelDept> selectChonnelIds(List<Long> deptParentList) {
        return channelDeptMapper.listByDeptIdList(deptParentList);
    }


    public List<Dept> selectDeptByid(Long id) {
        return deptMapper.selectDeptByid(id);
    }
}
