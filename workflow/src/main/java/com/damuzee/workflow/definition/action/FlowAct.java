package com.damuzee.workflow.definition.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 流程工作流的定义
 *
 * Created by K.K on 2014/10/31.
 */

@Controller
@RequestMapping(value = "flow")
public class FlowAct {
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String vlist(ModelMap map) {

        return "/flow/list";
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public String add(ModelMap map) {

        return "/flow/add";
    }
}
