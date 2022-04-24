package com.icepoint.base.api.util.validate;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MultipleValidator<T>  {

    private final T target;

    public <P> MultipleValidatorSource<P, T> property(Function<T, P> getter) {
        return new MultipleValidatorSource<>(PropertyValidator.MAPPER.from(() -> getter.apply(target)), this);
    }

    public <E> MultipleValidator<E> next(E target) {
        return new MultipleValidator<>(target);
    }
}
