package com.icepoint.framework.web.core.response;

import com.icepoint.framework.core.io.Serializers;
import com.icepoint.framework.core.util.ApplicationContextUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.web.core.message.MessageContext;
import com.icepoint.framework.web.core.util.CoreMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.util.ProxyUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class ResponseUtils {

    private static final String SUCCESS = CoreMessage.OK.getCode();

    private static final ResponseProcessor<?> NULL_PROCESSOR = ResponseHandlers.NULL;

    private static final ResponseProcessor<?> EMPTY_PROCESSOR = ResponseHandlers.EMPTY;

    private ResponseUtils() {
        throw new UnsupportedOperationException();
    }

    public static Response<Void> operate(boolean result) {
        return operate(result, null);
    }

    public static Response<Void> operate(boolean result, @Nullable String failCode) {
        Response<Void> response = ResponseBuilder.<Void>any(null)
                .code(SUCCESS)
                .build();

        if (!result) {
            failCode = StringUtils.hasText(failCode) ? failCode : CoreMessage.OPERATE_FAILED.getCode();
            response.setCode(failCode);
        }

        return response;
    }

    public static <T> Response<T> any(@Nullable T data) {
        return ResponseBuilder.any(data)
                .code(SUCCESS)
                .build();
    }

    public static <T> Response<T> one(@Nullable T data) {
        Response<T> response = ResponseBuilder.any(data)
                .code(SUCCESS)
                .build();

        if (data == null) {
            response = processAndCast(NULL_PROCESSOR, response);
        }

        return response;
    }

    public static <T> CollectionResponse<T> many(@Nullable Collection<T> collection) {
        CollectionResponse<T> response = ResponseBuilder.collection(collection)
                .code(SUCCESS)
                .build();

        if (CollectionUtils.isEmpty(collection)) {
            response = processAndCast(EMPTY_PROCESSOR, response);
        }

        return response;
    }

    public static <K, V> MapResponse<K, V> map(@Nullable Map<K, V> map) {
        MapResponse<K, V> response = ResponseBuilder.map(map)
                .code(SUCCESS)
                .build();

        if (CollectionUtils.isEmpty(map)) {
            response = processAndCast(EMPTY_PROCESSOR, response);
        }

        return response;
    }

    public static <T> PageResponse<T> page(@Nullable Page<T> page) {
        PageResponse<T> response = ResponseBuilder.page(page)
                .code(SUCCESS)
                .build();

        if (page == null || page.isEmpty()) {
            response = processAndCast(EMPTY_PROCESSOR, response);
        }

        return response;
    }

    public static void writeJson(Object data, HttpServletResponse response) throws IOException {
        writeJson(one(data), response);
    }

    public static void writeJson(Response<?> r, HttpServletResponse response) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ServletOutputStream outputStream = response.getOutputStream();

        MessageContext context = ApplicationContextUtils.getRequiredBean(MessageContext.class);

        String code = r.getCode();
        if (StringUtils.hasText(code) && !StringUtils.hasText(r.getMessage())) {
            String message = context.getMessage(code);
            r.setMessage(message);
        }

        outputStream.write(Serializers.json().serializeAsString(r).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    /**
     * 是否集合型的{@link Response}
     *
     * @param response 要检查的Response
     * @return 是否集合型
     */
    public static boolean isCollectionLikeResponse(Response<?> response) {
        return response instanceof CollectionResponse || response.getData() instanceof Collection;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <E> E unwrapContent(@Nullable Object data) {

        if (!(data instanceof RepresentationModel)) {
            return CastUtils.cast(data);
        }

        if (data instanceof CollectionModel) {

            Collection<E> collection = ((CollectionModel<E>) data).getContent();

            if (!CollectionUtils.isEmpty(collection)) {

                List<E> list = (List<E>) collection.stream()
                        .map(ResponseUtils::unwrapContent)
                        .collect(Collectors.toList());

                collection.clear();
                collection.addAll(list);
            }

            return CastUtils.cast(collection);

        } else if (data instanceof EntityModel) {

            return unwrapContent(((EntityModel<E>) data).getContent());

        } else {
            return CastUtils.cast(data);
        }

    }

    @SuppressWarnings({ "unchecked", "ConstantConditions" })
    private static <R extends Response<T>, T> R processAndCast(ResponseProcessor<?> processor, R response) {
        return (R) processor.process(response, null, null);
    }

    @Nullable
    public static Class<?> getContentBeanType(Object data) {

        Class<?> clazz = data.getClass();
        Object unwrapped = null;

        if (clazz.isArray()) {

            Object[] array = (Object[]) data;
            if (array.length > 0) {
                unwrapped = unwrapContent(array[0]);
            }

        } else if (Collection.class.isAssignableFrom(clazz)) {

            Iterator<?> iterator = ((Collection<?>) data).iterator();
            if (iterator.hasNext()) {
                unwrapped = unwrapContent(iterator.next());
            }

        } else if (Map.class.isAssignableFrom(clazz)) {

            Iterator<?> iterator = ((Map<?, ?>) data).values().iterator();
            if (iterator.hasNext()) {
                unwrapped = unwrapContent(iterator.next());
            }

        } else {

            unwrapped = unwrapContent(data);
        }

        return unwrapped == null ? null : ProxyUtils.getUserClass(unwrapped);
    }
}
