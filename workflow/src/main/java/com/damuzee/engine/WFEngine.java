package com.damuzee.engine;

import java.util.Map;

/**
 * Created by karka.w on 2014/12/4.
 */
public interface WFEngine {

    /**
     *  根据工作流定义启动工作流
     * @param id       实例流水号
     * @param operner  操作人
     * @return
     */
    public Map startInstance(String id,String operner);


}
