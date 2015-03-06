package com.damuzee.web.admin;

import com.damuzee.service.perm.domain.Resource;
import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.service.perm.repos.ResourceRepos;
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
 * 资源管理和操作管理
 * <p/>
 * Created by  karka.w on 14-7-27.
 */

@Controller
@RequestMapping(value = "resource")
public class ResourceAct {

    public static final String ID = "id";
    public static final String _ID = "_id";

    @Autowired
    private ResourceRepos resourceRepos;

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String vlist(ModelMap map) {

        return "/resource/tree";
    }

    @RequestMapping(value = "/add.do")
    public String add(Map resMap) {
        return "/resource/add";
    }

    @RequestMapping(value = "/list.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult list(String json) {
        Map map = JSONUtil.stringToMap(json);

        Map list = resourceRepos.findResourceByPage(map);
        JsonResult result = JsonResult.page(list);
         return result;
    }

    @RequestMapping(value = "/tree.do", method = RequestMethod.POST)
    @ResponseBody
    public List tree(@RequestParam Map<String, Object> mapvo) {
        Map queryMap = new HashMap();
        if (mapvo.get(ID) == null || mapvo.get(ID).equals("")) {
            queryMap.put(Resource.PARENT, null);
        } else {
            queryMap.put(Resource.PARENT, mapvo.get(ID));
        }
        List list = resourceRepos.findResource(queryMap);
        return list;
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(String json, HttpServletRequest request) {
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);

        if (mapvo.get(_ID) == null){
            resourceRepos.saveResource(mapvo);
        }else{
            resourceRepos.updateResource(mapvo);
        }

        JsonResult result = JsonResult.success(null);

        return null;
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(String json) {
        Map<String, List> listvo = (Map) JSONUtil.stringToMap(json);
        resourceRepos.deleteResource(listvo.get("ids"));

        return JsonResult.success("ok");
    }

}
