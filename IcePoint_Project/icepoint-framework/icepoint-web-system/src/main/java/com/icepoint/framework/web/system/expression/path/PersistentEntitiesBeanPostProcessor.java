package com.icepoint.framework.web.system.expression.path;

import com.icepoint.framework.core.support.ApplicationContextAwareBeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.util.ProxyUtils;
import org.springframework.data.util.Streamable;
import org.springframework.data.util.TypeInformation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * @author Jiawei Zhao
 */
@Component
public class PersistentEntitiesBeanPostProcessor extends ApplicationContextAwareBeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (!(bean instanceof PersistentEntities)) {
            return bean;
        }

        return new DelegatingPersistentEntities(Collections.emptyList(), (PersistentEntities) bean);
    }

    private static class DelegatingPersistentEntities extends PersistentEntities {

        private final PersistentEntities delegate;

        public DelegatingPersistentEntities(Iterable<? extends MappingContext<?, ?>> contexts,
                PersistentEntities delegate) {

            super(contexts);
            this.delegate = delegate;
        }

        @Override
        public Optional<PersistentEntity<?, ? extends PersistentProperty<?>>> getPersistentEntity(Class<?> type) {
            return this.delegate.getPersistentEntity(type);
        }

        @Override
        public PersistentEntity<?, ? extends PersistentProperty<?>> getRequiredPersistentEntity(Class<?> type) {
            return this.delegate.getRequiredPersistentEntity(ProxyUtils.getUserClass(type));
        }

        @Override
        public <T> Optional<T> mapOnContext(Class<?> type,
                BiFunction<MappingContext<?, ? extends PersistentProperty<?>>, PersistentEntity<?, ?>, T> combiner) {
            return this.delegate.mapOnContext(type, combiner);
        }

        @Override
        public Streamable<TypeInformation<?>> getManagedTypes() {
            return this.delegate.getManagedTypes();
        }

        @Override
        public Iterator<PersistentEntity<?, ? extends PersistentProperty<?>>> iterator() {
            return this.delegate.iterator();
        }

        @Nullable
        @Override
        public PersistentEntity<?, ?> getEntityUltimatelyReferredToBy(PersistentProperty<?> property) {
            return this.delegate.getEntityUltimatelyReferredToBy(property);
        }

        @Override
        public TypeInformation<?> getTypeUltimatelyReferredToBy(PersistentProperty<?> property) {
            return this.delegate.getTypeUltimatelyReferredToBy(property);
        }

        @Override
        public Stream<PersistentEntity<?, ? extends PersistentProperty<?>>> stream() {
            return this.delegate.stream();
        }

        @Override
        public <R> Streamable<R> map(
                Function<? super PersistentEntity<?, ? extends PersistentProperty<?>>, ? extends R> mapper) {
            return this.delegate.map(mapper);
        }

        @Override
        public <R> Streamable<R> flatMap(
                Function<? super PersistentEntity<?, ? extends PersistentProperty<?>>, ? extends Stream<? extends R>> mapper) {
            return this.delegate.flatMap(mapper);
        }

        @Override
        public Streamable<PersistentEntity<?, ? extends PersistentProperty<?>>> filter(
                Predicate<? super PersistentEntity<?, ? extends PersistentProperty<?>>> predicate) {
            return this.delegate.filter(predicate);
        }

        @Override
        public boolean isEmpty() {
            return this.delegate.isEmpty();
        }

        @Override
        public Streamable<PersistentEntity<?, ? extends PersistentProperty<?>>> and(
                Supplier<? extends Stream<? extends PersistentEntity<?, ? extends PersistentProperty<?>>>> stream) {
            return this.delegate.and(stream);
        }

        @Override
        public Streamable<PersistentEntity<?, ? extends PersistentProperty<?>>> and(
                PersistentEntity<?, ? extends PersistentProperty<?>>... others) {
            return this.delegate.and(others);
        }

        @Override
        public Streamable<PersistentEntity<?, ? extends PersistentProperty<?>>> and(
                Iterable<? extends PersistentEntity<?, ? extends PersistentProperty<?>>> iterable) {
            return this.delegate.and(iterable);
        }

        @Override
        public Streamable<PersistentEntity<?, ? extends PersistentProperty<?>>> and(
                Streamable<? extends PersistentEntity<?, ? extends PersistentProperty<?>>> streamable) {
            return this.delegate.and(streamable);
        }

        @Override
        public List<PersistentEntity<?, ? extends PersistentProperty<?>>> toList() {
            return this.delegate.toList();
        }

        @Override
        public Set<PersistentEntity<?, ? extends PersistentProperty<?>>> toSet() {
            return this.delegate.toSet();
        }

        @Override
        public Stream<PersistentEntity<?, ? extends PersistentProperty<?>>> get() {
            return this.delegate.get();
        }

        @Override
        public void forEach(Consumer<? super PersistentEntity<?, ? extends PersistentProperty<?>>> action) {
            this.delegate.forEach(action);
        }

        @Override
        public Spliterator<PersistentEntity<?, ? extends PersistentProperty<?>>> spliterator() {
            return this.delegate.spliterator();
        }
    }
}
