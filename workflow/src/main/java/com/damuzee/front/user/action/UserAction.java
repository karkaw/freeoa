package com.damuzee.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by karka.w on 2014/9/26.
 */
@Controller
@RequestMapping(value = "user")
public class UserAction {

    @RequestMapping(value = "register.do")
    public String list(ModelMap map ){

        return "/regis" ;
    }

    @RequestMapping(value = "login.do")
    public String login(ModelMap map ){

        return "/login" ;
    }

}
