package com.damuzee.web.admin;

import com.damuzee.core.auth.domain.ShiroUser;
import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.core.web.session.SessionProvider;
import com.damuzee.engine.WFEngine;
import com.damuzee.engine.domain.Order;
import com.damuzee.engine.domain.Task;
import com.damuzee.core.util.ObjectConvert;
import com.damuzee.service.apply.domain.Apply;
import com.damuzee.service.apply.repos.ApplyRepos;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by K.K on 2014/11/12.
 */

@Controller
@RequestMapping(value = "work/apply")
public class ApplyAct {

    @Autowired
    ApplyRepos applyTepos ;

    @Autowired
    SessionProvider session ;

    @Autowired
    WFEngine engine ;

    @RequestMapping(value = "list.do")
    public String list(){

        return "/work/apply/list" ;
    }

    @RequestMapping(value = "jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> jlist(String json,HttpServletRequest request){
        Map<String,Object> mapvo = JSONUtil.stringToMap(json);

        //创建人
        Map userMap = (Map)session.getAttribute(request, ShiroUser.LOGIN_USER_KEY);
        mapvo.put(Task.OPERATOR,userMap.get(ShiroUser.USER_NAME));

        Map<String,Object> map = applyTepos.findApplyByPage(mapvo);
        return map ;
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.POST)
    @ResponseBody
    public Map get(String id,HttpServletRequest request) {
        if(id != null && !id.equals("")){
            Map map = new HashMap();
            map.put("_id",new ObjectId(id));

            Map temp = applyTepos.findApply(map);

            Map outMap = new HashMap();
            ObjectConvert.convertMapToString(outMap,temp , "","") ;
            return outMap ;
        }
        return null;
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(HttpServletRequest request) {
        Map<String, Object> params = ObjectConvert.convertParamToMap(request);

        Map userMap = (Map)session.getAttribute(request, ShiroUser.LOGIN_USER_KEY);

        params.put(Apply.CREATOR,userMap.get(ShiroUser.USER_NAME));
        String id  = (String)params.remove("_id") ;

        if (id.equals("")){
             id = applyTepos.saveApply(params);

            //启用工作流参数
            Map args = new HashMap() ;
            args.put(Order.FORM_ID,id) ;
            //开始工作流
            engine.startInstanceByName((String)params.get("object_code"),(String)userMap.get(ShiroUser.USER_NAME) ,args);
        }else{
             params.put("_id",new ObjectId(id));
             applyTepos.updateApply(params);
        }

        return JsonResult.success(id);
    }

}
