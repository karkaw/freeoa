package com.damuzee.engine.domain;

import com.damuzee.engine.BaseMap;

import java.io.Serializable;
import java.util.Map;

/**表单
 * 
 * Created by karka.w on 2014/12/8.
 */
public class Order  extends BaseMap implements Map<String, Object>, Serializable {
    public static final String ORDER_ID  ="order_id"        ;//   VARCHAR(100) comment '父流程ID',
    public static final String PARENT_ID  ="parent_id"        ;//   VARCHAR(100) comment '父流程ID',
    public static final String FLOW_ID ="flow_id"        ;//   VARCHAR(100) NOT NULL comment '流程定义ID',
    public static final String FORM_ID ="form_id"        ;//   VARCHAR(100) NOT NULL comment '流程定义ID',
    public static final String CREATOR   ="creator"          ;//   VARCHAR(100) comment '发起人',
    public static final String CREATE_TIME  ="create_time"       ;//   VARCHAR(50) NOT NULL comment '发起时间',
    public static final String EXPIRE_TIME   ="expire_time"      ;//   VARCHAR(50) comment '期望完成时间',
    public static final String LAST_UPDATE_TIME   ="last_update_time" ;//   VARCHAR(50) comment '上次更新时间',
    public static final String LAST_UPDATOR    ="last_updator"    ;//   VARCHAR(100) comment '上次更新人',
    public static final String PRIORITY        ="priority"    ;//   TINYINT(1) comment '优先级',
    public static final String PARENT_NODE_NAME  ="parent_node_name"  ;//   VARCHAR(100) comment '父流程依赖的节点名称',
    public static final String ORDER_NO        ="order_no"    ;//   VARCHAR(100) comment '流程实例编号',
    public static final String VARIABLE      ="variable"      ;//   VARCHAR(2000) comment '附属变量json存储',
    public static final String VERSION          ="version"   ;//   INT(3) comment '版本'
 
}
