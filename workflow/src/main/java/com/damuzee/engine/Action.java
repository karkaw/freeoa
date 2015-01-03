package com.damuzee.engine;

import com.damuzee.engine.core.Execution;

/**
 * 模型对象的接口
 *
 * Created by karka.w on 14-12-18.
 */
public interface Action {
    /**
     * 根据当前的执行对象所维持的process、order、model、args对所属流程实例进行执行
     * @param execution 执行对象
     */
    public void execute(Execution execution);
}
