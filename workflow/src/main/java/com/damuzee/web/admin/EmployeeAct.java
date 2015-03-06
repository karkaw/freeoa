package com.damuzee.web.admin;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.service.perm.repos.UserRepos;
import com.damuzee.core.web.bean.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "get.do")
    @ResponseBody
    public JsonResult get(String json){
        Map mapvo = JSONUtil.stringToMap(json);
        mapvo = userRepos.findUser(mapvo);

        return JsonResult.success(mapvo);
    }

    @RequestMapping(value = "edit.do")
    public String edit(String id){

        return "/employee/edit" ;
    }

    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(String json){
        Map mapvo = JSONUtil.stringToMap(json);
        if(mapvo.containsKey("_id")){
            userRepos.updateUser(mapvo);
        }else {
            userRepos.saveUser(mapvo);
        }
        return JsonResult.success("ok");
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(String json) {
        Map<String, List> listvo = (Map) JSONUtil.stringToMap(json);
        userRepos.deleteUser(listvo.get("ids"));

        return JsonResult.success("ok");
    }
}
