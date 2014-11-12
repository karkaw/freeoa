package com.damuzee.admin.perm.action;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.admin.perm.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karka.w on 2014/7/24.
 */
@Controller
@RequestMapping(value = "employee")
public class EmployeeAct {

    @Autowired
    UserRepos userRepos ;

    @RequestMapping(value = "list.do")
    public String list(){

        return "/employee/list" ;
    }

    @RequestMapping(value = "jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> jlist(){
        Map<String,Object> map = userRepos.findUserByPage(new HashMap());
       return map ;
    }

    @RequestMapping(value = "add.do")
    public String add(){

        return "/employee/add" ;
    }

    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public String save(String json){
        Map mapvo = JSONUtil.stringToMap(json);
        userRepos.saveUser(mapvo);
        return "/employee/list" ;
    }
}
