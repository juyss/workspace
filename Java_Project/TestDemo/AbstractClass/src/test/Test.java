package test;

import pojo.Person;
import pojo.Student;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: test.Test
 * @Desc:   抽象类的抽象方法在实例化的时候需要实现,
 *          protected修饰的方法只能被在当前包内的其他类和继承子类中被调用
 *          默认的方法权限(default)只能被当前包内的其它类调用
 * @package PACKAGE_NAME
 * @project TestDemo
 * @date 2020/7/20 16:20
 */
public class Test {
    public static void main(String[] args) {
        Person person01 = new Person() {
            @Override
            public String getDesc() {
                return "匿名";
            }
        };

        person01.setName("Morty");
        String name = person01.getName();
        String desc = person01.getDesc();
        System.out.println(name);
        System.out.println(desc);
        System.out.println(person01.getClass());

        System.out.println("*************");

        Student student = new Student();
        System.out.println(student);
    }
}
