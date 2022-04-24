package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.Source;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public interface RouterFlow extends AggregatableComponent, Flow {

    List<Flow> route(Source<?> source);
}
