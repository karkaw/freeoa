package com.damuzee.engine.core;

import com.damuzee.common.Constants;
import com.damuzee.core.auth.repos.impl.AuthUserReposImpl;
import com.damuzee.engine.IQueryService;
import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.History;
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
 * 查询服务
 * 
 * 表单
 * 任务
 * 历史
 * 
 * Created by karka.w on 2014/12/8.
 */
@Service
public class QueryServiceImpl extends ReposImpl  implements IQueryService {

    Logger logger = Logger.getLogger("ProcessModel");

    public static final String USER_INFO = "userinfo" ;

    public static final String ORDER = "order" ;

    public static final String TASK = "task" ;
    
    public static final String HISTORY = "history" ;

    /**
     * 通过角色获取用户
     * 
     * 根据角色
     * 
     */
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
    * 从流程定义中获取参与任务用户
    */
    public List getActorUser(Map node) {

        List<Map> actorList = (List)Task.getPropValue(node,Flow.ROLES);
        List userList = new ArrayList();

        for (Map o : actorList){

            String roleType  = (String)o.get(Flow.ROLE_TYPE);
            if(roleType.equals("5")){
                userList.add(o.get(Flow.ORG_TEXT));
            }else{
                List roles = new ArrayList();
                Map orgs = new HashMap();
                String orgcode  = (String)o.get(Flow.ORG_TEXT);
                List<Map> rolecodes = (List) o.get(Flow.ROLE_CODE);

                String [] key = orgcode.split(",") ;
                List value = new ArrayList() ;
                for(Map role : rolecodes){
                    if((Boolean)role.get(Flow.CHECKED)){
                        value.add(role.get(Flow.VALUE));
                    }
                }
                orgs.put(key[0],value);
                roles.add(orgs);
                userList.addAll(queryUserByOrgAndRole(roles));
            }
        }
        return userList;
    }

    /**
     * 根据流程ID获取流程
     */
    public Order getOrder(String orderId){
        Map<String,Object> queryMap = new HashMap();
        queryMap.put(Order.ID,new ObjectId(orderId));

        Order order = new Order();
        order.putAll(template.findOne(ORDER, queryMap));
        order.put(Order.ORDER_ID,orderId);
        order.put(Order.ID,new ObjectId(orderId));
        return order ;
    }

    /**
     * 根据任务ID获取任务
     * @param taskId
     * @return
     */
    public Task getTask(String taskId){
        Map<String,Object> queryMap = new HashMap();
        queryMap.put(Task.ID,new ObjectId(taskId));

        Task task =  new Task();
        task.putAll(template.findOne(TASK,queryMap));
        return task ;
    }

	/**
	 * 根据任务编号获取历史任务
	 */
	public History getHistoryTask(String taskId) {
		 History history =  new History(taskId);
		 history.putAll(template.findOne(HISTORY,history));
		return history;
	}
}
