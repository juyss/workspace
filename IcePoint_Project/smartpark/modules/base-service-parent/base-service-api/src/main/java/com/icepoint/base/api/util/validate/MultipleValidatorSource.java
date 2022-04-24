package com.icepoint.base.api.util.validate;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MultipleValidatorSource<P, T> {

    private final PropertyMapper.Source<P> source;

    private final MultipleValidator<T> validator;

    public MultipleValidator<T> state(Function<P, Boolean> stateFunction, String failMessage) {
        source.to(property -> Assert.state(stateFunction.apply(property), failMessage));
        return validator;
    }

    public MultipleValidator<T> state(Function<P, Boolean> stateFunction, Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.state(stateFunction.apply(property), failMessageSupplier));
        return validator;
    }

    public MultipleValidator<T> isTrue(String failMessage) {
        source.to(property -> Assert.isTrue(parseBoolean(property), failMessage));
        return validator;
    }

    public MultipleValidator<T> isTrue(Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.isTrue(parseBoolean(property), failMessageSupplier));
        return validator;
    }

    public MultipleValidator<T> isFalse(String failMessage) {
        source.to(property -> Assert.isTrue(!parseBoolean(property), failMessage));
        return validator;
    }

    public MultipleValidator<T> isFalse(Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.isTrue(!parseBoolean(property), failMessageSupplier));
        return validator;
    }

    private boolean parseBoolean(@Nullable Object property) {
        boolean expression = property != null;
        if (property instanceof Long || property instanceof Integer) {
            return Integer.parseInt(property.toString()) == 1;
        }
        if (property instanceof String) {
            try {
                int parsedProperty = NumberUtils.parseNumber(property.toString(), Integer.class);
                if (parsedProperty == 1 || parsedProperty == 0)
                    return parsedProperty == 1;
            } catch (NumberFormatException ignored) {}
        }
        if (expression)
            expression = BooleanUtils.toBoolean(property.toString());
        return expression;
    }

    public MultipleValidator<T> notNull(String failMessage) {
        source.to(property -> Assert.notNull(property, failMessage));
        return validator;
    }

    public MultipleValidator<T> notNull(Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.notNull(property, failMessageSupplier));
        return validator;
    }

    public MultipleValidator<T> isNull(String failMessage) {
        source.to(property -> Assert.isNull(property, failMessage));
        return validator;
    }

    public MultipleValidator<T> isNull(Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.isNull(property, failMessageSupplier));
        return validator;
    }

    public MultipleValidator<T> hasLength(String failMessage) {
        source.to(property -> Assert.hasLength((String) property, failMessage));
        return validator;
    }

    public MultipleValidator<T> hasLength(Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.hasLength((String) property, failMessageSupplier));
        return validator;
    }

    public MultipleValidator<T> hasText(String failMessage) {
        source.to(property -> Assert.hasText((String) property, failMessage));
        return validator;
    }

    public MultipleValidator<T> hasText(Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.hasText((String) property, failMessageSupplier));
        return validator;
    }

    public MultipleValidator<T> doesNotContain(String substring, String failMessage) {
        source.to(property -> Assert.doesNotContain((String) property, substring, failMessage));
        return validator;
    }

    public MultipleValidator<T> doesNotContain(String substring, Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.doesNotContain((String) property, substring, failMessageSupplier));
        return validator;
    }

    public MultipleValidator<T> contains(String substring, String failMessage) {
        source.to(property -> Assert.isTrue(containsStr((String) property, substring), failMessage));
        return validator;
    }

    public MultipleValidator<T> contains(String substring, Supplier<String> failMessageSupplier) {
        source.to(property -> Assert.isTrue(containsStr((String) property, substring), failMessageSupplier));
        return validator;
    }

    private boolean containsStr(String textToSearch, String substring) {
        Assert.notNull(substring, "substring must not be null");
        return !StringUtils.hasLength(textToSearch) && !textToSearch.contains(substring);
    }

    public MultipleValidator<T> notEmpty(String failMessage) {
        notEmptyAdapt(() -> failMessage);
        return validator;
    }

    public MultipleValidator<T> notEmpty(Supplier<String> failMessageSupplier) {
        notEmptyAdapt(failMessageSupplier);
        return validator;
    }

    private void notEmptyAdapt(Supplier<String> failMessageSupplier) {
        source.to(property -> {
            if (property instanceof Collection) {
                Assert.notEmpty((Collection<?>) property, failMessageSupplier);
            } else if (property instanceof Map) {
                Assert.notEmpty((Map<?, ?>) property, failMessageSupplier);
            } else if (property instanceof Object[]) {
                Assert.notEmpty((Object[]) property, failMessageSupplier);
            }
        });
    }

    public MultipleValidator<T> noNullElements(String failMessage) {
        noNullElementsAdapt(() -> failMessage);
        return validator;
    }

    public MultipleValidator<T> noNullElements(Supplier<String> failMessageSupplier) {
        noNullElementsAdapt(failMessageSupplier);
        return validator;
    }

    private void noNullElementsAdapt(Supplier<String> failMessageSupplier) {
        source.to(property -> {
            if (property instanceof Collection) {
                Assert.noNullElements((Collection<?>) property, failMessageSupplier);
            } else if (property instanceof Map) {
                Assert.notNull(property, failMessageSupplier);
                Assert.noNullElements(((Map<?, ?>) property).values(), failMessageSupplier);
            } else if (property instanceof Object[]) {
                Assert.noNullElements((Object[]) property, failMessageSupplier);
            } else {
                throw new IllegalArgumentException(nullSafeGet(failMessageSupplier));
            }
        });
    }

    @Nullable
    private String nullSafeGet(@Nullable Supplier<String> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }
}
