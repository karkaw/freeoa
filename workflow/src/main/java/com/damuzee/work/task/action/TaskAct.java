package com.damuzee.work.task.action;

import com.damuzee.core.auth.domain.ShiroUser;
import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.core.web.session.SessionProvider;
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
    TaskRepos taskRepos ;

    @Autowired
    SessionProvider session ;

    @RequestMapping(value = "list.do")
    public String list(){

        return "/task/list" ;
    }
}
