package com.damuzee.work.apply.action;

import com.damuzee.core.auth.domain.ShiroUser;
import com.damuzee.core.util.JSONUtil;
import com.damuzee.core.web.bean.JsonResult;
import com.damuzee.core.web.session.SessionProvider;
import com.damuzee.engine.WFEngine;
import com.damuzee.web.util.ObjectConvert;
import com.damuzee.work.apply.domain.Apply;
import com.damuzee.work.apply.repos.ApplyRepos;
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
@RequestMapping(value = "apply")
public class ApplyAct {

    @Autowired
    ApplyRepos applyTepos ;

    @Autowired
    SessionProvider session ;

    @Autowired
    WFEngine engine ;

    @RequestMapping(value = "list.do")
    public String list(){

        return "/apply/list" ;
    }

    @RequestMapping(value = "jlist.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> jlist(String json){
        Map<String,Object> mapvo = JSONUtil.stringToMap(json);
        Map<String,Object> map = applyTepos.findApplyByPage(mapvo);
        return map ;
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.POST)
    @ResponseBody
    public Map get(String id) {
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
        }else{
             params.put("_id",new ObjectId(id));
              applyTepos.updateApply(params);
        }

        //开始工作流
        engine.startInstanceByName((String)params.get("object_code"),(String)userMap.get(ShiroUser.USER_NAME));

        return JsonResult.success(id);
    }

}
