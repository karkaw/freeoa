package com.damuzee.engine.core;

import com.damuzee.engine.IProcessService;
import com.damuzee.engine.domain.Flow;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by karka.w on 2014/12/8.
 */
@Service
public class ProcessServiceImpl extends ReposImpl  implements IProcessService {

    public Flow getProcessById(String id) {
        Map flowMap = super.getProcessById(id);

        return Flow.wrap(flowMap);
    }

    public Flow getProcessByCode(String name) {
        Map flowMap = super.getProcessByCode(name);
        return Flow.wrap(flowMap);
    }
}
