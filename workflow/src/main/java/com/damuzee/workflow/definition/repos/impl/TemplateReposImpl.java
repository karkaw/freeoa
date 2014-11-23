package com.damuzee.workflow.definition.repos.impl;

import com.damuzee.workflow.definition.repos.TemplateRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by K.K on 2014/11/13.
 */

@Service
public class TemplateReposImpl extends TemplateRepos{

    private static final String TEMPLATE = "template" ;

    @Autowired
    MongoTemplate template;

    public String saveTemplate(Map map) {
        return template.save(TEMPLATE,map);
    }

    public List findTemplate(Map map) {
        return template.find(TEMPLATE,map);
    }

    public Map findTemplateByPage(Map map) {
        /*MongoMap filter = new MongoMap();
        filter.put("ocode",1);

        map.put(MongoConstaints.PAGE_FILTER,filter);*/
        return template.findByPage(TEMPLATE,map);
    }

    public void deleteTemplate(List<String> idList) {
        for (String id : idList) {
            template.deleteById(TEMPLATE,id);
        }
    }

    public void updateTemplate(Map map) {
        template.update(TEMPLATE,map);
    }
}
