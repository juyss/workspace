package com.springAPI.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Dog
 * @Desc:
 * @package com.springAPI.pojo
 * @project KuangStudy-Spring
 * @date 2020/9/3 16:27
 */
public class Dog {
    private String name;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void shut(){
        System.out.println("wang~");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
