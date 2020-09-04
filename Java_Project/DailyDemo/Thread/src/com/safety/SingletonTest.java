package com.safety;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SingletonTest
 * @Desc: 使用同步代码块解决单例模式中懒汉式的线程安全问题
 * @package com.safety
 * @project DailyDemo
 * @date 2020/7/29 17:25
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
    }
}

//使用同步代码块解决单例模式中懒汉式的线程安全问题
class Singleton {

    private static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
