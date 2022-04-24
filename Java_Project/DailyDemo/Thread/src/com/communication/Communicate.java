package com.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Communicate
 * @Desc: 线程间通信
 * @package com.communication
 * @project DailyDemo
 * @date 2020/11/9 16:15
 */
public class Communicate {
    public static void main(String[] args) {
        Print print = new Print();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 52; i = i + 2) {
                    print.printNum(i);
                }
            }
        }, "数字线程:").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                char word = 65;
                for (int i = 0; i < 26; i++) {
                    print.printWord(word++);
                }
            }
        }, "字母线程:").start();
    }
}

//打印数字操作类
class Print {

    private int signal = 0;

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public void printNum(int i) {
        lock.lock();
        try {
            while (signal != 0) {
                condition.await();
            }
            System.out.println("线程名:" + Thread.currentThread().getName() + " 值:" + i);
            System.out.println("线程名:" + Thread.currentThread().getName() + " 值:" + (i + 1));
            signal = 1;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printWord(char word) {
        lock.lock();
        try {
            while (signal == 0) {
                condition.await();
            }
            System.out.println("线程名:" + Thread.currentThread().getName() + " 值:" + word);
            signal = 0;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
