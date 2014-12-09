package com.damuzee.work.apply.repos.impl;

import com.damuzee.work.apply.repos.ApplyRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/11/24.
 */
@Service
public class ApplyReposImpl implements ApplyRepos {
    private static  final String COLLECTION = "order" ;

    @Autowired
    MongoTemplate template;

    public String saveApply(Map map) {
        return template.save(COLLECTION,map);
    }

    public List findApplys(Map map) {
        List list = template.find(COLLECTION,map);
        return list;
    }

    public Map findApply(Map map) {
        Map templateMap = template.findOne(COLLECTION,map);
        return templateMap;
    }

    public  void updateApply(Map map){
        template.update(COLLECTION,map);
    }

    public  Map findApplyByPage(Map map){
        return  template.findByPage(COLLECTION,map);
    }

    public void deleteApplyById(List<String> idList) {
        for (String id : idList){
            template.deleteById(COLLECTION,id);
        }
    }
}
