package com.damuzee.service.task.repos.impl;

import com.damuzee.service.task.repos.TaskRepos;
import org.damuzee.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 2014/11/24.
 */
@Service
public class TaskReposImpl implements TaskRepos{
    private static  final String COLLECTION = "task" ;

    @Autowired
    MongoTemplate template;

    public String saveTask(Map map) {
        return template.save(COLLECTION,map);
    }

    public List findTasks(Map map) {
        List list = template.find(COLLECTION,map);
        return list;
    }

    public Map findTask(Map map) {
        Map templateMap = template.findOne(COLLECTION,map);
        return templateMap;
    }

    public  void updateTask(Map map){
        template.update(COLLECTION,map);
    }

    public  Map findTaskByPage(Map map){
        return  template.findByPage(COLLECTION,map);
    }

    public void deleteTaskById(List<String> idList) {
        for (String id : idList){
            template.deleteById(COLLECTION,id);
        }
    }
}
