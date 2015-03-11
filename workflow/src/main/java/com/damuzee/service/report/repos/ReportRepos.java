package com.damuzee.service.report.repos;


import java.util.List;
import java.util.Map;

/**
 * 举报信息服务接口
 */
public interface ReportRepos {
    public String saveReport(Map map);

    public List findReport(Map map);

    public Map findReportByPage(Map map) ;

    public void deleteReport(List<String> idList) ;

    public void updateReport(Map map);
}