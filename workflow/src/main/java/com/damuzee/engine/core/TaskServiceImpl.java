package com.damuzee.engine.core;

import com.damuzee.engine.ITaskService;
import com.damuzee.engine.domain.Task;
import com.damuzee.engine.model.FlowModel;
import org.springframework.stereotype.Service;

/**
 * Created by karka.w on 2014/12/8.
 */
@Service
public class TaskServiceImpl implements ITaskService {

    @Override
    public Task complete(String taskId, String operator) {
        return null;
    }

    @Override
    public Task rejectTask(FlowModel model, Task currentTask) {
        return null;
    }
}
