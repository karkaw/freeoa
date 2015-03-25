package com.damuzee.engine;

import com.damuzee.engine.core.Execution;
import com.damuzee.engine.domain.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/12/4.
 */
public interface WFEngine {

    /**
     * 获取flow服务
     * @return IFlowService 流程定义服务
     */
    public IFlowService flow();

    /**
     * 获取查询服务
     * @return IQueryService 常用查询服务
     */
    public IQueryService query();

    /**
     * 获取实例服务
     * @return IQueryService 流程实例服务
     */
    public IOrderService order();

    /**
     * 获取任务服务
     * @return ITaskService 任务服务
     */
    public ITaskService task();

    /**
     *  根据工作流定义启动工作流
     * @param id       实例流水号
     * @param operner  操作人
     * @return
     */
    public Map startInstanceById(String id,String operner);

    /**
     *  根据工作流定义启动工作流
     * @param name       实例编号
     * @param operner  操作人
     * @return
     */
    public Map startInstanceByName(String name, String operner,Map args);

    /**
     * 同意操作处理
     *
     * @param taskId
     * @param operator
     * @param args
     * @return
     */
    public List<Task> executeTask(String taskId, String operator, Map<String, Object> args);
    
    /**
     * 跳转任务
     * 
     * nodeName 为null时，拒绝到上一步 。
     *
     * @param taskId   任务Id
     * @param operator 操作人
     * @param args
     * @param nodeName 当前任务名称
     * @return
     */
    public List<Task> executeToTask(String taskId, String operator, Map<String, Object> args, String nodeName);

}
