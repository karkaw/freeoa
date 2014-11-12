package com.damuzee.admin.perm.repos.impl;

import com.damuzee.admin.perm.repos.RoleRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by K.K on 2014/10/27.
 */
@Service
public class RoleReposImpl implements RoleRepos {

    private static  final String ROLES = "roles" ;
    @Autowired
    MongoTemplate template;

    public String saveRole(Map map) {
        return template.save(ROLES,map);
    }

    public List findRoles(Map map) {
        List list =  new ArrayList();
        return list;
    }

    public  void updateRole(Map map){
        template.update(ROLES,map);
    }

    public  Map findRoleByPage(Map map){
        return  template.findByPage(ROLES,map);
    }

    public void deleteRoleById(List<String> idList) {
        for (String id : idList){
            template.deleteById(ROLES,id);
        }
    }
}
