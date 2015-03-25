package com.damuzee.engine.domain;

import com.damuzee.common.Constants;
import com.damuzee.common.util.DateUtil;
import com.damuzee.engine.BaseMap;
import com.damuzee.engine.IQueryService;
import com.damuzee.engine.core.Execution;
import com.damuzee.engine.core.QueryServiceImpl;
import com.damuzee.engine.model.FlowModel;
import com.damuzee.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.lang.model.element.Name;
import java.io.Serializable;
import java.util.*;

/**
 * Created by karka.w on 2014/12/8.
 */
public class Task  extends BaseMap implements Map<String, Object>, Serializable {

   

    /**
     * 流程实例ID
     */
    public static final  String ORDER_ID = "order_id";
    /**
     * 任务名称
     */
    public static final  String NAME = "name";
    /**
     * 任务显示名称
     */
    public static final  String TITLE = "title";
    /**
     * 参与方式（0：普通任务；1：参与者会签任务）
     */
    public static final  String PERFORM_TYPE = "perform_type" ;
    /**
     * 任务类型（0：主办任务；1：协办任务）
     */
    public static final  String TASK_TYPE = "task_type";
    /**
     * 任务处理者ID
     */
    public static final  String OPERATOR = "operator";
    /**
     * 任务创建时间
     */
    public static final  String CREATE_TIME = "create_time";
    /**
     * 任务完成时间
     */
    public static final  String FINISH_TIME = "finish_time";
    /**
     * 期望任务完成时间
     */
    public static final  String EXPIRE_TIME = "expire_time";
    /**
     * 期望的完成时间date类型
     */
    public static final  String EXPIRE_DATE ="expire_date";
    /**
     * 提醒时间date类型
     */
    public static final  String REMIND_DATE = "remind_date";
    /**
     * 任务关联的表单url
     */
    public static final  String FORM_ID = "form_id";
    /**
     * 任务参与者列表
     */
    public static final  String  ACTOR_IDS = "actor_ids";
    /**
     * 父任务Id
     */
    public static final  String PARENT_TASK_ID = "parent_task_id";

    /**
     * 节点名称name
     */
    public static final String TASK_NAME = "task_name" ;
    

    /**
     * 任务附属变量
     */
    private String variable;
    /**
     * 保持模型对象
     */
    private TaskModel model;


    public Task warp(Map params,Execution execution) {
        Order order = execution.getOrder();

        //任务编码名称
        this.put(TASK_NAME,params.get(TASK_NAME));
        //任务关联的工作表单
        this.put(FORM_ID,order.get(Order.FORM_ID));

        //任务关联的工作流
        this.put(ORDER_ID,order.get(Order.ORDER_ID));
        //任务名称
        this.put(NAME,getPropValue(params,NAME));
        //任务标题
        this.put(TITLE,getPropValue(params,NAME));
        
        Task currentTask = execution.getTask();
        if(currentTask != null){
	        //上一个任务节点名称
	        this.put(PARENT_TASK_ID,currentTask.get(Task.ID));
	    }
        //根据角色和部门查询出员工
        this.put(ACTOR_IDS,execution.getOperator());
        //任务创建人
        this.put(OPERATOR,execution.getOperator());
        //任务创建时间
        this.put(CREATE_TIME, DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME));

       return this;
    }

    /**
     * 获取节点参与者
     *
     * @param node
     * @return
     */
    public List getActorUser(Map node){
        List<Map> actorList = (List)getPropValue(node,Flow.ROLES);
        return actorList ;
    }

    //解析流程定义的props值
    public static Object getPropValue(Map node,String key){

        Map props = (Map)node.get(Flow.PROPS);
        Map value = (Map)props.get(key);
        if(value != null && value.size() > 0){
            return value.get(Flow.VALUE);
        }
        return null ;
    }

    public String getOrderId(){
        return  this.getString(ORDER_ID);
    }


}
