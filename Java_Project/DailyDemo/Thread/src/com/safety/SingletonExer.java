package com.safety;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SingletonExer
 * @Desc:  单例模式懒汉式练习
 * @package com.safety
 * @project DailyDemo
 * @date 2020/7/30 10:01
 */
public class SingletonExer {
    public static void main(String[] args) {
        Bank instance1 = Bank.getInstance();
        Bank instance2 = Bank.getInstance();
        Bank instance3 = Bank.getInstance();

        System.out.println(instance1==instance2);
        System.out.println(instance1==instance3);
        System.out.println(instance3==instance2);
    }
}

//单例模式的懒汉式线程安全问题解决
class Bank{

    private static Bank instance;

    private Bank(){}

    public static Bank getInstance(){
        if (instance==null) {
            synchronized(Bank.class){
                if (instance==null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}