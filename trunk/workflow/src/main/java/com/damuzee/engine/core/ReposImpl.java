package com.damuzee.engine.core;


import com.damuzee.engine.AbstractRepos;
import org.bson.types.ObjectId;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karka.w on 2014/12/8.
 */
@Service
public class ReposImpl implements AbstractRepos {

    public static final String  FLOW = "flows" ;

    @Autowired
    MongoTemplate template ;

    public Map getProcessById(String id) {
        Map _idMap = new HashMap();
        _idMap.put("_id",new ObjectId(id));

        Map map = template.findOne(FLOW,_idMap);
        return  map;
    }

    public Map getProcessByCode(String code) {
        Map _idMap = new HashMap();
        _idMap.put("ocode", code);

        Map map = template.findOne(FLOW,_idMap);
        return map;
    }
}
