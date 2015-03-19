package com.damuzee.engine.core;

import com.damuzee.common.util.DateUtil;
import com.damuzee.engine.util.Assert;
import com.damuzee.engine.IQueryService;
import com.damuzee.engine.ITaskService;
import com.damuzee.engine.domain.History;
import com.damuzee.engine.domain.Task;
import com.damuzee.engine.model.FlowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karka.w on 2014/12/8.
 */
@Service
public class TaskServiceImpl  extends ReposImpl  implements ITaskService {

    Logger logger = Logger.getLogger("TaskServiceImpl");

    /**
     * 状态；活动状态
     */
    public static final Integer STATE_ACTIVE = 1;
    /**
     * 状态：结束状态
     */
    public static final Integer STATE_FINISH = 0;
    /**
     * 状态：终止状态
     */
    public static final Integer STATE_TERMINATION = 2;


    @Autowired
    IQueryService queryService ;

    @Override
    public Task complete(String taskId, String operator) {
        return null;
    }

    /**
     * 完成指定任务
     * 该方法仅仅结束活动任务，并不能驱动流程继续执行
     *
     * @param taskId 任务id
     * @param operator 操作人
     * @param map
     * @return
     */
    public Task complete(String taskId, String operator,Map map) {

        Task task = queryService.getTask(taskId);
        Assert.notNull(task, "指定的任务[id=" + taskId + "]不存在");
        //task.setVariable(JsonHelper.toJson(args));
       /* if(!isAllowed(task, operator)) {
            throw new Exception("当前参与者[" + operator + "]不允许执行任务[taskId=" + taskId + "]");
        }*/
        History history = new History(task);
        history.put(History.FINISH_TIME, DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME));
        history.put(History.TASK_STATE,STATE_FINISH);
        history.put(History.OPERATOR, operator);

        saveHistory(history);
        deleteTask(taskId);

        return task;
    }

    @Override
    public Task rejectTask(FlowModel model, Task currentTask) {
        return null;
    }

    @Override
    public List<Task> createTask(Map taskModel, Execution execution) {
        logger.log(Level.INFO,"----------------检查是否存在相同的任务---------------------+++++" );

        logger.log(Level.INFO,"----------------创建一个新的任务---------------------+++++" );

        Task task = new Task();
        task.warp(taskModel,execution);

        //获取任务处理的用户
        List useList = queryService.getActorUser(taskModel);
        task.put(Task.ACTOR_IDS,useList) ;

        saveTask(task);

        List<Task> list  = new ArrayList<Task>();
        list.add(task);
        return list;
    }
}
