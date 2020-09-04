package com.juyss.pojo;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Hello
 * @Desc: bean
 * @package com.juyss.pojo
 * @project KuangStudy-Spring
 * @date 2020/9/1 11:16
 */
public class Hello {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("Hello,"+ name );
    }
}
