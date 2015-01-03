package com.damuzee.engine.handlers.imp;

import com.damuzee.engine.core.Execution;
import com.damuzee.engine.domain.Task;
import com.damuzee.engine.handlers.IHandler;
import com.damuzee.engine.model.TaskModel;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 14-12-19.
 */
public class CreateTaskHandler implements IHandler {
    /**
     * 任务模型
     */
    private Map model;

    /**
     * 调用者需要提供任务模型
     * @param model
     */
    public CreateTaskHandler(Map model) {
        this.model = model;
    }

    /**
     * 根据任务模型、执行对象，创建下一个任务，并添加到execution对象的tasks集合中
     */
    public void handle(Execution execution) {
        List<Task> tasks = execution.getEngine().task().createTask(model, execution);
        execution.addTasks(tasks);
        //从服务上下文中查找任务拦截器列表，依次对task集合进行拦截处理

    }
}
