package com.damuzee.system.perm.repos.impl;

import com.damuzee.system.perm.repos.RoleRepos;
import org.damuzee.mongo.MongoTemplate;
import org.damuzee.mongo.annotation.Collectoion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by K.K on 2014/10/27.
 */
@Service
@Collectoion(name="resource")
public class RoleReposImpl implements RoleRepos {

    @Autowired
    MongoTemplate template;

    public String saveRole(Map map) {
        template.setCollection("roles");
        return template.save(map);
    }

    public List findRoles(Map map) {
        List list =  new ArrayList();
        return list;
    }

    public  void updateRole(Map map){
        template.setCollection("roles");
        template.update(map);
    }
}
