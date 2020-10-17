package com.juyss.service.impl;

import com.juyss.bean.TAdmin;
import com.juyss.bean.TAdminExample;
import com.juyss.mapper.TAdminMapper;
import com.juyss.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AdminServiceImpl
 * @Desc: 管理员业务层实现类
 * @package com.juyss.service.impl
 * @project atguigu-atcf
 * @date 2020/10/14 14:43
 */
@Service
public class AdminServiceImpl implements AdminService {

    private TAdminMapper mapper;

    @Autowired
    public void setMapper(TAdminMapper mapper) {
        this.mapper = mapper;
    }


    /**
     * 获取所有管理员信息
     *
     * @return List<TAdmin>
     */
    @Override
    public List<TAdmin> getAdmin() {
        TAdminExample example = new TAdminExample();
        example.createCriteria();
        return mapper.selectByExample(example);
    }

    /**
     * 条件查询
     *
     * @param example 条件对象
     * @return List<TAdmin>
     */
    @Override
    public List<TAdmin> selectByExample(TAdminExample example) {
        return mapper.selectByExample(example);
    }

    /**
     * 根据管理员id查询
     *
     * @param id 管理员id
     * @return 符合条件的管理员
     */
    @Override
    public TAdmin selectAdmin(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 添加一条数据
     *
     * @param admin 要添加的对象
     * @return Boolean 是否添加成功
     */
    @Override
    public Boolean insertAdmin(TAdmin admin) {
        boolean flag = false;
        int i = mapper.insert(admin);
        if (i==1){
            flag=true;
        }
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @param admin 要修改的的信息对象
     * @return Boolean 是否修改成功
     */
    @Override
    public Boolean updateAdmin(TAdmin admin) {
        boolean flag = false;
        if (admin.getId()!=null) {
            int i = mapper.updateByPrimaryKey(admin);
            if (i==1){
                flag=true;
            }
        }
        return flag;
    }

    /**
     * 删除单条数据
     *
     * @param id 要删除的admin的Id
     * @return Boolean 是否修改成功
     */
    @Override
    public Boolean deleteSingle(Integer id) {
        boolean flag = false;
        int i = mapper.deleteByPrimaryKey(id);
        if (i==1){
            flag=true;
        }
        return flag;
    }

    /**
     * 删除一批数据
     *
     * @param ids 要删除的数据的id数组
     * @return Boolean 是否删除成功
     */
    @Override
    public Boolean deleteBatch(ArrayList<Integer> ids) {
        boolean flag = false;
        int i = mapper.deleteBatch(ids);
        if (i==ids.size()){
            flag=true;
        }
        return flag;
    }
}
