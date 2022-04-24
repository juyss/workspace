package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ThreadTest
 * @Desc: //线程池创建多线程
 *              1.便于管理
 *              2.节省资源
 *              3.
 * @package com.threadpool
 * @project DailyDemo
 * @date 2020/7/29 19:59
 */
public class ThreadTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 50; i++) {
                threadPool.execute(()-> System.out.println(Thread.currentThread().getName()+"\t 执行操作"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
