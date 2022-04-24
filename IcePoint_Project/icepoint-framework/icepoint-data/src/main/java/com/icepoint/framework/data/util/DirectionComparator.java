package com.icepoint.framework.data.util;

import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.NumberUtils;

import java.util.Comparator;

/**
 * @author Jiawei Zhao
 */
public class DirectionComparator implements Comparator<Comparable<Object>> {

    private final Sort.Direction direction;

    private final boolean nullValueLast;

    public DirectionComparator(Sort.Direction direction) {
        this.direction = direction;
        this.nullValueLast = true;
    }

    @Override
    public int compare(@Nullable Comparable<Object> o1, @Nullable Comparable<Object> o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return nullValueLast ? -1 : 1;
        }
        if (o2 == null) {
            return nullValueLast ? 1 : 0;
        }
        return direction.equals(Sort.Direction.ASC)
                ? castIfNecessary(o1).compareTo(castIfNecessary(o2))
                : castIfNecessary(o2).compareTo(castIfNecessary(o1));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Comparable<Object> castIfNecessary(Comparable<Object> o) {
        try {
            return (Comparable) NumberUtils.parseNumber(o.toString(), Long.class);
        } catch (IllegalArgumentException e) {
            return o;
        }
    }
}
