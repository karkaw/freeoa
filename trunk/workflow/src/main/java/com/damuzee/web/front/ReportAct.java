package com.damuzee.web.front;

import com.damuzee.core.util.JSONUtil;
import com.damuzee.service.report.repos.ReportRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karka.w on 15-3-6.
 */
@Controller
@RequestMapping("report")
public class ReportAct {

    @Autowired
    ReportRepos reportRepos;

    @RequestMapping("save")
    public String save(String json){
        Map<String, Object> mapvo = JSONUtil.stringToMap(json);

        if (mapvo.get("_id") == null){
            reportRepos.saveReport(mapvo);
        }else{
            reportRepos.updateReport(mapvo);
        }
        return "/query" ;
    }
    @RequestMapping("query")
    public String query(String  keywold){
            Map queryMap = new HashMap();
            queryMap.put("keyworld",keywold);
            reportRepos.findReportByPage(queryMap) ;
        return "/query" ;
    }
}
