package com.damuzee.engine;

import com.damuzee.engine.domain.Order;

import java.util.Map;

/**
 * 抽象数据库操作接口
 * 
 * Created by karka.w on 2014/12/8.
 */
public interface AbstractRepos {

    public Map getFlowById(String id);

    public String saveOrder(Order order);
}
