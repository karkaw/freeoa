package com.damuzee.workflow.definition.repos.impl;

import com.damuzee.workflow.definition.repos.FlowRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 14-11-7.
 */

@Service
public class FlowReposImpl implements FlowRepos{
    private static  final String FLOW = "flows" ;

    private static  final  String ATTRIBUTE = "attribute";

    @Autowired
    MongoTemplate template;

    public String saveFlow(Map map) {


        return template.save(FLOW,map);
    }

    public List findFlows(Map map) {
        List list = template.find(FLOW,map);
        return list;
    }

    public  void updateFlow(Map map){
        template.update(FLOW,map);
    }

    public  Map findFlowByPage(Map map){
        return  template.findByPage(FLOW,map);
    }

    public void deleteFlowById(List<String> idList) {
        for (String id : idList){
            template.deleteById(FLOW,id);
        }
    }

}
