package com.damuzee.engine.core;

import com.damuzee.engine.*;
import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2014/12/8.
 */
@Service
public class WFEngineImpl implements WFEngine {

    @Autowired
    IProcessService processService ;

    @Autowired
    IQueryService queryService ;

    @Autowired
    IOrderService orderService ;

    @Autowired
    ITaskService taskService ;

    public IProcessService process() {

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
        Map<String,Object> flow = process().getProcessById(id);

        for (String key : flow.keySet()){
            System.out.println(key);
        }

        return null;
    }

    public Map startInstanceByName(String name, String operner) {
        //获取定义
        Flow flow = process().getProcessByCode(name);

        //获取执行器
        Execution execution = execute(flow, operner, null, null, null);

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
}
