package com.damuzee.service.perm.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by  karka.w on 2014/7/18.
 */
public abstract class OrgRepos {

    public abstract  String saveOrg(Map map);

    public abstract List findOrg(Map map);
    public abstract Map findOrgByPage(Map map);

    public abstract  void deleteOrg(List<String> idList);
    public abstract  void updateOrg(Map map);
}
