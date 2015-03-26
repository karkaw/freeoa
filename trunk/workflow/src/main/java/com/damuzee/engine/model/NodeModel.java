package com.damuzee.engine.model;

import com.damuzee.engine.Action;
import com.damuzee.engine.core.Execution;
import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 节点模型
 * 
 * Created by karka.w on 14-12-10.
 */
public abstract class NodeModel extends BaseModel implements Action{

    public String currentNodename = null;

    private Map<String,Object> params = new HashMap<String,Object>();

    Logger logger = Logger.getLogger("NodeModel");

    private List<Map> nextModel = new ArrayList<Map>();
    /**
     * 具体节点模型需要完成的执行逻辑
     * @param execution
     */
    protected abstract void exec(Execution execution);

    /**
     * 对执行逻辑增加前置、后置拦截处理
     * @param execution
     * @return
     */
    public void execute(Execution execution) {
        intercept(null, execution);
        exec(execution);
        intercept(null, execution);
    }

    public void run(Execution execution){
        if(nextModel == null || nextModel.size() ==0){
            FlowModel model = execution.getFlow().getModel();
            nextModel = model.getNextNodes(currentNodename);
        }
        ProcessModel processModel = new ProcessModel(nextModel);
        processModel.exec(execution);
    }

    public void intercept(List list,Execution execution){
        logger.log(Level.INFO,"------------拦截器执行------------");
    }

    /**
     * 获取节点类型
     * @param nodeMap
     * @return
     */
    public String getNodeType(Map nodeMap){
        if(nodeMap != null && nodeMap.get(Flow.TYPE) != null  ){
            return  (String)nodeMap.get(Flow.TYPE) ;
        }
        return null ;
    }

    public List<Map> getNextModel() {
        return nextModel;
    }

    public void setNextModel(List<Map> nextModel) {
        this.nextModel = nextModel;
    }

    public void setParams(String key ,Object value) {
        this.params.put(key,value);
    }

    public Object getParam(String key) {
        return params.get(key);
    }
}
