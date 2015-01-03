package com.damuzee.engine.model;

import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Task;
import com.damuzee.workflow.definition.repos.ObjectRepos;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/12/8.
 */
public class FlowModel extends BaseModel{

    /**
     * 流程实例启动url
     */
    private String formId;
    /**
     * 期望完成时间
     */
    private String expireTime;

    private Map<String,Object> model = new HashMap<String, Object>() ;

    public static final String START = "start" ;

    public static final String TYPE = "type" ;

    public StartModel getStart(){
        StartModel startModel = new StartModel();
          Map<String,Object> nodeMap  = (Map)model.get(Flow.STATES);
          for (String key  : nodeMap.keySet()){
                Map<String,Object> node = (Map<String,Object>)nodeMap.get(key);
                if (node != null && node.get(TYPE)!= null &&  START.equals(node.get(TYPE))){
                    startModel.setNextModel(getNextNodes(key));
                    break;
                }
            }

        return startModel ;
    }

    public List<Map> getNextNodes(String nodeCode){
        Map<String,Object> pathMap  = (Map)model.get(Flow.PATHS);
        List list = new ArrayList() ;
        for (String key  : pathMap.keySet()){
            Map<String,Object> path = (Map<String,Object>)pathMap.get(key);
            if (path != null && path.get(Flow.FROM)!= null &&  path.get(Flow.FROM).equals(nodeCode)){

                path.put(Task.TASK_NAME,key) ;
               list.add(path);
            }
        }
        return list ;

    }

    /**
     * 获取定义中的节点
     *
     * @return
     */
    public Map getNode(){
        Map model = (Map)getModel().get(Flow.STATES);
        return model;
    }

    /**
     * 获取定义中的节点
     *
     * @return
     */
    public NodeModel getNodeByName(String taskname){
        List<Map> nextNodes = this.getNextNodes(taskname);
        NodeModel nodeModel = new TaskModel();
        nodeModel.setNextModel(nextNodes);
        return nodeModel;
    }

    /**
     * 解析模板定义成model
     *
     * @param map
     * @return
     */
    public FlowModel parse(Map map){

        return null;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
