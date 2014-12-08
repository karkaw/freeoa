package com.damuzee.engine;

import com.damuzee.engine.domain.*;
import com.damuzee.engine.domain.Process;

/**
 * 获取定义的操作类
 *
 * Created by karka.w on 2014/12/5.
 */
public interface IProcessService {

    /**
     * 根据主键ID获取流程定义对象
     * @param id 流程定义id
     * @return Process 流程定义对象
     */
     Process getProcessById(String id);

    /**
     * 根据流程name获取流程定义对象
     * @param name 流程定义名称
     * @return Process 流程定义对象
     */
     Process getProcessByName(String name);


}
