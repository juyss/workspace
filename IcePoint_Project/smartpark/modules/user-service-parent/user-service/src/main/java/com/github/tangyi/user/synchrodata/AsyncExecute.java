package com.github.tangyi.user.synchrodata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * api 接口 异步执行，可查询执行状态
 */
@Component
@Slf4j
public class AsyncExecute {
    private ExecutorService pool = Executors.newFixedThreadPool(1);
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询状态
     *
     * @param task
     * @return
     */
    public ExecuteState getExecuteState(Task task) {
        String taskState = null;
        try {
            taskState = this.getTaskState(task);
        } catch (Exception e) {
            log.info(e.toString());
        }
        return new ExecuteState(task.getTaskName(), StateEnum.valueOf(taskState));
    }

    /**
     * 执行任务
     *
     * @param task
     * @return
     */
    public ExecuteState execute(Task task) {
        // 1 根据任务名称，查询是否有正在执行的任务。
        String taskState = this.getTaskState(task);
        StateEnum state = StateEnum.valueOfName(taskState);
        // 2 如果有正在执行的，返回doing 状态
        if (state.equals(StateEnum.doing)) {
            return new ExecuteState(task.getTaskName(), StateEnum.doing);
        }
        // 3 查询已完成的任务，清除该任务记录
        if (state.equals(StateEnum.finish)) {
            this.delTaskState(task);
        }
        // 4 如果没有正在执行的，则执行，并返回doing 状态;
        pool.execute(task);
        return new ExecuteState(task.getTaskName(), StateEnum.doing);
    }

    public static abstract class Task implements Runnable {
        private String taskName;
        private Runnable task;
        private AsyncExecute.StateEnum state;

        public String getTaskName() {
            return taskName;
        }

        public AsyncExecute.StateEnum getState() {
            return state;
        }

        public Task(String taskName, Runnable task) {
            this.task = task;
            this.taskName = taskName;
        }

        @Override
        public void run() {
            this.state = AsyncExecute.StateEnum.doing;
            before(this);
            try {
                task.run();
                this.state = AsyncExecute.StateEnum.finish;
            } catch (Exception e) {
                this.state = AsyncExecute.StateEnum.exception;
            }
            after(this);
        }

        public abstract void before(Task task);

        public abstract void after(Task task);
    }

    public Task createTask(String taskName, Runnable runnable) {
        final AsyncExecute this_ = this;
        return new Task(taskName, runnable) {

            @Override
            public void before(Task task) {
                this_.setTaskState(task);
            }

            @Override
            public void after(Task task) {
                this_.setTaskState(task);
            }
        };
    }

    @Data
    @AllArgsConstructor
    public static class ExecuteState {
        private String taskName;
        private StateEnum state;
    }

    public static enum StateEnum {
        no,
        doing,
        finish,
        exception;

        public static StateEnum valueOfName(String name) {
            for (StateEnum value : StateEnum.values()) {
                if (StringUtils.equals(value.name(), name)) {
                    return value;
                }
            }
            return no;
        }
    }

    //从缓存中取
    public String getTaskState(Task task) {
        String state = null;
        Object o = redisTemplate.opsForValue().get("taskState:" + task.getTaskName());
        if (o == null) {
            state = task.getState() == null ? StateEnum.no.name() : task.getState().name();
            redisTemplate.opsForValue().set("taskState:" + task.getTaskName(), state, 600, TimeUnit.SECONDS);
        } else {
            state = o.toString();
        }
        return state;
    }

    //修改缓存
    public String setTaskState(Task task) {
        String state = task.getState() == null ? StateEnum.no.name() : task.getState().name();
        redisTemplate.opsForValue().set("taskState:" + task.getTaskName(), state, 600, TimeUnit.SECONDS);
        return state;
    }

    //删除缓存
    public void delTaskState(Task task) {
        redisTemplate.delete("taskState:" + task.getTaskName());
    }
}
