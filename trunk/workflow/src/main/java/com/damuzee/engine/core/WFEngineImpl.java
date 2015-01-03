package com.damuzee.engine.core;

import com.damuzee.common.util.DateUtil;
import com.damuzee.engine.*;
import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Order;
import com.damuzee.engine.domain.Task;
import com.damuzee.engine.model.FlowModel;
import com.damuzee.engine.model.NodeModel;
import com.damuzee.engine.model.StartModel;
import com.damuzee.engine.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2014/12/8.
 */
@Service
public class WFEngineImpl implements WFEngine {

    Logger logger = Logger.getLogger("WFEngineImpl");

    @Autowired
    IFlowService processService ;

    @Autowired
    IQueryService queryService ;

    @Autowired
    IOrderService orderService ;

    @Autowired
    ITaskService taskService ;

    public IFlowService flow() {

        return processService;
    }

    public IQueryService query() {
        return queryService;
    }

    public IOrderService order() {
        return orderService;
    }

    public ITaskService task() {
        return taskService;
    }

    public Map startInstanceById(String id, String operner) {
        Map<String,Object> flow = flow().getFlowById(id);

        for (String key : flow.keySet()){
            System.out.println(key);
        }

        return null;
    }

    /**
     * 启动执行流程
     * @param name       实例编号
     * @param operner  操作人
     * @param args
     * @return
     */
    public Map startInstanceByName(String name, String operner,Map args) {
        //获取定义
        Flow flow = flow().getFlowByCode(name);

        //检查流程定义的状态

        //获取执行器
        Execution execution = execute(flow, operner, args, null, null);

        //获取StartModel模型
        StartModel start = flow.getModel().getStart();
        start.execute(execution);

        return null;
    }

    /**
     * 创建流程实例，并返回执行对象
     * @param flow 流程定义
     * @param operator 操作人
     * @param args 参数列表
     * @param parentId 父流程实例id
     * @param parentNodeName 启动子流程的父流程节点名称
     * @return Execution
     */
    private Execution execute(Flow flow, String operator, Map<String, Object> args,
                              String parentId, String parentNodeName) {
        Order order = order().createOrder(flow, operator, args, parentId, parentNodeName);

        Execution current = new Execution(this, flow, order, args);

        current.setOperator(operator);
        return current;
    }

    /**
     * 根据任务主键ID，操作人ID，参数列表执行任务
     */
    public List<Task> executeTask(String taskId, String operator, Map<String, Object> args) {
        //完成任务，并且构造执行对象
        Execution execution = execute(taskId, operator, args);
        if(execution == null) return Collections.emptyList();
        FlowModel model = execution.getFlow().getModel();
        if(model != null) {
            NodeModel nodeModel = model.getNodeByName(execution.getTask().getString(Task.TASK_NAME));
            //将执行对象交给该任务对应的节点模型执行
            nodeModel.execute(execution);
        }
        return execution.getTasks();
    }

    /**
     * 根据任务主键ID，操作人ID，参数列表完成任务，并且构造执行对象
     * @param taskId 任务id
     * @param operator 操作人
     * @param args 参数列表
     * @return Execution
     */
    private Execution execute(String taskId, String operator, Map<String, Object> args) {
        if(args == null) args = new HashMap<String, Object>();
        Task task = task().complete(taskId, operator, args);
        if(logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO,"任务[taskId=" + taskId + "]已完成");
        }

        Order order = query().getOrder(task.getOrderId());
        Assert.notNull(order, "指定的流程实例[id=" + task.getOrderId() + "]已完成或不存在");
        order.put(Order.LAST_UPDATOR, operator);
        order.put(Order.LAST_UPDATE_TIME, DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME));
        order().update(order);
        //协办任务完成不产生执行对象

        Flow flow = flow().getFlowById(order.getString(Order.FLOW_ID));
        Execution execution = new Execution(this, flow, order, args);
        execution.setOperator(operator);
        execution.setTask(task);
        return execution;
    }
}
