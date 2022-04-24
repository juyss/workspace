package com.github.tangyi.core.common.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 公共线程池
 *
 * @author hedongzhou
 * @since 2019/04/09
 */
public class ThreadPool {

    /**
     * 线程池
     */
    private ThreadPoolTaskExecutor pool;

    /**
     * 基础线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maxPoolSize;

    /**
     * 最大等待队列
     */
    private int queueCapacity;

    /**
     * 是否已经初始化
     */
    private boolean isInit = false;

    public ThreadPool(int corePoolSize,
                      int maxPoolSize,
                      int queueCapacity) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queueCapacity = queueCapacity;
    }

    /**
     * 初始化
     */
    public void init() {
        if (!this.isInit) {
            this.pool = new ThreadPoolTaskExecutor();
            this.pool.setCorePoolSize(this.corePoolSize);
            this.pool.setMaxPoolSize(this.maxPoolSize);
            this.pool.setQueueCapacity(this.queueCapacity);
            this.pool.initialize();
            this.isInit = true;
        }
    }

    /**
     * 执行
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        pool.execute(runnable);
    }

    /**
     * 执行
     *
     * @param threadPoolWait
     * @param runnable
     */
    public void execute(ThreadPoolWait threadPoolWait,
                        Runnable runnable) {
        pool.execute(() -> {
            try {
                runnable.run();
            } finally {
                threadPoolWait.countDown();
            }
        });
    }

}
