package com.damuzee.engine;

import com.damuzee.engine.domain.Order;
import com.damuzee.engine.domain.Process;
import java.util.Map;

/**
 * 工作流实例操作类
 *
 * Created by karka.w on 2014/12/5.
 */

public interface IOrderService {

    /**
     * 根据流程、操作人员、父流程实例ID创建流程实例
     * @param process 流程定义对象
     * @param operator 操作人员ID
     * @param args 参数列表
     * @return Order 活动流程实例对象
     */
    Order createOrder(Process process, String operator, Map<String, Object> args);
}
