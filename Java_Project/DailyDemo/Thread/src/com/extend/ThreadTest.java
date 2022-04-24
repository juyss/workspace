package com.extend;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ThreadTest
 * @Desc:  需要开启线程的类继承Thread类,重写run()方法,在主线程中创建类的对象,调用start()方法,即可开启新的线程
 * @package com.extend
 * @project DailyDemo
 * @date 2020/7/28 16:45
 */
public class ThreadTest {
    public static void main(String[] args) {
        //创建子类对象
        Sale sale1 = new Sale();
        Sale sale2 = new Sale();
        Sale sale3 = new Sale();

        //调用start()方法,启动一个线程
        sale1.start();
        sale2.start();
        sale3.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

/**
 * 继承Thread类,重写run()方法
 */
class Sale extends Thread{
    private static int ticket = 500;

    @Override
    public void run() {
        while (ticket>0){
            ticket=ticket-1;
            System.out.println(currentThread().getName()+"-->余票:"+ticket);
        }
    }
}
