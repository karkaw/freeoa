package com.damuzee.workflow.definition.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 流程流程审批单 ---对象定义
 * Created by K.K on 2014/10/31.
 */
@Controller
@RequestMapping(value = "object")
public class ObjectAct {
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String vlist(ModelMap map) {

        return "/object/list";
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public String add(ModelMap map) {

        return "/object/add";
    }
}
