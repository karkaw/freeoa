package com.damuzee.system.perm.action;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.system.perm.repos.RoleRepos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/jlist.do", method = RequestMethod.GET)
    public String jList(ModelMap map) {
        List list = roleRepos.findRoles(new HashMap());
        map.put("rolelist",list);
        return "/role/list";
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(String json, HttpServletRequest request) {
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);

        if (mapvo.get("_id") == null){
            roleRepos.saveRole(mapvo);
        }else{
            roleRepos.updateRole(mapvo);
        }

        JsonResult result = JsonResult.success(null);

        return result;
    }

}
