package com.juyss.service;

import com.juyss.bean.TAdmin;
import com.juyss.bean.TAdminExample;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AdminService
 * @Desc: 管理员业务层
 * @package com.juyss.service
 * @project atguigu-atcf
 * @date 2020/10/14 14:42
 */
public interface AdminService {

    /**
     * 获取分页后的管理员信息
     * @return List<TAdmin>
     */
    List<TAdmin> getAdmin();

    /**
     * 条件查询
     * @param example 条件对象
     * @return List<TAdmin>
     */
    List<TAdmin> selectByExample(TAdminExample example);

    /**
     * 根据管理员id查询
     * @param id 管理员id
     * @return 符合条件的管理员
     */
    TAdmin selectAdmin(Integer id);

    /**
     * 添加一条数据
     * @param admin 要添加的对象
     * @return Boolean 是否添加成功
     */
    Boolean insertAdmin(TAdmin admin);

    /**
     * 修改一条数据
     * @param admin 要修改的的信息对象
     * @return Boolean 是否修改成功
     */
    Boolean updateAdmin(TAdmin admin);

    /**
     * 删除单条数据
     * @param id 要删除的admin的Id
     * @return Boolean 是否修改成功
     */
    Boolean deleteSingle(Integer id);

    /**
     * 删除一批数据
     * @param ids 要删除的数据的id数组
     * @return Boolean 是否删除成功
     */
    Boolean deleteBatch(ArrayList<Integer> ids);

}
