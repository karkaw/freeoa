package com.damuzee.service.definition.repos;

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

    /**
     *通过对象编码查询对象属性
     *
     * @param
     *  codeMap [code]  对象编码
     *
     * @return
     *  attribute 对象属性
     *
     */
    public abstract  List findObjectsAttrs(Map codeMap);
}
