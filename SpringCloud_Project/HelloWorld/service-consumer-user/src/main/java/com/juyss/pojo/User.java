package com.juyss.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc:
 * @package com.juyss.pojo
 * @project HelloWorld
 * @date 2020/10/29 21:28
 */
@Component
public class User {
    private Integer id;
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
