package com.damuzee.admin.perm.repos.impl;

import com.damuzee.core.auth.util.EncryptUtils;
import com.damuzee.admin.perm.repos.UserRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by  karka.w on 2014/7/18.
 */
@Service
public class UserReposImpl  extends UserRepos {

    private static  final String USERINFO  = "userinfo";

    @Autowired
    MongoTemplate template;

    public String saveUser(Map map) {
        String password = (String)map.get("passWord");
        if (password != null){
            map.put("passWord", EncryptUtils.encryptMD5(password));
        }
        return template.save(USERINFO,map);
    }

    public   List findUsers(Map map){
       return  template.find(USERINFO,map);
    }

    public  Map findUserByPage(Map map){
        return  template.findByPage(USERINFO,map);
    }
}
