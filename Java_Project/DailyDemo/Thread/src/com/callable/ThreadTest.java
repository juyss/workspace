package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ThreadTest
 * @Desc:  实现Callable接口创建多线程
 *              1.实现Callable接口,实现call()方法
 *              2.创建实现类对象
 *              3.将实现类对象传入FutureTask类构造器中,创建FutureTask对象
 *              4.将FutureTask对象传入Thread类构造器中,创建Thread对象
 *              5.调用Thread类的start()方法
 *              6.调用FutureTask.get()方法获取call()方法的返回值
 * @package com.callable
 * @project DailyDemo
 * @date 2020/7/29 19:18
 */
public class ThreadTest {
    public static void main(String[] args) {
        //分线程
        NumSum numSum = new NumSum();
        FutureTask futureTask = new FutureTask(numSum);
        Thread thread = new Thread(futureTask);
        thread.setName("2线程-->");
        thread.start(); //启动线程

        // 主线程
        Thread.currentThread().setName("1线程-->");
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                sum += i;
                System.out.println(Thread.currentThread().getName()+i + ":" + sum);
            }
        }
        System.out.println("main():1线程结果:"+sum);
        try {
            Object callSum = futureTask.get();
            System.out.println("main():2线程结果:"+callSum);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

class NumSum implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                sum += i;
                System.out.println(Thread.currentThread().getName()+i + ":" + sum);
            }
        }
        System.out.println("call():2线程结果:"+sum);
        return sum;
    }
}