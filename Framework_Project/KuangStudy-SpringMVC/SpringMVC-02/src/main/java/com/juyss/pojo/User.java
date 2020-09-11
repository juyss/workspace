package com.juyss.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc: 实体类
 * @package com.juyss.pojo
 * @project KuangStudy-SpringMVC
 * @date 2020/9/11 21:44
 */
@Component
public class User {

    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
