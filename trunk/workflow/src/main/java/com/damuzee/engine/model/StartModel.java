package com.damuzee.engine.model;

import com.damuzee.engine.core.Execution;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 开始节点处理模型
 * 
 * Created by karka.w on 14-12-18.
 */
public class StartModel extends NodeModel{
    Logger logger = Logger.getLogger("StartModel");

    protected void exec(Execution execution) {
        logger.log(Level.INFO,"------流程开始执行-------");
        run(execution);
    }
}
