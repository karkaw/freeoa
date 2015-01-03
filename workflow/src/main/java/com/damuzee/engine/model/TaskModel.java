package com.damuzee.engine.model;

import com.damuzee.engine.Action;
import com.damuzee.engine.core.Execution;

import java.util.logging.Level;

/**
 * Created by karka.w on 14-12-10.
 */
public class TaskModel extends NodeModel implements Action{

    protected void exec(Execution execution) {
        logger.log(Level.INFO, "------任务节点 处理模型开始执行-------");
        run(execution);
    }
}
