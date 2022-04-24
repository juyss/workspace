package com.icepoint.framework.workorder.work.service;

import com.icepoint.framework.workorder.config.agilebpm.constant.TaskAction;
import com.icepoint.framework.workorder.config.agilebpm.model.Task;

import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
public interface AgileBpmRestService {

    /**
     * 启动对应流程定义id的流程实例
     *
     * @param defId 流程定义id
     * @param data  启动流程的数据
     * @return 启动后的流程实例id
     */
    String startNewFlowInstance(String defId, Object data);

    /**
     * 根据当前用户、主表数据id，获取当前用户的任务数据
     * 如果当前用户没有对应任务，则返回empty
     *
     * @param objectId 主表数据id
     * @return 任务信息
     */
    Optional<Task> getTaskByObjectId(Long objectId);

    /**
     * 执行任务动作
     *
     * @param task    要执行的任务
     * @param action  要执行的动作
     * @param opinion 执行意见
     */
    void doTaskAction(Task task, TaskAction action, String opinion);

}
