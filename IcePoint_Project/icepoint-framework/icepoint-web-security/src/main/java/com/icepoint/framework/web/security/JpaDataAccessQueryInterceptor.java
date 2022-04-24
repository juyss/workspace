package com.icepoint.framework.web.security;

import com.icepoint.framework.data.dao.QueryInterceptor;
import com.icepoint.framework.data.domain.StdEntity;
import com.icepoint.framework.web.security.util.SecurityUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

import static com.icepoint.framework.data.domain.PropertyConstants.APP_ID;
import static com.icepoint.framework.data.domain.PropertyConstants.OWNER_ID;

/**
 * 数据权限访问控制的拦截器
 *
 * @author Jiawei Zhao
 */
@Component
public class JpaDataAccessQueryInterceptor implements QueryInterceptor {

    private static final EntityPathResolver PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    @Nullable
    @Override
    public <T> Specification<T> before(
            EntityInformation<T, ?> entityInformation,
            @Nullable Specification<T> spec,
            @Nullable Pageable pageable,
            @Nullable Sort sort) {

        if (!StdEntity.class.isAssignableFrom(entityInformation.getJavaType())) {
            return spec;
        }

        Long appId = SecurityUtils.getAppId();
        Long ownerId = SecurityUtils.getOwnerId();

        Specification<T> newSpec = (root, query, cb) -> {

            Predicate appIdPred = appId == null
                    ? cb.isNull(root.get(APP_ID))
                    : cb.equal(root.get(APP_ID), appId);

            Predicate ownerIdPred = ownerId == null
                    ? cb.isNull(root.get(OWNER_ID))
                    : cb.equal(root.get(OWNER_ID), ownerId);

            return cb.and(appIdPred, ownerIdPred);
        };

        return spec == null ? newSpec : spec.and(newSpec);
    }

    @Override
    public com.querydsl.core.types.Predicate before(
            EntityInformation<?, ?> entityInformation,
            com.querydsl.core.types.Predicate predicate,
            @Nullable Pageable pageable,
            @Nullable Sort sort,
            @Nullable OrderSpecifier<?>... orders) {

        Class<?> entityType = entityInformation.getJavaType();
        if (!StdEntity.class.isAssignableFrom(entityType)) {
            return predicate;
        }

        Long appId = SecurityUtils.getAppId();
        Long ownerId = SecurityUtils.getOwnerId();

        EntityPath<?> entityPath = PATH_RESOLVER.createPath(entityType);
        NumberPath<Long> appIdPath = Expressions.numberPath(Long.class, entityPath, APP_ID);
        NumberPath<Long> ownerIdPath = Expressions.numberPath(Long.class, entityPath, OWNER_ID);

        return new BooleanBuilder(predicate)
                .and(appId == null ? appIdPath.isNull() : appIdPath.eq(appId))
                .and(ownerId == null ? ownerIdPath.isNull() : ownerIdPath.eq(ownerId));
    }
}
