package com.springAPI.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Admin
 * @Desc:
 * @package com.springAPI.pojo
 * @project KuangStudy-Spring
 * @date 2020/9/2 11:58
 */
@Component
public class Admin {
    private String name;

    public String getName() {
        return name;
    }

    @Value("Admin")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                '}';
    }
}
