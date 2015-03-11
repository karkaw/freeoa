package com.damuzee.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by karka.w on 15-3-7.
 */
@Controller
public class IndexAct {
    @RequestMapping("/")
    public String index(){
        return "/index";
    }
}
