package com.icepoint.framework.web.core.response.hateoas;

import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.util.CoreMessage;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.util.*;

/**
 * @author Jiawei Zhao
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class HateoasResponseConvertAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Override
    protected void beforeBodyWriteInternal(
            MappingJacksonValue bodyContainer, MediaType contentType,
            MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

        Object value = bodyContainer.getValue();

        if (!(value instanceof RepresentationModel))
            return;

        Response<?> res;
        if (value instanceof PagedModel) {

            PagedModel<?> pagedModel = (PagedModel<?>) value;

            PagedModel.PageMetadata metadata = pagedModel.getMetadata();
            Links links = pagedModel.getLinks();
            Collection<?> content = unwrapCollection(pagedModel.getContent());

            Assert.notNull(metadata, "Hateoas返回值转换失败, 缺少PageMetadata");
            res = new HateoasPageResponse<>(content, links, metadata);

        } else if (value instanceof CollectionModel) {

            CollectionModel<?> model = (CollectionModel<?>) value;

            Links links = model.getLinks();
            Collection<?> content = unwrapCollection(model.getContent());

            res = new HateoasCollectionResponse<>(content, links);

        } else if (value instanceof EntityModel) {

            EntityModel<?> model = (EntityModel<?>) value;
            Links links = model.getLinks();

            res = new HateoasEntityResponse<>(model, links);

        } else if (value instanceof RepositoryLinksResource){

            RepositoryLinksResource resource = (RepositoryLinksResource) value;

            Links links = resource.getLinks();

            res = new HateoasDefaultResponse<>(null, links);

        } else {

            RepresentationModel<?> model = (RepresentationModel<?>) value;

            res = new HateoasDefaultResponse<>(null, model.getLinks());
        }

        res.setCode(CoreMessage.OK.getCode());
        bodyContainer.setValue(res);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return super.supports(returnType, converterType)
                && TypeConstrainedMappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    private Collection<?> unwrapCollection(Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection))
            return collection;

        List<Object> list = new ArrayList<>();
        collection.stream()
                .filter(Objects::nonNull)
                .map(this::unwrapObject)
                // 查询结果为空的情况的话，内部元素是是一个空集合，这里过滤一下
                .filter(it -> !(it instanceof Collection) || !((Collection<?>) it).isEmpty())
                .forEach(list::add);

        return Collections.unmodifiableCollection(list);
    }

    @Nullable
    private Object unwrapObject(@Nullable Object object) {
        Object result = object;
        if (result instanceof EmbeddedWrapper) {
            EmbeddedWrapper wrapper = (EmbeddedWrapper) result;
            result = wrapper.getValue();
        }

        return result;
    }
}
