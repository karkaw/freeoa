package com.damuzee.engine.domain;

import com.damuzee.common.util.DateUtil;
import com.damuzee.core.auth.domain.ShiroUser;
import com.damuzee.engine.BaseMap;
import com.damuzee.engine.model.FlowModel;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by karka.w on 2014/12/8.
 */
public class Flow extends BaseMap implements Map<String, Object>, Serializable {

    /**
     * 流程定义名称 *
     */
    public static final String FLOW_ID = "flow_id";

    /**
     * 流程定义名称 *
     */
    public static final String NAME = "name";

    /**
     * 流程定义标题 *
     */
    public static final String TITLE = "title";

    /**
     * 流程定义描述
     */
    public static final String DESC = "desc";
    /**
     * 流程定义类型（预留字段）
     */
    public static final String TYPE = "type";
    /**
     * 当前流程的实例url（一般为流程第一步的url）
     * 该字段可以直接打开流程申请的表单
     */
    public static final String FORM_ID = "form_id";
    /**
     * 是否可用的开关
     */
    public static final String STATE = "state";
    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "create_time";
    /**
     * 创建人
     */
    public static final String CREATOR = "creator";

    /**
     * 节点Key
     */

    public static  final  String STATES = "states" ;

    /**
     * 节点Key
     */

    public static  final  String TYPE_START = "start" ;

    /**
     * 路径
     */
    public static  final String PATHS = "paths" ;

    /**
     * 路径
     */
    public static  final String FROM = "from" ;

    /**
     * 路径
     */
    public static  final String TO = "to" ;

    /**
     * 节点类型  任务
     */
    public static  final String TASK = "task" ;

    public static  final String FORK = "fork" ;

    /**
     * 节点类型  结束
     */
    public static  final String END = "end" ;

    public static final String PROPS = "props";

    public static final String VALUE = "value";

    public static final String ROLES = "roles";

    public static final String ORG_TEXT = "org_text";

    public static final String ROLE_CODE = "role_code";

    public static final String ROLE_TYPE = "type";

    public static final String CHECKED = "checked" ;

    FlowModel model;

    public Flow warp(Map params) {
        this.put(FLOW_ID,params.get("_id"));
        this.put(NAME,params.get(NAME));
        this.put(TITLE,params.get(TITLE));
        this.put(DESC,params.get(DESC));
        this.put(FORM_ID,params.get(FORM_ID));
        this.put(CREATE_TIME,DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME));
        this.put(CREATOR,SecurityUtils.getSubject().getSession().getAttribute(ShiroUser.USER_NAME));

        //初始化模型
        model = new FlowModel();
        model.setModel(params);
        return this;
    }

    public FlowModel getModel() {
        return model;
    }

    public Set<Entry<String, Object>> entrySet() {
        return this.entrySet();
    }
}