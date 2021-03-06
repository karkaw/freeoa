package com.damuzee.service.task.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/11/24.
 */
public interface TaskRepos {
    public abstract  String saveTask(Map map);

    public abstract List findTasks(Map map);

    public abstract Map findTask(Map map);

    public abstract  void updateTask(Map map);

    public abstract Map findTaskByPage(Map map);

    public abstract  void deleteTaskById(List<String> idList);
}
