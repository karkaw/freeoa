package com.damuzee.engine;

import java.util.Map;

/**
 * Created by karka.w on 2014/12/4.
 */
public interface WFEngine {

    /**
     * 获取process服务
     * @return IProcessService 流程定义服务
     */
    public IProcessService process();

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
    public Map startInstanceByName(String name, String operner);

}
