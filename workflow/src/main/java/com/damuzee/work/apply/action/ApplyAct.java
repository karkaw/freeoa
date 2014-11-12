package com.damuzee.work.apply.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by K.K on 2014/11/12.
 */

@Controller
@RequestMapping(value = "apply")
public class ApplyAct {

    @RequestMapping(value = "list.do")
    public String list(){

        return "/apply/list" ;
    }
}
