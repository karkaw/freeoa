package com.damuzee.work.task.action;

import com.damuzee.common.web.RequestUtils;
import com.damuzee.core.auth.domain.ShiroUser;
import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.core.web.session.SessionProvider;
import com.damuzee.web.util.ObjectConvert;
import com.damuzee.work.task.domain.Task;
import com.damuzee.work.task.repos.TaskRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(HttpServletRequest request) {
      /*  Map<String, Object> params = RequestUtils.getRequestMap(request);*/

        Map<String, Object> params = ObjectConvert.convertParamToMap(request);

        Map userMap = (Map)session.getAttribute(request, ShiroUser.LOGIN_USER_KEY);

        params.put(Task.CREATE_USER_ID,userMap.get(ShiroUser.USER_NAME));

        String id = taskTepos.saveTask(params);
        return JsonResult.success(id);
    }

}
