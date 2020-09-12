package com.juyss.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc: 实体类
 * @package com.juyss.pojo
 * @project KuangStudy-SpringMVC
 * @date 2020/9/12 17:15
 */
@Component
public class User {

    private Integer id;
    private String name;
    private String pwd;

    public User() {
    }

    public User(Integer id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
