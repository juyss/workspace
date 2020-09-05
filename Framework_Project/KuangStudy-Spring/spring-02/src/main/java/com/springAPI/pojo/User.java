package com.springAPI.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc: 实体类
 * @package com.springAPI.pojo
 * @project KuangStudy-Spring
 * @date 2020/9/1 20:16
 */
@Component
public class User {

    @Value("Juyss")
    public String name;

}
