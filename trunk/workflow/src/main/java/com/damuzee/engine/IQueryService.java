package com.damuzee.engine;

import com.damuzee.engine.domain.History;
import com.damuzee.engine.domain.Order;
import com.damuzee.engine.domain.Task;

import java.util.List;
import java.util.Map;

/**
 * 查询接口，任务、表单、历史
 * 
 * Created by karka.w on 2014/12/8.
 */
public interface IQueryService {

    /**
     * 根据流程定义查旬节点的用户
     *
     * @param roles
     * @return
     */
    public List  queryUserByOrgAndRole(List<Map> roles);

    /**
     * 获取定义的参与者
     *
     * @param node
     * @return
     */
    public List  getActorUser(Map node);

    /**
     * 获取流程单
     * @param orderId
     * @return
     */
    public Order getOrder(String orderId);

    /**
     * 获取任务
     */
    public Task getTask(String taskId);
    
    /**
     * 获取历史任务
     */
    public History getHistoryTask(String taskId);
    
    
}
