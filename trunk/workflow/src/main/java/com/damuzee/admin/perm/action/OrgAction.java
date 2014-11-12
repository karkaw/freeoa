package com.damuzee.admin.perm.action;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.admin.perm.repos.OrgRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by K.K on 2014/9/26.
 */
@Controller
@RequestMapping(value = "/org")
public class OrgAction {

    @Autowired
    OrgRepos orgRepos ;

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String vlist(ModelMap map) {

        return "/org/tree";
    }

    @RequestMapping(value = "/list.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult list(String json) {
        Map map = JSONUtil.stringToMap(json);

        Map list = orgRepos.findOrgByPage(map);
        JsonResult result = JsonResult.page(list);
        return result;
    }

    @RequestMapping(value = "/tree.do", method = RequestMethod.POST)
    @ResponseBody
    public List tree(@RequestParam Map<String, Object> mapvo) {
        Map queryMap = new HashMap();
        if (mapvo.get("id") == null || mapvo.get("id").equals("")) {
            queryMap.put("parentId", null);
        } else {
            queryMap.put("parentId", mapvo.get("id"));
        }
        List list = orgRepos.findOrg(queryMap);
        return list;
    }

    @RequestMapping(value = "/add.do")
    public String add(Map resMap) {
        return "/resource/add";
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(String json, HttpServletRequest request) {
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);

        if (mapvo.get("_id") == null){
            orgRepos.saveOrg(mapvo);
        }else{
            orgRepos.updateOrg(mapvo);
        }

        JsonResult result = JsonResult.success(null);

        return null;
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(String json) {
        Map<String, List> listvo = (Map) JSONUtil.stringToMap(json);
        orgRepos.deleteOrg(listvo.get("ids"));

        return JsonResult.success("ok");
    }
}
