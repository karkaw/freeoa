package com.damuzee.engine.domain;

import com.damuzee.engine.model.FlowModel;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by karka.w on 2014/12/8.
 */
public class Flow extends AbstractMap<String,Object>
        implements Map<String,Object>, Serializable {

    /** 流程定义名称 **/
    public static  final String NAME = "name"  ;

    /** 流程定义标题 **/
    public static  final String TITLE = "title"  ;

    /**
     * 流程定义描述
     */
    public static  final String DESC = "desc";
    /**
     * 流程定义类型（预留字段）
     */
    public static  final String TYPE = "type";
    /**
     * 当前流程的实例url（一般为流程第一步的url）
     * 该字段可以直接打开流程申请的表单
     */
    public static  final String form_ID = "form_id";
    /**
     * 是否可用的开关
     */
    public static  final String STATE  = "state";
    /**
     * 创建时间
     */
    public static  final String CREATE_TIME ="create-time";
    /**
     * 创建人
     */
    public static  final String creator = "creator";

    FlowModel model;

    public static Flow wrap(Map map) {
        Flow flow = new Flow();



        return flow;
    }

    public FlowModel getModel() {
        return model;
    }

    public Set<Entry<String, Object>> entrySet() {
        return this.entrySet();
    }
}