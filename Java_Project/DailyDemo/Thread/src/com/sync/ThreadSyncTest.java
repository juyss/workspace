package com.sync;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ThreadSyncTest
 * @Desc:  线程同步
 *      synchronized关键字
 *          方式一:同步代码块()
 *                synchronized(Object object){
 *                    //同步的代码
 *                }
 *                同步监视器(锁):可以是任意一个对象,需要线程共用一把锁
 *                             继承Thread类的多线程可以使用`ClassName.class`
 *                             实现Runnable接口的多线程可使用`this`
 *          方式二:同步方法
 *                synchronized修饰方法
 *                同步监视器(不需要显示声明): 非静态方法,默认为this
 *                                        静态方法,同步监视器变为`ClassName.class`
 *
 * @package com.sync
 * @project DailyDemo
 * @date 2020/7/29 14:17
 */
public class ThreadSyncTest {
    public static void main(String[] args) {
        Window1 window1 = new Window1(100);

        Thread thread1 = new Thread(window1);
        Thread thread2 = new Thread(window1);
        Thread thread3 = new Thread(window1);
        thread1.setName("窗口1:");
        thread2.setName("窗口2:");
        thread3.setName("窗口3:");

        thread1.start();
        thread2.start();
        thread3.start();

        Window2 window2 = new Window2();

        Thread thread4 = new Thread(window2);
        Thread thread5 = new Thread(window2);
        Thread thread6 = new Thread(window2);
        Thread thread7 = new Thread(window2);

        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
    }
}

// 同步代码块
class Window1 implements Runnable{

    private int ticket;

    public Window1(int ticket){
        this.ticket=ticket;
    }

    @Override
    public void run() {
        synchronized (this/* 或者`Window.class` 作为同步监视器*/) {
            while (ticket>0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"-->出票成功,票号为:"+ticket);
                ticket--;
            }
        }
    }
}

//同步方法
class Window2 implements Runnable{

    private int ticket;

    @Override
    public void run() {
        saleTickets();
    }

    public /*synchronized*/ void saleTickets(){
        if (ticket>0){
            System.out.println(Thread.currentThread().getName()+"出票成功,票号:"+ticket);
            ticket--;
        }
    }
}
