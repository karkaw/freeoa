package com.damuzee.engine.domain;

import com.damuzee.engine.BaseMap;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by karka.w on 14-12-25.
 */
public class History  extends BaseMap implements Map<String, Object>, Serializable {

    /** 处理时间 */
    public static  final  String FINISH_TIME = "finish_time" ;

    /*** 任务状态*/
    public static  final  String TASK_STATE = "task_state" ;

    /** 处理人*/
    public static  final  String OPERATOR = "operator" ;

    /*** 任务参与者列表*/
    public static final  String  ACTOR_IDS = "actor_ids";

    public  History(Task task){

    }

}
