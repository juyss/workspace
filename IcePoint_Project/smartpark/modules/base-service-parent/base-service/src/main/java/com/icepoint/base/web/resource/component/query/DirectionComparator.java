package com.icepoint.base.web.resource.component.query;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.util.NumberUtils;

import java.util.Comparator;

@AllArgsConstructor
public class DirectionComparator implements Comparator<Comparable<Object>> {

    private final Sort.Direction direction;

    private final boolean nullValueLast;

    public DirectionComparator(Sort.Direction direction) {
        this.direction = direction;
        this.nullValueLast = true;
    }

    @Override
    public int compare(Comparable<Object> o1, Comparable<Object> o2) {
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
                ? castIfNeccessary(o1).compareTo(castIfNeccessary(o2))
                : castIfNeccessary(o2).compareTo(castIfNeccessary(o1));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Comparable<Object> castIfNeccessary(Comparable<Object> o) {
        try {
            return (Comparable) NumberUtils.parseNumber(o.toString(), Long.class);
        } catch (IllegalArgumentException e) {
            return o;
        }
    }
}
