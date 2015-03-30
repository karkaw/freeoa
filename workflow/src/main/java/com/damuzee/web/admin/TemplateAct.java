package com.damuzee.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.damuzee.mongo.MongoConstaints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.damuzee.core.util.ConfigUtil;
import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.service.definition.repos.TemplateRepos;
import com.damuzee.service.file.repos.FileRepos;

/**
 * Created by K.K on 2014/11/12.
 */

@Controller
@RequestMapping(value = "wf/template")
public class TemplateAct {

    public static final String FORMID = "formid" ;

    @Autowired
    TemplateRepos templateRepos ;

    @Autowired
    FileRepos fileRepos ;

    @RequestMapping(value = "list.do")
    public String list(){

        return "/workflow/template/list" ;
    }

    @RequestMapping(value = "add.do")
    public String add(){

        return "/workflow/template/add" ;
    }

    @RequestMapping(value = "design.do")
    public String design(String id,HttpServletRequest request,ModelMap model){
    	if(id != null){
    		Map<String,Object> mapvo = new HashMap<String,Object>();
    		mapvo.put("_id", new ObjectId(id));
    		Map<String,Object> map = templateRepos.findTemplateById(mapvo);
    		model.put("json_props", JSON.toJSONString(map.get("props"))) ;
    		model.put("content", map.get("content")) ;
    	}
        return "/workflow/template/design" ;
    }

    @RequestMapping(value = "jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> jlist(String json){
        Map<String,Object> mapvo = JSONUtil.stringToMap(json);
        Map<String,Object> map = templateRepos.findTemplateByPage(mapvo);
        return map ;
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.POST)
    @ResponseBody
    public List get(String json, HttpServletRequest request) {
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);

        List objects = templateRepos.findTemplate(mapvo);

        return objects;
    }

    @RequestMapping(value = "/find.do", method = RequestMethod.POST)
    @ResponseBody
    public Map find(String json, HttpServletRequest request) {
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);

        Map object = templateRepos.findTemplateById(mapvo);

        return object;
    }

    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    @ResponseBody
    public String save(String json){
        Map<String,Object> mapvo = JSONUtil.stringToMap(json);
        templateRepos.saveTemplate(mapvo);

        //保存到文件
        String name =  (String)mapvo.get("code");
        String content = (String)mapvo.get("context");
        String path = ConfigUtil.config.get("server:realpath") +
                      ConfigUtil.config.get("template:path")  ;

        String tempPath = "E:\\damuzee2\\workflow\\src\\main\\webapp\\WEB-INF\\template\\apply\\" ;
        fileRepos.writeStringToFile(tempPath , name  + ".ftl" ,content ) ;

        return "/employee/list" ;
    }

    @RequestMapping(value = "nsave.do",method = RequestMethod.POST)
    @ResponseBody
    public String nsave(String json){
        Map<String,Object> mapvo = JSONUtil.stringToMap(json);
        String formid = null ;
        if(mapvo.containsKey(FORMID)){
            formid = (String)mapvo.remove(FORMID);
            mapvo.put(MongoConstaints.ID,formid);
            templateRepos.updateTemplate(mapvo);
        }else {
            formid = templateRepos.saveTemplate(mapvo);
        }
        return formid ;
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(String json) {
        Map<String, List> listvo = (Map) JSONUtil.stringToMap(json);
        templateRepos.deleteTemplate(listvo.get("ids"));

        return JsonResult.success("ok");
    }

}
