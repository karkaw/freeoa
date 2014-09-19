package com.damuzee.system.perm.repos;

import org.damuzee.mongo.MongoMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/7/18.
 */
public abstract class UserRepos {

    public abstract  String saveUser(Map map);

    public abstract List findUsers(Map map);
}
