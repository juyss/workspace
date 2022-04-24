package com.icepoint.framework.web.system.resource.builder;

import com.icepoint.framework.web.system.resource.DefaultLookup;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModelMetadata;
import com.icepoint.framework.web.system.resource.ViewType;
import com.icepoint.framework.web.system.resource.query.Query;

import java.util.function.Consumer;

/**
 * @author Jiawei Zhao
 */
public class DefaultLookupBuilder implements LookupBuilder {

    private final ResourceModelMetadata metadata;

    private ViewType viewType;

    private Query query;

    private DefaultLookupBuilder(ResourceModelMetadata metadata) {
        this.metadata = metadata;
    }

    public static DefaultLookupBuilder of(ResourceModelMetadata metadata) {
        return new DefaultLookupBuilder(metadata);
    }

    public static DefaultLookupBuilder of(ResourceModelMetadata metadata, ViewType viewType) {
        return of(metadata).viewType(viewType);
    }

    public DefaultLookupBuilder viewType(ViewType viewType) {
        this.viewType = viewType;
        return this;
    }

    public DefaultLookupBuilder query(Query query) {
        this.query = query;
        return this;
    }

    public DefaultLookupBuilder query(Consumer<DefaultQueryBuilder> consumer) {
        DefaultQueryBuilder builder = DefaultQueryBuilder.of();
        consumer.accept(builder);
        this.query = builder.build();
        return this;
    }

    @Override
    public Lookup build() {
        return new DefaultLookup(
                metadata,
                viewType == null ? ViewType.NO_VIEW : viewType,
                query == null ? Query.EMPTY : query
        );
    }
}
