package com.springAPI.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: User
 * @Desc:
 * @package com.springAPI.pojo
 * @project KuangStudy-Spring
 * @date 2020/9/2 22:18
 */
public class User {

    private String name;

    public User() {
        System.out.println("User的无参构造方法");
    }

    public User(String name) {
        System.out.println("User的有参构造方法");
        this.name = name;
    }

    public void setName(String name) {
        System.out.println("进入了set方法,name="+name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public void show(){
        System.out.println("name-->"+name);
    }
}
