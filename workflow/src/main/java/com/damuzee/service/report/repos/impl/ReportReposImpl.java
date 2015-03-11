package com.damuzee.service.report.repos.impl;

import com.damuzee.service.report.repos.ReportRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 15-3-6.
 */

@Service
public class ReportReposImpl implements ReportRepos {
    
    private static final String REPORT = "report" ;
    
    @Autowired
    MongoTemplate template;

    public String saveReport(Map map) {
        return template.save(REPORT,map);
    }

    public List findReport(Map map) {
        return template.find(REPORT,map);
    }

    public Map findReportByPage(Map map) {

        return template.findByPage(REPORT,map);
    }

    public void deleteReport(List<String> idList) {
        for (String id : idList) {
            template.deleteById(REPORT,id);
        }
    }

    public void updateReport(Map map) {
        template.update(REPORT,map);
    }
}
