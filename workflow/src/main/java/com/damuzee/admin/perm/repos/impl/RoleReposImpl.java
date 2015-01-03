package com.damuzee.admin.perm.repos.impl;

import com.damuzee.admin.perm.domain.Employee;
import com.damuzee.admin.perm.domain.Org;
import com.damuzee.admin.perm.repos.RoleRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
        String org_text = (String)map.get(Org.ORG_TEXT);
        List orgList = new ArrayList();
        if(org_text !=null && !"".equals(org_text)){
            String [] org_texts = org_text.split(";") ;
            for(String text : org_texts){
                orgList.add(text.split(",")[0]);
            }
            map.put(Org.ORG_CODE,orgList);
        }
        return template.save(ROLES,map);
    }

    public List findRoles(Map map) {
        Map in= new HashMap();

        String org_text = (String)map.get(Org.ORG_TEXT);
        Map  query = new HashMap() ;
        List orgList = new ArrayList();
        if(org_text != null && !"".equals(org_text)){
            String [] org_texts = org_text.split(";") ;
            for(String text : org_texts){
                orgList.add(text.split(",")[0]);
            }
            in.put("$in",orgList);
            query.put(Org.ORG_CODE,in);
        }

        return template.find(ROLES,query);
    }

    public Map findRole(Map map) {

        return template.findOne(ROLES,map);
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
