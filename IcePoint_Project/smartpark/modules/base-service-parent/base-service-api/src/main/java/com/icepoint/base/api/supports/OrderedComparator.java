package com.icepoint.base.api.supports;

import org.springframework.core.Ordered;

import java.util.Comparator;

public class OrderedComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o2 == null) {
            return 1;
        }
        if (o1 == null) {
            return 0;
        }
        return Integer.compare(((Ordered) o1).getOrder(), ((Ordered) o2).getOrder());
    }
}
