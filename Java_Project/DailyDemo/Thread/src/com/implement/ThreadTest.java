package com.implement;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ThreadTest
 * @Desc: 实现Runnable接口, 线程共用实现类对象的成员变量
 * @package com.implement
 * @project DailyDemo
 * @date 2020/7/28 21:59
 */
public class ThreadTest {
    public static void main(String[] args) {
        //创建实现类对象
        Window window1 = new Window();

        //创建Thread类的对象,将实现类对象作为参数传入构造器
        Thread thread1 = new Thread(window1);
        Thread thread2 = new Thread(window1);
        Thread thread3 = new Thread(window1);

        //设置线程名
        thread1.setName("窗口1:");
        thread2.setName("窗口2:");
        thread3.setName("窗口3:");

        //调用Thread实例的start(),启动线程
        thread1.start();
        thread2.start();
        thread3.start();

    }
}

/**
 * 实现Runnable接口,实现抽象方法:run().
 */
class Window implements Runnable {
    private int ticket = 100;

//    @Override
//    public void run() {
//        while (ticket > 0) {
//            ticket--;
//            System.out.println(Thread.currentThread().getName() + "-->票号:" + ticket);
//        }
//    }

    //容易出现线程安全问题
    @Override
    public void run() {

        while (true) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket--;
                    System.out.println(Thread.currentThread().getName() + "-->票号:" + ticket);
                } else {
                    break;
                }
        }
    }
}
