package com.atguigu.gmall.jucdemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xialei
 * <p>
 * 在信号量上我们定义两种操作：
 * acquire（获取） 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），
 * 要么一直等下去，直到有线程释放信号量，或超时。
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 * <p>
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 * @Description: TODO(这里用一句话描述这个类的作用)
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        //三个停车位
        Semaphore sp = new Semaphore(3);


        //停六个汽车
        for (int i = 1; i <=6 ; i++) {
           new Thread(()->{

                   try {
                       sp.acquire();
                       System.out.println(Thread.currentThread().getName()
                               +"\t号车驶入停车位");
                       TimeUnit.SECONDS.sleep(3);
                       System.out.println(Thread.currentThread().getName()
                               +"\t号车驶出停车位");
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } finally {
                       sp.release();
                   }


           },String.valueOf(i)).start();
        }



    }
}
