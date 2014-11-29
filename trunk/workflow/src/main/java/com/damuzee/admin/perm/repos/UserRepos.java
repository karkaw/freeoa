package com.damuzee.admin.perm.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by  karka.w on 2014/7/18.
 */
public abstract class UserRepos {

    public abstract  String saveUser(Map map);

    public abstract List findUsers(Map map);

    public abstract Map findUserByPage(Map map) ;

    public abstract  void deleteUser(List<String> idList);

    public abstract  void updateUser(Map map);
}
