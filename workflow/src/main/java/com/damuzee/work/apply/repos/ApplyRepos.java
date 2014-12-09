package com.damuzee.work.apply.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/11/24.
 */
public interface ApplyRepos {
    public abstract  String saveApply(Map map);

    public abstract List findApplys(Map map);

    public abstract Map findApply(Map map);

    public abstract  void updateApply(Map map);

    public abstract Map findApplyByPage(Map map);

    public abstract  void deleteApplyById(List<String> idList);
}
