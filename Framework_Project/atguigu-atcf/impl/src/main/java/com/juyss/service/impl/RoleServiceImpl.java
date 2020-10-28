package com.juyss.service.impl;

import com.juyss.bean.TRole;
import com.juyss.bean.TRoleExample;
import com.juyss.mapper.TRoleMapper;
import com.juyss.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RoleServiceImpl
 * @Desc: 角色模块业务层实现类
 * @package com.juyss.service.impl
 * @project atguigu-atcf
 * @date 2020/10/17 18:04
 */
@Service
public class RoleServiceImpl implements RoleService {

    private TRoleMapper mapper;

    @Autowired
    public void setMapper(TRoleMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 查询所有符合条件的用户
     *
     * @param example 条件
     * @return List<TRole> 符合条件的数据集合
     */
    @Override
    public List<TRole> selectByExample(TRoleExample example) {
        return mapper.selectByExample(example);
    }

    /**
     * 删除一条数据
     *
     * @param id 要删除的数据的id
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteSingle(Integer id) {
        boolean flag = false;
        int i = mapper.deleteByPrimaryKey(id);
        if (i==1){
            flag = true;
        }
        return flag;
    }

    /**
     * 批量删除
     *
     * @param ids 要批量删除的id集合
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteBatch(ArrayList<Integer> ids) {
        boolean flag = false;
        int i = mapper.deleteBatch(ids);
        if (i==ids.size()){
            flag = true;
        }
        return flag;
    }
}
