package com.damuzee.web.admin;

import com.damuzee.core.auth.domain.ShiroUser;
import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.session.SessionProvider;
import com.damuzee.engine.WFEngine;
import com.damuzee.engine.domain.Task;
import com.damuzee.core.util.ObjectConvert;
import com.damuzee.service.apply.domain.Apply;
import com.damuzee.service.apply.repos.ApplyRepos;
import com.damuzee.service.task.repos.TaskRepos;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by K.K on 2014/11/12.
 */

@Controller
@RequestMapping(value = "work/task")
public class TaskAct {

    @Autowired
    TaskRepos taskRepos ;

    @Autowired
    ApplyRepos applyRepos ;

    @Autowired
    WFEngine engine ;

    @Autowired
    SessionProvider session ;

    @RequestMapping(value = "list.do")
    public String list(){

        return "/work/task/list" ;
    }

    @RequestMapping(value = "jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> jlist(String json,HttpServletRequest request){
        Map<String,Object> mapvo = JSONUtil.stringToMap(json);

        Map userMap = (Map)session.getAttribute(request, ShiroUser.LOGIN_USER_KEY);
        mapvo.put(Task.ACTOR_IDS,userMap.get(ShiroUser.USER_NAME));

        Map<String,Object> map = taskRepos.findTaskByPage(mapvo);
        return map ;
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.POST)
    @ResponseBody
    public Map get(String id,HttpServletRequest request) {
        if(id != null && !id.equals("")){
            Map map = new HashMap();
            map.put("_id",new ObjectId(id));
            //查找任务
            Map task = taskRepos.findTask(map);


            //根据任务获取form_id
            map.put("_id",new ObjectId((String)task.get("form_id")));
            Map temp = applyRepos.findApply(map);

            Map outMap = new HashMap();
            ObjectConvert.convertMapToString(outMap,temp , "","") ;

            return outMap ;
        }
        return null;
    }

    @RequestMapping(value = "/agree.do", method = RequestMethod.POST)
    @ResponseBody
    public Map agree(String taskId,String context,HttpServletRequest request) {
        Map params = new HashMap();
        Map userMap = (Map)session.getAttribute(request, ShiroUser.LOGIN_USER_KEY);

        params.put(Apply.CREATOR,userMap.get(ShiroUser.USER_NAME));

        //启用工作流参数
        Map args = new HashMap() ;
        args.put("context",context) ;
        //开始工作流
        engine.executeTask(taskId,(String)userMap.get(ShiroUser.USER_NAME) ,args);

        return null;
    }
}
