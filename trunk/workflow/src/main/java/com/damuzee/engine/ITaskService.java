package com.damuzee.engine;

import com.damuzee.engine.core.Execution;
import com.damuzee.engine.domain.Task;
import com.damuzee.engine.model.FlowModel;
import com.damuzee.engine.model.TaskModel;

import java.util.List;
import java.util.Map;

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
     * 完成指定的任务，删除活动任务记录，创建历史任务
     * @param taskId 任务id
     * @param operator 操作人
     * @param args 参数
     * @return Task 任务对象
     */
    Task complete(String taskId, String operator,Map args);

    /**
     * 根据当前任务对象驳回至上一步处理
     * @param model 流程定义模型，用以获取上一步模型对象
     * @param currentTask 当前任务对象
     * @return Task 任务对象
     */
    Task rejectTask(FlowModel model, Task currentTask);

    /**
     * 根据任务模型、执行对象创建新的任务
     * @param taskModel 任务模型
     * @param execution 执行对象
     * @return List<Task> 创建任务集合
     */
    List<Task> createTask(Map taskModel, Execution execution);
}
