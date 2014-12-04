package com.damuzee.admin.perm.action;

import com.damuzee.admin.perm.domain.Org;
import com.damuzee.admin.perm.repos.OrgRepos;
import com.damuzee.common.util.MongodbUtil;
import com.damuzee.engine.util.JSONUtil;
import com.damuzee.engine.web.bean.JsonResult;
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

    public static  final String ID = "id" ;

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

        if(map.containsKey(Org.CODE)){
            map.put(Org.CODE, MongodbUtil.likeLeft((String) map.get(Org.CODE)));
        }
        Map list = orgRepos.findOrgByPage(map);
        JsonResult result = JsonResult.page(list);
        return result;
    }

    @RequestMapping(value = "/tree.do", method = RequestMethod.POST)
    @ResponseBody
    public List tree(@RequestParam Map<String, Object> mapvo) {
        Map queryMap = new HashMap();
        if (mapvo.get(ID) == null || mapvo.get(ID).equals("")) {
            queryMap.put(Org.PARENT, null);
        } else {
            queryMap.put(Org.PARENT, mapvo.get(ID));
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

        return JsonResult.success(mapvo);
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(String json) {
        Map<String, List> listvo = (Map) JSONUtil.stringToMap(json);
        orgRepos.deleteOrg(listvo.get("ids"));

        return JsonResult.success("ok");
    }
}
