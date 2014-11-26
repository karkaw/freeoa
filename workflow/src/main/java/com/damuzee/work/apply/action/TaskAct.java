package com.damuzee.work.apply.action;

import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.work.apply.repos.TaskRepos;
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
    TaskRepos taskAct ;
    @RequestMapping(value = "list.do")
    public String list(){

        return "/task/list" ;
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(HttpServletRequest request) {
        Map<String, Object> listvo = request.getParameterMap();

        String id = taskAct.saveTask(listvo);
        return JsonResult.success(id);
    }

}
