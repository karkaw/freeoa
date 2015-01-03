package com.damuzee.engine;

import com.damuzee.engine.domain.Order;
import com.damuzee.engine.domain.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by karkaw on 2014/12/8.
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
     * 获取流程单
     * @param orderId
     * @return
     */
    public Order getOrder(String orderId);

    /**
     * 获取任务
     */

    public Task getTask(String taskId);
}
