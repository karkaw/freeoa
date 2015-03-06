package com.damuzee.service.perm.repos.impl;

import com.damuzee.service.perm.repos.OrgRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/7/18.
 */
@Service
public class OrgReposImpl extends OrgRepos {

    private static final String ORG = "org" ;

    @Autowired
    MongoTemplate template;

    public String saveOrg(Map map) {
        return template.save(ORG,map);
    }

    public List findOrg(Map map) {
        return template.find(ORG,map);
    }

    public Map findOrgByPage(Map map) {
        return template.findByPage(ORG,map);
    }

    public void deleteOrg(List<String> idList) {
        for (String id : idList) {
            template.deleteById(ORG,id);
        }
    }

    public void updateOrg(Map map) {
        template.update(ORG,map);
    }
}
