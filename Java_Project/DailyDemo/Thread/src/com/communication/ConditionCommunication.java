package com.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ConditionCommunication
 * @Desc:
 * @package com.communication
 * @project DailyDemo
 * @date 2020/11/10 18:35
 */
public class ConditionCommunication {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 52; i+=2) {
                    share.printNum(i);
                }
            }
        }, "数字线程").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                char word = 65;
                for (int i = 0; i < 26; i++) {
                    share.printWord(word++);
                }
            }
        }, "字母线程").start();
    }
}

class Share{

    private int signal = 0; // 0:打印数字 1:打印字母

    private final Lock lock = new ReentrantLock();

    private final Condition numCondition = lock.newCondition();
    private final Condition wordCondition = lock.newCondition();

    public void printNum(int i){
        lock.lock();
        try {
            while (signal != 0){
                numCondition.await();
            }
            System.out.println(i);
            System.out.println(++i);
            signal=1;
            wordCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printWord(char word){
        lock.lock();
        try {
            while (signal != 1){
                wordCondition.await();
            }
            System.out.println(word);
            signal=0;
            numCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


