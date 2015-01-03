package com.damuzee.admin.perm.action;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.admin.perm.repos.RoleRepos;
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

    @RequestMapping(value = "/jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult jList(String json) {
        Map<String, List> map = (Map) JSONUtil.stringToMap(json);
        Map list = roleRepos.findRoleByPage(map);
        JsonResult result = JsonResult.page(list);
        return result;
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.POST)
    @ResponseBody
    public List get(String json) {
        Map<String, List> map = (Map) JSONUtil.stringToMap(json);
        List list = roleRepos.findRoles(map);
        return list;
    }

    @RequestMapping(value = "/getById.do", method = RequestMethod.POST)
    @ResponseBody
    public Map getById(String json) {
        Map<String, List> map = (Map) JSONUtil.stringToMap(json);
        Map resultMap = roleRepos.findRole(map);
        return resultMap;
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

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(String json) {
        Map<String, List> listvo = (Map) JSONUtil.stringToMap(json);
        roleRepos.deleteRoleById(listvo.get("ids"));

        return JsonResult.success("ok");
    }
}
