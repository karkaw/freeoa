package com.damuzee.report.surgery.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2014/7/18.
 */

@Controller
@RequestMapping(value = "/surgery")
public class RecordAct {

    @RequestMapping(value = "/index.do")
    public String index(){
        return "/surgery/index";
    }

    @RequestMapping(value = "/list.do")
    public String list(){
        return "/surgery/list";
    }

    @RequestMapping(value = "/add.do")
    public String add(){
        return "/surgery/add";
    }


}
