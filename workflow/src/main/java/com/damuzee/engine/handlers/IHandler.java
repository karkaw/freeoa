package com.damuzee.engine.handlers;

/**
 * Created by karka.w on 14-12-19.
 */

import com.damuzee.engine.core.Execution;

/**
 * 流程各模型操作处理接口
 * @author yuqs
 * @since 1.0
 */
public interface IHandler {
    /**
     * 子类需要实现的方法，来处理具体的操作
     * @param execution 执行对象
     */
    public void handle(Execution execution);
}