package com.juyss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc: 实体类
 * @package com.juyss.pojo
 * @project atguigu-Advanced
 * @date 2020/12/1 13:47
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{

    //ID
    private Integer id;

    //用户名
    private String name;

    //密码
    private String password;

}
