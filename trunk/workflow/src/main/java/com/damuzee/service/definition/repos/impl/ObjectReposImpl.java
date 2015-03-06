package com.damuzee.service.definition.repos.impl;

import com.damuzee.service.definition.repos.ObjectRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 14-11-1.
 */

@Service
public class ObjectReposImpl implements ObjectRepos{

    private static  final String OBJECTS = "objects" ;

    private static  final  String ATTRIBUTE = "attribute";
    @Autowired
    MongoTemplate template;

    public String saveObject(Map map) {
        return template.save(OBJECTS,map);
    }

    public List findObjects(Map map) {
        List list = template.find(OBJECTS,map);
        return list;
    }

    public  void updateObject(Map map){
        template.update(OBJECTS,map);
    }

    public  Map findObjectByPage(Map map){
        return  template.findByPage(OBJECTS,map);
    }

    public void deleteObjectById(List<String> idList) {
        for (String id : idList){
            template.deleteById(OBJECTS,id);
        }
    }

    public    List findObjectsAttrs(Map codeMap){
        Map<String,Object> attrsMap = template.findOne(OBJECTS, codeMap);
        List attrs = (List)attrsMap.get(ATTRIBUTE);

        return attrs == null ? new ArrayList(): attrs ;
    }
}
