package com.damuzee.engine.core;

import com.damuzee.engine.IOrderService;
import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Order;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by karka.w on 2014/12/8.
 */
@Service
public class OrderServiceImpl implements IOrderService {

    public Order createOrder(Flow flow, String operator, Map<String, Object> args) {
        return null;
    }

    /**
     * 根据流程、操作人员、父流程实例ID创建流程实例
     * @param flow 流程定义对象
     * @param operator 操作人员ID
     * @param args 参数列表
     * @param parentId 父流程实例ID
     * @param parentNodeName 父流程节点模型
     * @return 活动流程实例对象
     */
    public Order createOrder(Flow flow, String operator, Map<String, Object> args, String parentId, String parentNodeName){
        return null;
    }
}
