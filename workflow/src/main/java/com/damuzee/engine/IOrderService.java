package com.damuzee.engine;

import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Order;

import java.util.Map;

/**
 * 工作流实例操作类
 *
 * Created by karka.w on 2014/12/5.
 */

public interface IOrderService {

    /**
     * 根据流程、操作人员、父流程实例ID创建流程实例
     * @param flow 流程定义对象
     * @param operator 操作人员ID
     * @param args 参数列表
     * @return Order 活动流程实例对象
     */
    public Order createOrder(Flow flow, String operator, Map<String, Object> args);

    /**
     * 根据流程、操作人员、父流程实例ID创建流程实例
     * @param flow 流程定义对象
     * @param operator 操作人员ID
     * @param args 参数列表
     * @param parentId 父流程实例ID
     * @param parentNodeName 父流程节点模型
     * @return 活动流程实例对象
     */
    public Order createOrder(Flow flow, String operator, Map<String, Object> args, String parentId, String parentNodeName);

    /**
     * 更新流程实例
     * @param order 流程实例对象
     */
    public String save(Order order);

    /**
     * 更新流程实例
     * @param order 流程实例对象
     */
    public void update(Order order);

}
