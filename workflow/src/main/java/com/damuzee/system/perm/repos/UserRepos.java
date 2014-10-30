package com.damuzee.system.perm.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by  karka.w on 2014/7/18.
 */
public abstract class UserRepos {

    public abstract  String saveUser(Map map);

    public abstract List findUsers(Map map);
}
