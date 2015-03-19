package com.damuzee.engine.model;

import com.damuzee.engine.Action;
import com.damuzee.engine.core.Execution;
import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Task;
import com.damuzee.engine.handlers.imp.CreateTaskHandler;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karka.w on 14-12-19.
 */
public class ProcessModel extends NodeModel implements Action {

    Logger logger = Logger.getLogger("ProcessModel");

    ProcessModel(List<Map> nextModel){
        setNextModel(nextModel);
    }

    protected void exec(Execution execution) {
        FlowModel flowModel = execution.getFlow().getModel();
        Map nodeMap  = flowModel.getNode() ;
        logger.log(Level.INFO, "------节点处理模型开始执行-------");

        List<Map> nextModel = getNextModel();
        for(Map<String,Object> model : nextModel){
            if(model != null){
                String nextNodeName = (String)model.get(Flow.TO);
                Map<String,Object> nextNode = (Map<String,Object>)nodeMap.get(nextNodeName);
                nextNode.put(Task.TASK_NAME,model.get(Flow.TO));
                if(nextNode != null){
                    String type = (String)nextNode.get(Flow.TYPE);
                    if(type.equals(Flow.TASK)){//下一步是任务节点，创建一个新任务
                       fire(new CreateTaskHandler(nextNode),execution);
                    }else if (type.equals(Flow.FORK)){ //下一步是分支节点，继续执行
                        NodeModel nodeModel = new ForkModel();
                        nodeModel.currentNodename = (String)model.get(Flow.TO);
                        nodeModel.setParams(Task.TASK_NAME,model.get(Flow.TO));
                        nodeModel.execute(execution);
                    }
                }
            }
        }
    }
}
