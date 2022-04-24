package com.icepoint.framework.web.core.support;

import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.data.dao.BaseRepository;
import com.icepoint.framework.data.dao.StdRepository;
import com.icepoint.framework.data.domain.BaseEntity;
import com.icepoint.framework.data.domain.StdEntity;
import com.icepoint.framework.data.util.PersistenceUtils;
import com.icepoint.framework.web.core.util.ServletUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@ControllerAdvice
public class EntityRequestBodyAdvice implements RequestBodyAdvice {

    private final Repositories repositories;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {

        HttpMethod httpMethod = Optionals.firstNonEmpty(
                () -> Optional.of(inputMessage)
                        .filter(HttpRequest.class::isInstance)
                        .map(HttpRequest.class::cast)
                        .map(HttpRequest::getMethod),
                () -> Optional.ofNullable(ServletUtils.getRequest())
                        .map(HttpServletRequest::getMethod)
                        .map(String::toUpperCase)
                        .map(HttpMethod::resolve))
                .orElse(null);

        if (HttpMethod.PATCH != httpMethod) {
            return body;
        }

        if (!PersistenceUtils.isPersistentEntity(body) && body instanceof BaseEntity) {
            return body;
        }

        BaseEntity<?> entity = (BaseEntity<?>) body;
        Serializable id = entity.getId();
        Assert.notNull(id, "修改数据id不能为空");

        return repositories.getRepositoryFor(body.getClass())
                .filter(BaseRepository.class::isInstance)
                .flatMap(repository -> {

                    Optional<?> db;

                    // 根据实体的类型执行不同的查询方法
                    if (repository instanceof StdRepository && body instanceof StdEntity) {
                        db = ((StdRepository<?, ?>) repository).findById(CastUtils.cast(id), false);

                    } else {
                        db = ((BaseRepository<?, ?>) repository).findById(CastUtils.cast(id));
                    }

                    // 对Hibernate的代理进行反代理，以及将修改的数据覆盖到数据库实体进行返回（忽略null值）
                    return db
                            .map(it -> {
                                BeanUtils.copyProperties(body, it, true);
                                return it;
                            });
                })
                .orElse(CastUtils.cast(body)); // 如果找不到Repository或者找不到对应id的数据，返回本来的body
    }

    @Override
    public Object handleEmptyBody(@Nullable Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }

}
