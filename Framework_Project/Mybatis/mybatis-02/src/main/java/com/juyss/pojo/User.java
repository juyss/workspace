package com.juyss.pojo;


import org.apache.ibatis.type.Alias;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc:
 * @package com.juyss.pojo
 * @project Mybatis
 * @date 2020/8/26 18:35
 */
@Alias("alias")
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
