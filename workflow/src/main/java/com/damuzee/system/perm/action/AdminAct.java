package com.damuzee.system.perm.action;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.system.perm.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/7/24.
 */
@Controller
@RequestMapping(value = "admin")
public class AdminAct {

    @Autowired
    UserRepos userRepos ;

    @RequestMapping(value = "list.do")
    public String list(ModelMap map ){
        List list = userRepos.findUsers(new HashMap());
        map.put("userlist",list);
        return "/admin/list" ;
    }

    @RequestMapping(value = "add.do")
    public String add(){

        return "/admin/add" ;
    }

    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public String save(String json){
        Map mapvo = JSONUtil.stringToMap(json);
        userRepos.saveUser(mapvo);
        return "/admin/list" ;
    }
}
