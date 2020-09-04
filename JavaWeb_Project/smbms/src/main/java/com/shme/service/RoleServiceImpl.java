package com.shme.service;

import com.shme.dao.RoleDAO;
import com.shme.pojo.Role;
import com.shme.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RoleServiceImpl
 * @Desc: 角色service层接口实现类
 * @package com.shme.service
 * @project smbms
 * @date 2020/7/10 15:52
 */
public class RoleServiceImpl implements RoleService {

    /**
     * 获取用户角色列表
     * @return List<Role> 用户列表
     */
    @Override
    public List<Role> getRoleList() {
        SqlSession sqlSession = null;
        List<Role> roleList = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            RoleDAO mapper = sqlSession.getMapper(RoleDAO.class);
            roleList = mapper.getRoleList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return roleList;
    }
}
