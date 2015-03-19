package com.damuzee.service.perm.repos.impl;

import com.damuzee.core.auth.domain.ShiroUser;
import com.damuzee.core.auth.util.EndecryptUtils;
import com.damuzee.service.perm.domain.Employee;
import com.damuzee.core.auth.util.EncryptUtils;
import com.damuzee.service.perm.repos.UserRepos;
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
        String username = (String)map.get(Employee.USER_NAME);
        String password = (String)map.get(Employee.PASS_WORD);

        ShiroUser user = EndecryptUtils.md5Password(username, password);
        if (password != null){
            map.put(Employee.PASS_WORD, user.getPassword());
            map.put(Employee.SALT, user.getSalt());
        }
        String org_text = (String)map.get(Employee.ORG_TEXT);
        List orgList = new ArrayList();
        if(org_text !=null && !"".equals(org_text)){
            String [] org_texts = org_text.split(";") ;
            for(String text : org_texts){
                orgList.add(text.split(",")[0]);
            }
            map.put(Employee.ORG_CODE,orgList);
        }
        return template.save(USERINFO,map);
    }

    public   List findUsers(Map map){
       return  template.find(USERINFO,map);
    }

    @Override
    public Map findUser(Map map) {
        return  template.findOne(USERINFO,map);
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
