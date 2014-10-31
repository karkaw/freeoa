package com.damuzee.system.perm.repos.impl;

import com.damuzee.system.perm.repos.ResourceRepos;
import org.damuzee.mongo.MongoTemplate;
import org.damuzee.mongo.annotation.Collectoion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/7/18.
 */
@Service
@Collectoion(name="resource")
public class ResourceReposImpl extends ResourceRepos {

    private static final String RESOURCE = "resource" ;
    @Autowired
    MongoTemplate template;

    public String saveResource(Map map) {
        return template.save(RESOURCE,map);
    }

    public   List findResource(Map map){
       return  template.find(RESOURCE,map);
    }
    public   Map findResourceByPage(Map map){
        return  template.findByPage(RESOURCE,map);
    }

    public void deleteResource(List<String> idList) {
        for (String id : idList){
            template.deleteById(RESOURCE,id);
        }
    }

    public   void updateResource(Map map){
        template.update(RESOURCE,map);
    }
}
