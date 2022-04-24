package com.example;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: ProducerAndCustomer
 * @Desc: 生产者消费者问题
 * @package com.example
 * @project DailyDemo
 * @date 2020/7/29 18:37
 */
public class ProducerAndCustomer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Consumer consumer = new Consumer(clerk);
        consumer.setName("消费者");

        Producer producer = new Producer(clerk);
        producer.setName("生产者");

        consumer.start();
        producer.start();
    }
}

//店员
class Clerk {

    private int goodsNum;

    //生产
    public synchronized void produce() throws InterruptedException {
        if (goodsNum < 20) {
            goodsNum++;
            System.out.println(Thread.currentThread().getName() + "生产第" + goodsNum + "个产品");
            notify();
        } else {
            wait();
        }
    }

    //消费
    public synchronized void consume() throws InterruptedException {
        if (goodsNum > 0) {
            System.out.println(Thread.currentThread().getName() + "消费第" + goodsNum + "个产品");
            goodsNum--;
            notify();
        } else {
            wait();
        }
    }
}

//生产者
class Producer extends Thread {

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":生产产品...");
        while (true) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                clerk.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//消费者
class Consumer extends Thread {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":消费商品...");
        while (true) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                clerk.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}