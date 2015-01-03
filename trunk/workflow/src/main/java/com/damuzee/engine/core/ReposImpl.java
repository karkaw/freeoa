package com.damuzee.engine.core;


import com.damuzee.engine.AbstractRepos;
import com.damuzee.engine.domain.Order;
import com.damuzee.engine.domain.Task;
import org.bson.types.ObjectId;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karka.w on 2014/12/8.
 */
@Service
public class ReposImpl implements AbstractRepos {

    public static final String  FLOW = "flows" ;

    public static final String  ORDER = "order" ;

    public static final String  TASK = "task" ;

    public static final String HISTORY  = "history" ;

    @Autowired
    MongoTemplate template ;

    /**
     * 根据流程编号查询流程单
     *
     * @param id
     * @return
     */
    public Map getFlowById(String id) {
        Map _idMap = new HashMap();
        _idMap.put("_id",new ObjectId(id));

        Map map = template.findOne(FLOW,_idMap);
        return  map;
    }

    /**
     * 根据主对象找到流程单
     *
     * @param code
     * @return
     */
    public Map getFlowByCode(String code) {
        Map _idMap = new HashMap();
        _idMap.put("ocode", code);

        Map map = template.findOne(FLOW,_idMap);
        return map;
    }

    /**
     * 保存流程单
     *
     * @param order
     * @return
     */
    public String saveOrder(Order order) {
        String id = template.save(ORDER,order);
        return id;
    }

    /**
     * 更新流程单
     *
     * @param order
     * @return
     */
    public void updateOrder(Order order) {
         template.save(ORDER,order);
    }

    /**
     * 保存任务
     *
     * @param task
     * @return
     */
    public String saveTask(Task task){
        String id = template.save(TASK,task);
        return id;
    }

    /**
     * 删除任务
     *
     * @param taskId
     * @return
     */
    public void deleteTask(String taskId){
        template.deleteById(TASK, taskId);
    }

    //保存历史
    public void saveHistory(Map history){
        template.save(HISTORY,history) ;
    }

}
