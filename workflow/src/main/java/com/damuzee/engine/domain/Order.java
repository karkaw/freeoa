package com.damuzee.engine.domain;

/**
 * Created by Administrator on 2014/12/8.
 */
public class Order  {
    private static final String id ="id"                ;// VARCHAR(100) NOT NULL PRIMARY KEY comment '主键ID',
    private static final String parent_id  ="parent_id"        ;//   VARCHAR(100) comment '父流程ID',
    private static final String process_id  ="process_id"        ;//   VARCHAR(100) NOT NULL comment '流程定义ID',
    private static final String creator   ="creator"          ;//   VARCHAR(100) comment '发起人',
    private static final String create_time  ="create_time"       ;//   VARCHAR(50) NOT NULL comment '发起时间',
    private static final String expire_time   ="expire_time"      ;//   VARCHAR(50) comment '期望完成时间',
    private static final String last_update_time   ="last_update_time" ;//   VARCHAR(50) comment '上次更新时间',
    private static final String last_updator    ="last_updator"    ;//   VARCHAR(100) comment '上次更新人',
    private static final String priority        ="priority"    ;//   TINYINT(1) comment '优先级',
    private static final String parent_node_name  ="parent_node_name"  ;//   VARCHAR(100) comment '父流程依赖的节点名称',
    private static final String order_no        ="order_no"    ;//   VARCHAR(100) comment '流程实例编号',
    private static final String variable      ="variable"      ;//   VARCHAR(2000) comment '附属变量json存储',
    private static final String version          ="version"   ;//   INT(3) comment '版本'
 
}
