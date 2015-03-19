package com.damuzee.engine.model;

import com.damuzee.engine.core.Execution;

/**
 * Created by karka.w on 15-3-15.
 */
public class ForkModel extends NodeModel {
    protected void exec(Execution execution) {
        run(execution);
    }
}
