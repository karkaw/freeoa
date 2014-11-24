package com.damuzee.workflow.definition.repos;

import java.util.List;
import java.util.Map;

/**
 * Created by K.K on 2014/11/13.
 */
public abstract class TemplateRepos {
    public abstract  String saveTemplate(Map map);

    public abstract List findTemplate(Map map);
    public abstract Map findTemplateById(Map map);
    public abstract Map findTemplateByPage(Map map);

    public abstract  void deleteTemplate(List<String> idList);
    public abstract  void updateTemplate(Map map);
}
