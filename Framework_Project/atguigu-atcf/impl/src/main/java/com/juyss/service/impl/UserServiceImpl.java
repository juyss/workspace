package com.juyss.service.impl;

import com.juyss.bean.TAdmin;
import com.juyss.bean.TAdminExample;
import com.juyss.mapper.TAdminMapper;
import com.juyss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @Desc: user业务层实现类
 * @package com.juyss.service.impl
 * @project atguigu-CrowdFunding
 * @date 2020/10/11 18:21
 */
@Service
public class UserServiceImpl implements UserService {

    private TAdminMapper mapper;

    @Autowired
    public void setMapper(TAdminMapper mapper) {
        this.mapper = mapper;
    }

    public TAdminMapper getMapper() {
        return mapper;
    }


    /**
     * 根据条件查询用户
     *
     * @param map 条件封装载体
     * @return 符合条件的用户
     */
    @Override
    public TAdmin getUserByMap(Map<String, Object> map) {

        String loginacct = (String) map.get("loginacct");
        TAdminExample example = new TAdminExample();

        example.createCriteria().andLoginacctEqualTo(loginacct);

        List<TAdmin> list = mapper.selectByExample(example);

        TAdmin user =null;
        if (list != null && list.size() ==1){
            user = list.get(0);
        }

        return user;
    }
}
