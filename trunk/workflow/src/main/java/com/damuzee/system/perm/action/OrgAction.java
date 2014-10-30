package com.damuzee.system.perm.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by K.K on 2014/9/26.
 */
@Controller
@RequestMapping(value = "/org")
public class OrgAction {

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String vlist(ModelMap map) {

        return "/org/tree";
    }

}
