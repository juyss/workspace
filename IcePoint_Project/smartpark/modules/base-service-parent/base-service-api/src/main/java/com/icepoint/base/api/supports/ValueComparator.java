package com.icepoint.base.api.supports;

import com.icepoint.base.api.domain.GenericProperty;
import org.springframework.util.comparator.Comparators;

import java.util.Comparator;
import java.util.Objects;

public class ValueComparator implements Comparator<GenericProperty> {


    @Override
    public int compare(GenericProperty o1, GenericProperty o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o2 == null) {
            return 1;
        }
        if (o1 == null) {
            return 0;
        }
        if (o1.getValue() == o2.getValue()) {
            return 0;
        }
        return Objects.compare(o1.getValue(), o2.getValue(), Comparators.nullsLow());
    }
}
