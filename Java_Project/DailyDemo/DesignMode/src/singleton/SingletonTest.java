package singleton;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Singleton
 * @Desc: 单例模式, 一个类只能创建一个对象,`懒汉式`和`饿汉式`两种实现方式
 * @package singleton
 * @project DailyDemo
 * @date 2020/7/26 18:45
 */
public class SingletonTest {
    public static void main(String[] args) {

        //饿汉式
        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();
        System.out.println(bank1 == bank2);

        //懒汉式
        Person person1 = Person.getInstance();
        Person person2 = Person.getInstance();
        System.out.println(person1 == person2);
    }
}

//饿汉式
class Bank {

    //1. 私有化类构造器
    private Bank() {
    }

    //2. 内部创建类的实例
    private static Bank instance = new Bank();

    //3. 提供公共的静态方法返回类的实例
    public static Bank getInstance() {
        return instance;
    }

}

//懒汉式
class Person {

    //声明类的对象
    private static Person instance;

    //私有化构造器
    private Person() {
    }

    //提供静态方法获取实例
    public static Person getInstance() {
        if (instance != null) {
            instance = new Person();
        }
        return instance;
    }

}




