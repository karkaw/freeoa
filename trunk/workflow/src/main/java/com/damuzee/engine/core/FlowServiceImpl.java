package com.damuzee.engine.core;

import com.damuzee.engine.IFlowService;
import com.damuzee.engine.domain.Flow;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by karka.w on 2014/12/8.
 */
@Service
public class FlowServiceImpl extends ReposImpl  implements IFlowService {

    public Flow getFlowById(String id) {
        Map flowMap = super.getFlowById(id);
        Flow flow = new Flow();
        flow.putAll(flowMap);
        return  flow.warp(flowMap);
    }


    public Flow getFlowByCode(String name) {
        Map flowMap = super.getFlowByCode(name);
        Flow flow = new Flow();
        return flow.warp(flowMap);
    }
}
