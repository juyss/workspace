package com.example;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: JudgeAndAthlete
 * @Desc: 三个运动员同时开始比赛, 由裁判记录完成顺序
 * @package com.example
 * @project DailyDemo
 * @date 2020/9/2 20:46
 */
public class JudgeAndAthlete {
    public static void main(String[] args) {
        Athlete athlete = new Athlete();
        Judge judge = new Judge();

        Thread thread= new Thread(judge);
        Thread thread01 = new Thread(athlete);
        Thread thread02 = new Thread(athlete);
        Thread thread03 = new Thread(athlete);

        thread.setName("裁判");
        thread01.setName("1号运动员");
        thread02.setName("2号运动员");
        thread03.setName("3号运动员");

        thread.start();
        thread01.start();
        thread02.start();
        thread03.start();

    }
}

class Athlete implements Runnable {

    @Override
    public void run() {
        for (int i = 10000; i >= 0; i--) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
        notifyAll();
    }
}

class Judge implements Runnable {

    @Override
    public void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("运动员完成比赛");
    }
}
