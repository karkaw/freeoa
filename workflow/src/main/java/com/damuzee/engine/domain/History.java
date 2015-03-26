package com.damuzee.engine.domain;

import com.damuzee.common.util.DateUtil;
import com.damuzee.engine.BaseMap;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

/**历史任务
 * 
 * Created by karka.w on 14-12-25.
 */
public class History  extends BaseMap implements Map<String, Object>, Serializable {

	/** 任务名称 */
	public static  final  String TASK_NAME = "task_name" ;
	
	/** 任务编号 */
	public static  final  String TASK_ID = "task_id" ;
	
    /** 处理时间 */
    public static  final  String FINISH_TIME = "finish_time" ;

    /*** 任务状态*/
    public static  final  String TASK_STATE = "task_state" ;

    /** 处理人*/
    public static  final  String OPERATOR = "operator" ;

    /*** 任务参与者列表*/
    public static final  String  ACTOR_IDS = "actor_ids";
    
    public List<String> getActorIds(){
    	return (List<String>)this.get(ACTOR_IDS);
    }

    public  History(Task task){
    	this.put(TASK_ID,task.get(Task.ID));
    	this.putAll(task);
    	this.remove(Task.ID);
    }
    
    public  History(String taskId){
    	this.put(TASK_ID, taskId);
    }

	public Task undoTask() {
		Task task = new Task();
		//任务编码名称
		task.put(Task.TASK_NAME,this.get(TASK_NAME));
        //任务关联的工作表单
		task.put(Task.FORM_ID,this.get(Task.FORM_ID));

        //任务关联的工作流
		task.put(Task.ORDER_ID,this.get(Task.ORDER_ID));
        //任务名称
		task.put(Task.NAME,this.get(Task.NAME));
        //任务标题
		task.put(Task.TITLE,this.get(Task.TITLE));
        
        //上一个任务节点名称
		task.put(Task.PARENT_TASK_ID,this.get(Task.PARENT_TASK_ID));
        //根据角色和部门查询出员工
		task.put(Task.ACTOR_IDS,this.get(Task.ACTOR_IDS));
        //任务创建人
		task.put(Task.OPERATOR,this.get(Task.OPERATOR));
        //任务创建时间
		task.put(Task.CREATE_TIME, Task.CREATE_TIME);
		return task;
	}

}
