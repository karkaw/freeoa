package com.damuzee.system.perm.action;

import com.damuzee.system.perm.repos.RoleRepos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by K.K on 2014/10/27.
 */
@Controller
@RequestMapping(value = "role")
public class RoleAct {

    @Resource
    RoleRepos roleRepos ;

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String vlist(ModelMap map) {
        List list = roleRepos.findRoles(new HashMap());
        map.put("rolelist",list);
        return "/role/list";
    }

    @RequestMapping(value = "/j_list.do", method = RequestMethod.GET)
    public String jList(ModelMap map) {
        List list = roleRepos.findRoles(new HashMap());
        map.put("rolelist",list);
        return "/role/list";
    }

}
