package com.icepoint.base.api.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.util.comparator.Comparators;

import java.util.Objects;

/**
 * JSON反序列化的时候，会以value的值作为这个对象的值反序列化，类似基本类型的方式
 *
 * @author Jiawei Zhao
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ValueGenericProperty implements GenericProperty {

    private final String name;

    @Nullable
    @JsonValue
    private Object value;

    @Override
    public int compareTo(GenericProperty o) {
        if (o == this) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        if (value == o.getValue()) {
            return 0;
        }
        return Objects.compare(value, o.getValue(), Comparators.nullsLow());
    }
}
