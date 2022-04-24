package com.icepoint.base.api.supports;

import lombok.experimental.UtilityClass;

/**
 * @author Jiawei Zhao
 */
@UtilityClass
public class PropertyComparators {

    public static final OrderedComparator ORDERED = new OrderedComparator();

    public static final ValueComparator VALUE = new ValueComparator();
}
