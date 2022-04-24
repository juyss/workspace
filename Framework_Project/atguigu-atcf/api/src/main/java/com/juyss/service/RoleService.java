package com.juyss.service;

import com.juyss.bean.TRole;
import com.juyss.bean.TRoleExample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RoleService
 * @Desc: 角色模块业务层接口
 * @package com.juyss.service
 * @project atguigu-atcf
 * @date 2020/10/17 18:03
 */
public interface RoleService {

    /**
     * 查询所有用户
     * @param example 条件
     * @return List<TRole> 符合条件的数据集合
     */
    List<TRole> selectByExample(TRoleExample example);

    /**
     * 删除一条数据
     * @param id 要删除的数据的id
     * @return 是否删除成功
     */
    Boolean deleteSingle(Integer id);

    /**
     * 批量删除
     * @param ids 要批量删除的id集合
     * @return 是否删除成功
     */
    Boolean deleteBatch(ArrayList<Integer> ids);

}
