package com.damuzee.front.index.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by karka.w on 14-9-20.
 */

@Controller
public class IndexAction {

    @RequestMapping(value = "/index.do")
    public String index(){

        return "/index" ;
    }

}
