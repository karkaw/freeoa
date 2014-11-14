package com.damuzee.workflow.definition.action;

import com.damuzee.core.util.ConfigUtil;
import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.module.file.service.FileService;
import com.damuzee.workflow.definition.repos.TemplateRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by K.K on 2014/11/12.
 */

@Controller
@RequestMapping(value = "template")
public class TemplateAct {

    @Autowired
    TemplateRepos templateRepos ;

    @Autowired
    FileService fileService ;

    @RequestMapping(value = "list.do")
    public String list(){

        return "/template/list" ;
    }

    @RequestMapping(value = "add.do")
    public String add(){

        return "/template/add" ;
    }

    @RequestMapping(value = "jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> jlist(String json){
        Map<String,Object> mapvo = JSONUtil.stringToMap(json);
        Map<String,Object> map = templateRepos.findTemplateByPage(mapvo);
        return map ;
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
        fileService.writeStringToFile(tempPath , name  + ".ftl" ,content ) ;

        return "/employee/list" ;
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(String json) {
        Map<String, List> listvo = (Map) JSONUtil.stringToMap(json);
        templateRepos.deleteTemplate(listvo.get("ids"));

        return JsonResult.success("ok");
    }

}
