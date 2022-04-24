package com.github.tangyi.core.common.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 线程等待
 *
 * @author hedongzhou
 * @since 2019/04/09
 */
public class ThreadPoolWait {

    /**
     * 计数
     */
    private CountDownLatch countDownLatch;

    public ThreadPoolWait(int size) {
        this.countDownLatch = new CountDownLatch(size);
    }

    /**
     * 减少
     */
    public void countDown() {
        this.countDownLatch.countDown();
    }

    /**
     * 等待
     */
    public void await() {
        try {
            this.countDownLatch.await();
        } catch (Exception e) {
        }
    }

}
