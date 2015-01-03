package com.damuzee.engine.model;

import com.damuzee.engine.core.Execution;
import com.damuzee.engine.handlers.IHandler;

import java.io.Serializable;

/**
 * Created by karka.w on 14-12-18.
 */
public class BaseModel implements Serializable {
    /**
     * 将执行对象execution交给具体的处理器处理
     * @param handler
     * @param execution
     */
    protected void fire(IHandler handler, Execution execution) {
        handler.handle(execution);
    }
}
