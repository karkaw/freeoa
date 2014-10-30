package com.damuzee.system.perm.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/10/27.
 */
public interface RoleRepos {
    public abstract  String saveRole(Map map);

    public abstract List findRoles(Map map);

    public abstract  void updateRole(Map map);

    public abstract Map findResourceByPage(Map map);
}
