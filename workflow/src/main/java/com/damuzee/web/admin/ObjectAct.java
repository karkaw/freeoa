package com.damuzee.web.admin;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.service.definition.repos.ObjectRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 流程流程审批单 ---对象定义
 * Created by K.K on 2014/10/31.
 */
@Controller
@RequestMapping(value = "wf/object")
public class ObjectAct {

    @Autowired
    ObjectRepos objectRepos ;

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String vlist(ModelMap map) {

        return "/workflow/object/list";
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public String add(ModelMap map) {

        return "/workflow/object/add";
    }

    @RequestMapping(value = "/jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult jList(ModelMap map) {
        Map list = objectRepos.findObjectByPage(map);
        JsonResult result = JsonResult.page(list);
        return result;
    }


    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(String json, HttpServletRequest request) {
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);

        if (mapvo.get("_id") == null){
            objectRepos.saveObject(mapvo);
        }else{
            objectRepos.updateObject(mapvo);
        }

        JsonResult result = JsonResult.success(null);

        return result;
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.POST)
    @ResponseBody
    public List get(String json, HttpServletRequest request) {
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);

        List objects = objectRepos.findObjects(mapvo);

        return objects;
    }

    @RequestMapping(value = "/getAttr.do", method = RequestMethod.POST)
    @ResponseBody
    public List getAttr(String json,HttpServletRequest request){
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);
        List attrs = objectRepos.findObjectsAttrs(mapvo);
        return attrs;
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(String json, HttpServletRequest request) {
        Map<String, List> listvo = (Map) JSONUtil.stringToMap(json);
        objectRepos.deleteObjectById(listvo.get("ids"));

        return JsonResult.success("ok");
    }

}
