package com.damuzee.engine.core;

import com.damuzee.engine.IFlowService;
import com.damuzee.engine.domain.Flow;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 流程定义的服务
 * 	
 * 
 * Created by karka.w on 2014/12/8.
 */
@Service
public class FlowServiceImpl extends ReposImpl  implements IFlowService {

	/**
	 * 根据流程表单获取定义
	 */
    public Flow getFlowById(String id) {
        Map flowMap = super.getFlowById(id);
        Flow flow = new Flow();
        flow.putAll(flowMap);
        return  flow.warp(flowMap);
    }

    /**
     * 根据流程的编码获取定义
     */
    public Flow getFlowByCode(String name) {
        Map flowMap = super.getFlowByCode(name);
        Flow flow = new Flow();
        return flow.warp(flowMap);
    }
}
