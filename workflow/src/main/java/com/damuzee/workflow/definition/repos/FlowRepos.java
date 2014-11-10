package com.damuzee.workflow.definition.repos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by karka.w on 14-11-1.
 */
public interface FlowRepos {
    public abstract  String saveFlow(Map map);

    public abstract List findFlows(Map map);

    public abstract  void updateFlow(Map map);

    public abstract Map findFlowByPage(Map map);

    public abstract  void deleteFlowById(List<String> idList);


}
