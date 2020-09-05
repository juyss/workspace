package com.springAPI.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Cat
 * @Desc:
 * @package com.springAPI.pojo
 * @project KuangStudy-Spring
 * @date 2020/9/3 16:27
 */
public class Cat {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void shut(){
        System.out.println("miao~");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
