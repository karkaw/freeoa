package com.damuzee.workflow.definition.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 14-11-1.
 */
public interface ObjectRepos {
    public abstract  String saveObject(Map map);

    public abstract List findObjects(Map map);

    public abstract  void updateObject(Map map);

    public abstract Map findObjectByPage(Map map);

    public abstract  void deleteObjectById(List<String> idList);
}
