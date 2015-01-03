package com.damuzee.engine.core;

import com.damuzee.common.Constants;
import com.damuzee.core.auth.repos.impl.AuthUserReposImpl;
import com.damuzee.engine.IQueryService;
import com.damuzee.engine.domain.Order;
import com.damuzee.engine.domain.Task;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karka.w on 2014/12/8.
 */
@Service
public class QueryServiceImpl extends ReposImpl  implements IQueryService {

    Logger logger = Logger.getLogger("ProcessModel");

    public static final String USER_INFO = "userinfo" ;

    public static final String ORDER = "order" ;

    public static final String TASK = "task" ;

    //通过组织结构和角色查询用户
    public List queryUserByOrgAndRole(List<Map> roles) {

        logger.log(Level.INFO,"------------获取用户---------参数： " + roles.toString());

        Map or = new HashMap() ;

        List orList = new ArrayList();
        for(Map<String,List> role : roles){
            Map ands= new HashMap() ;
            List andList  = new ArrayList();
            for(String orgcode : role.keySet()){
                Map and = new HashMap();
                and.put("org_code",orgcode) ;
                and.put("roles",role.get(orgcode)) ;
                andList.add(and);
                ands.put("$and",andList) ;
            }

            orList.add(ands);
        }

        or.put("$or",orList) ;

        logger.log(Level.INFO,"------------查询用户的---------参数： " + orList.toString());

        List<Map> lst = template.find(USER_INFO,or) ;

        List userList = new ArrayList();

        for (Map user : lst){
            userList.add(user.get("username"));
        }

        logger.log(Level.INFO,"------------查询到的角色---------参数： " + userList.toString());
        return userList;
    }

    /**
     * 根据流程ID获取流程
     */
    public Order getOrder(String orderId){
        Map queryMap = new HashMap();
        queryMap.put("_id",new ObjectId(orderId));

        Order order = new Order();
        order.putAll(template.findOne(ORDER, queryMap));
        order.put(Order.ORDER_ID,orderId);
        return order ;
    }

    /**
     * 根据任务ID获取任务
     * @param taskId
     * @return
     */
    public Task getTask(String taskId){
        Map queryMap = new HashMap();
        queryMap.put("_id",new ObjectId(taskId));

        Task task =  new Task();
        task.putAll(template.findOne(TASK,queryMap));
        return task ;
    }
}
