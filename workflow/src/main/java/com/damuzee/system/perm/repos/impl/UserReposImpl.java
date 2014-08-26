package com.damuzee.system.perm.repos.impl;

import com.damuzee.core.auth.util.EncryptUtils;
import com.damuzee.system.perm.repos.UserRepos;
import org.damuzee.mongo.MongoTemplate;
import org.damuzee.mongo.annotation.Collectoion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/7/18.
 */
@Service
@Collectoion(name="userinfo")
public class UserReposImpl  extends UserRepos {

    @Autowired
    MongoTemplate template;

    public String saveUser(Map map) {
        String password = (String)map.get("passWord");
        if (password != null){
            map.put("passWord", EncryptUtils.encryptMD5(password));
        }
        template.setCollection("userinfo");
        return template.save(map);
    }

    public   List findUsers(Map map){
        template.setCollection("userinfo");
       return  template.find(map);
    }
}
