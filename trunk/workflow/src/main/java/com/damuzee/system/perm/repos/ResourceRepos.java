package com.damuzee.system.perm.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/7/18.
 */
public abstract class ResourceRepos {

    public abstract  String saveResource(Map map);

    public abstract List findResource(Map map);

    public abstract  void deleteResource(List<String> idList);
    public abstract  void updateResource(Map map);
}
