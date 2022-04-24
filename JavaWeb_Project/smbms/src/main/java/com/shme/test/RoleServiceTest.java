package com.shme.test;

import com.shme.dao.RoleDAO;
import com.shme.pojo.Role;
import com.shme.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RoleServiceTest
 * @Desc: RoleService测试
 * @package com.shme.test
 * @project smbms
 * @date 2020/8/29 17:01
 */
public class RoleServiceTest {

    /**
     * 测试RoleDao接口的getRoleList()
     */
    @Test
    public void getRoleListTest(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSqlSession();
            RoleDAO mapper = sqlSession.getMapper(RoleDAO.class);
            List<Role> roleList = mapper.getRoleList();
            for (Role role : roleList) {
                System.out.println(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
