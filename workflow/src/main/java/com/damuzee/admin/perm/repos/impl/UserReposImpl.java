package com.damuzee.admin.perm.repos.impl;

import com.damuzee.admin.perm.domain.Employee;
import com.damuzee.engine.auth.util.EncryptUtils;
import com.damuzee.admin.perm.repos.UserRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String password = (String)map.get(Employee.PASS_WORD);
        if (password != null){
            map.put(Employee.PASS_WORD, EncryptUtils.encryptMD5(password));
        }
        String orgText = (String)map.get(Employee.ORG_TEXT);
        List orgList = new ArrayList();
        if(orgText !=null && !"".equals(orgText)){
            String [] orgTexts = orgText.split(";") ;
            for(String text : orgTexts){
                orgList.add(text.split(",")[0]);
            }
            map.put(Employee.ORG_CODE,orgList);
        }
        return template.save(USERINFO,map);
    }

    public   List findUsers(Map map){
       return  template.find(USERINFO,map);
    }

    public  Map findUserByPage(Map map){
        return  template.findByPage(USERINFO,map);
    }

    public void deleteUser(List<String> idList) {
        for (String id : idList) {
            template.deleteById(USERINFO,id);
        }
    }

    public void updateUser(Map map) {
        template.update(USERINFO,map);
    }
}
