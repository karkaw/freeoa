package com.damuzee.workflow.definition.action;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.workflow.definition.domain.Flow;
import com.damuzee.workflow.definition.repos.FlowRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程工作流的定义
 *
 * Created by K.K on 2014/10/31.
 */

@Controller
@RequestMapping(value = "flow")
public class FlowAct {

    @Autowired
    FlowRepos flowRepos ;

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String vlist(ModelMap map) {

        return "/flow/list";
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public String add(ModelMap map) {

        return "/flow/add";
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(String json, HttpServletRequest request) {
        System.out.print(json);
        Map<String, Object> listvo = (Map<String, Object>) JSONUtil.stringToMap(json);
        Map<String,Object> flowMap = new HashMap<String, Object>();
        flowMap.put(Flow.RESTORE,json);

        flowRepos.saveFlow(listvo);
        return JsonResult.success("ok");
    }
}
