package com.damuzee.engine.core;

import com.damuzee.common.util.DateUtil;
import com.damuzee.engine.AbstractRepos;
import com.damuzee.engine.IOrderService;
import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Order;
import com.damuzee.engine.model.FlowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**流程表单的服务
 * 
 * Created by karka.w on 2014/12/8.
 */
@Service
public class OrderServiceImpl  extends ReposImpl  implements IOrderService {

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
        Order order = new Order();

        order.put(Order.PARENT_ID,parentId);
        order.put(Order.PARENT_NODE_NAME, parentNodeName);
        order.put(Order.CREATE_TIME, DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME));
        order.put(Order.LAST_UPDATE_TIME, DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME));
        order.put(Order.CREATOR, operator);
        order.put(Order.LAST_UPDATOR, operator);
        //审批单编号
        order.put(Order.FORM_ID, args.get(Order.FORM_ID));
        //流程定义编号
        order.put(Order.FLOW_ID, flow.get(Flow.FLOW_ID));
        FlowModel model = flow.getModel();
        if(model != null && args != null) {
       
        }

        String id =  save(order);
        order.put(Order.ORDER_ID,id) ;
        return order;
    }

    public String save(Order order) {
        return super.saveOrder(order);
    }

    public void update(Order order) {
        updateOrder(order);
    }
}
