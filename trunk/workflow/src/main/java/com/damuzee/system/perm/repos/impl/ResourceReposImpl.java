package com.damuzee.system.perm.repos.impl;

import com.damuzee.core.auth.util.EncryptUtils;
import com.damuzee.system.perm.repos.ResourceRepos;
import com.damuzee.system.perm.repos.UserRepos;
import org.damuzee.mongo.MongoMap;
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

    @Autowired
    MongoTemplate template;

    public String saveResource(Map map) {
        template.setCollection("resource");
        return template.save(map);
    }

    public   List findResource(Map map){
        template.setCollection("resource");
       return  template.find(map);
    }
    public   Map findResourceByPage(Map map){
        template.setCollection("resource");
        return  template.findByPage(map);
    }

    public void deleteResource(List<String> idList) {
        template.setCollection("resource");
        for (String id : idList){
            template.deleteById(id);
        }
    }

    public   void updateResource(Map map){
        template.setCollection("resource");
        template.update(map);
    }
}
