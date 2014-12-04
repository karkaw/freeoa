package com.damuzee.work.task.action;

import com.damuzee.engine.auth.domain.ShiroUser;
import com.damuzee.engine.util.JSONUtil;
import com.damuzee.engine.web.bean.JsonResult;
import com.damuzee.engine.web.session.SessionProvider;
import com.damuzee.web.util.ObjectConvert;
import com.damuzee.work.task.domain.Task;
import com.damuzee.work.task.repos.TaskRepos;
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
@RequestMapping(value = "task")
public class TaskAct {

    @Autowired
    TaskRepos taskTepos ;

    @Autowired
    SessionProvider session ;

    @RequestMapping(value = "list.do")
    public String list(){

        return "/task/list" ;
    }

    @RequestMapping(value = "jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> jlist(String json){
        Map<String,Object> mapvo = JSONUtil.stringToMap(json);
        Map<String,Object> map = taskTepos.findTaskByPage(mapvo);
        return map ;
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.POST)
    @ResponseBody
    public Map get(String id) {
        if(id != null && !id.equals("")){
            Map map = new HashMap();
            map.put("_id",new ObjectId(id));
            Map temp = taskTepos.findTask(map);

            Map outMap = new HashMap();
            ObjectConvert.convertMapToString(outMap,temp , "","") ;
            return outMap ;
        }
        return null;
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(HttpServletRequest request) {
        Map<String, Object> params = ObjectConvert.convertParamToMap(request);

        Map userMap = (Map)session.getAttribute(request, ShiroUser.LOGIN_USER_KEY);

        params.put(Task.CREATE_USER_ID,userMap.get(ShiroUser.USER_NAME));
        String id  = (String)params.remove("_id") ;
        if (id.equals("")){
             id = taskTepos.saveTask(params);
        }else{
             params.put("_id",new ObjectId(id));
              taskTepos.updateTask(params);
        }

        return JsonResult.success(id);
    }

}
