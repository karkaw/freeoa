package com.damuzee.engine;

import com.damuzee.engine.domain.Task;
import com.damuzee.engine.model.ProcessModel;

/**
 * 任务实例的操作类
 *
 * Created by karka.w on 2014/12/5.
 */
public interface ITaskService {
    /**
     * 完成指定的任务，删除活动任务记录，创建历史任务
     * @param taskId 任务id
     * @param operator 操作人
     * @return Task 任务对象
     */
    Task complete(String taskId, String operator);

    /**
     * 根据当前任务对象驳回至上一步处理
     * @param model 流程定义模型，用以获取上一步模型对象
     * @param currentTask 当前任务对象
     * @return Task 任务对象
     */
    Task rejectTask(ProcessModel model, Task currentTask);
}
