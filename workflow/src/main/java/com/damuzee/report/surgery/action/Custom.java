package com.damuzee.report.surgery.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 14-7-20.
 */
@Controller
@RequestMapping(value="/custom")
public class Custom {

    @RequestMapping(value="add")
    public String add(){
        return "/custom/add";
    }

    @RequestMapping(value = "save")
    public String save(){
        return "/custom/list";
    }
}
