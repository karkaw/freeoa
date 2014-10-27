package com.damuzee.system.perm.repos.impl;

import com.damuzee.system.perm.repos.RoleRepos;
import org.damuzee.mongo.annotation.Collectoion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by K.K on 2014/10/27.
 */
@Service
@Collectoion(name="resource")
public class RoleReposImpl implements RoleRepos {
    public String saveRole(Map map) {
        return null;
    }

    public List findRoles(Map map) {
        List list =  new ArrayList();
        return list;
    }
}
