package com.communication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ImplCommunication
 * @Desc:
 * @package com.communication
 * @project DailyDemo
 * @date 2020/11/10 11:17
 */
public class ImplCommunication {

    public static void main(String[] args) {
        Data data = new Data();

        PrintNum printNum = new PrintNum(data);
        PrintWord printWord = new PrintWord(data);

        Thread numThread = new Thread(printNum, "数字线程");
        Thread wordThread = new Thread(printWord, "字母线程");

        wordThread.start();
        numThread.start();
    }
}

class PrintNum implements Runnable {

    private final Data data;

    public PrintNum(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            data.printNum();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintWord implements Runnable {

    private final Data data;

    public PrintWord(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            data.printWord();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Data {
    private int signal = 0;

    private final Lock lock = new ReentrantLock();

    public synchronized void printNum() throws InterruptedException {
        for (int i = 1; i <= 52; i = i + 2) {
            while (signal != 0){
                wait();
            }
            System.out.println("线程名:" + Thread.currentThread().getName() + " 值:" + i);
            System.out.println("线程名:" + Thread.currentThread().getName() + " 值:" + (i + 1));
            signal = 1;
            notifyAll();
        }
    }

    public synchronized void printWord() throws InterruptedException {
        char word = 65;
        for (int i = 0; i < 26; i++) {
            while (signal == 0){
                wait();
            }
            System.out.println("线程名:" + Thread.currentThread().getName() + " 值:" + word++);
            signal = 0;
            notifyAll();
        }
    }

}
