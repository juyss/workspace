package com.icepoint.framework.web.system.rest;

import com.icepoint.framework.core.util.FieldUtils;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import com.icepoint.framework.web.core.util.ServletUtils;
import com.icepoint.framework.web.system.entity.TableService;
import com.icepoint.framework.web.system.util.EntityUrlUtils;
import org.apache.catalina.connector.Request;
import org.apache.tomcat.util.buf.MessageBytes;
import org.jetbrains.annotations.Contract;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.rest.core.Path;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ResourceMapping;
import org.springframework.data.rest.core.mapping.ResourceMappings;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.data.rest.webmvc.BaseUri;
import org.springframework.data.rest.webmvc.RepositoryRestHandlerMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.ServletRequestPathUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class MultiplyPathsRepositoryRestHandlerMapping extends RequestMappingHandlerMapping {

    public static final String MULTIPLY_PATH = MultiplyPathsRepositoryRestHandlerMapping.class + ".MULTIPLY_PATH";

    private final RepositoryRestHandlerMapping delegate;

    private final RepositoryRestConfiguration configuration;

    private final Object mappingRegistry;

    private final HandlerMethod preflightAmbiguousMatch;

    private final ResourceMappings mappings;

    private final Set<Path> multiplyPaths = new HashSet<>();

    public MultiplyPathsRepositoryRestHandlerMapping(
            RepositoryRestHandlerMapping delegate) {

        this.delegate = delegate;
        this.configuration = getDelegateField("configuration");
        this.mappingRegistry = getDelegateField("mappingRegistry");
        this.preflightAmbiguousMatch = getDelegateField("PREFLIGHT_AMBIGUOUS_MATCH");
        this.mappings = getDelegateField("mappings");

        handleMultiplyPath(mappings);
    }

    private void handleMultiplyPath(ResourceMappings mappings) {
        Map<Class<?>, ResourceMetadata> cache = getTargetField(mappings, "cache");

        cache.forEach((entityType, metadata) -> {

            ResourceMapping mapping = getTargetField(metadata, "mapping");
            RepositoryInformation info = getTargetField(mapping, "metadata");

            Optional<MultiplyPathRepositoryRestResource> annotation = Optional.ofNullable(AnnotationUtils.findAnnotation(
                    info.getRepositoryInterface(),
                    MultiplyPathRepositoryRestResource.class));

            if (!annotation.isPresent()) {
                return;
            }

            String path = annotation.get().path();
            if (!StringUtils.hasText(path) || !path.contains("/")) {
                return;
            }

            if (EntityUrlUtils.getUrl(info.getDomainType()) == null) {
                Path newPath = new Path(path);
                FieldUtils.setField(mapping, "path", newPath);

                multiplyPaths.add(newPath);
            }
        });
    }

    @Nullable
    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) { // NOSONAR

        HandlerMethod handlerMethod = null;

        // BasePathAwareHandlerMapping

        List<MediaType> mediaTypes = new ArrayList<>();
        boolean defaultFound = false;

        for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader(HttpHeaders.ACCEPT))) {

            MediaType rawtype = mediaType.removeQualityValue();

            if (rawtype.equals(configuration.getDefaultMediaType())) {
                defaultFound = true;
            }

            if (!rawtype.equals(MediaType.ALL)) {
                mediaTypes.add(mediaType);
            }
        }

        if (!defaultFound) {
            mediaTypes.add(configuration.getDefaultMediaType());
        }

        request = new CustomAcceptHeaderHttpServletRequest(request, mediaTypes);

        // AbstractHandlerMethodMapping

        List<Match> matches = new ArrayList<>();
        List<RequestMappingInfo> directPathMatches = invokeTargetMethod(mappingRegistry,
                "getMappingsByDirectPath", new ParameterPairs(
                        new Class[]{ String.class },
                        new Object[]{ lookupPath }), true);

        if (directPathMatches != null) {

            invokeDelegateMethod("addMatchingMappings", new ParameterPairs(
                    new Class[]{ Collection.class, List.class, HttpServletRequest.class },
                    new Object[]{ directPathMatches, matches, request }), false);

        }

        Map<RequestMappingInfo, Object> registrations = invokeTargetMethod(mappingRegistry, "getRegistrations",
                new ParameterPairs(new Class[]{}, new Object[]{}), false);


        /* 改造点 start ============================================= */

        /*
         * Spring Rest Data的Controller的路径是: /{repository}/...
         * 而现在的请求路径是: /{modules}.../{repository}/...
         * 这里改造目的就是要找到正确的HandlerMethod
         *
         * 改造的方式是通过RequestModifier来实现，实际上是通过反射来修改底层对象的属性来实现
         *
         * 要注意的是所有标注@RepositoryRestController注解的Controller都会被改造，非必要不要使用这个注解
         */

        RequestModifier requestModifier = null;
        String repositoryPath = null;
        if (matches.isEmpty()) {

            // 改造前源码: AbstractHandlerMethodMapping#addMatchingMappings(this.mappingRegistry.getRegistrations().keySet(), matches, request)

            String servletPath = request.getServletPath();
            TableService service = EntityUrlUtils.getTableService(request.getServletPath());

            String newRequestURI = null;
            if (service != null) {

                // TODO: 因为每次请求都会查询，需要考虑优化查询流程
                repositoryPath = EntityUrlUtils.getUrlByTableServiceId(service.getId());
                Assert.hasText(repositoryPath, "实体url查找失败");

                request.setAttribute(MULTIPLY_PATH, repositoryPath);
                String serviceName = service.getName();
                newRequestURI = servletPath.replace(repositoryPath, "/" + serviceName);

            } else if ((repositoryPath = getMultiplyPath(servletPath)) != null) {

                request.setAttribute(MULTIPLY_PATH, repositoryPath);
                String[] paths = repositoryPath.split("/");
                newRequestURI = "/" + paths[paths.length - 1];
            }

            if (newRequestURI != null) {
                requestModifier = new RequestModifier(request);
                requestModifier.replace(newRequestURI);
                ServletRequestPathUtils.parseAndCache(request);
            }

            for (Map.Entry<RequestMappingInfo, Object> entry : registrations.entrySet()) {

                RequestMappingInfo mapping = entry.getKey();
                Object registration = entry.getValue();

                RequestMappingInfo match = mapping.getMatchingCondition(request);
                if (match != null) {
                    matches.add(new Match(match, registration));
                }

            }
        }

        /* 改造点 end ============================================= */

        if (!matches.isEmpty()) {
            Match bestMatch = matches.get(0);
            if (matches.size() > 1) {
                Comparator<Match> comparator = new MatchComparator(getMappingComparator(request));
                matches.sort(comparator);
                bestMatch = matches.get(0);
                if (logger.isTraceEnabled()) {
                    logger.trace(matches.size() + " matching mappings: " + matches);
                }
                if (CorsUtils.isPreFlightRequest(request)) {
                    for (Match match : matches) {
                        if (match.hasCorsConfig()) {
                            handlerMethod = preflightAmbiguousMatch;
                        }
                    }
                } else {
                    Match secondBestMatch = matches.get(1);
                    if (comparator.compare(bestMatch, secondBestMatch) == 0) {
                        Method m1 = bestMatch.getHandlerMethod().getMethod();
                        Method m2 = secondBestMatch.getHandlerMethod().getMethod();
                        String uri = request.getRequestURI();
                        throw new IllegalStateException(
                                "Ambiguous handler methods mapped for '" + uri + "': {" + m1 + ", " + m2 + "}");
                    }
                }
            }

            if (handlerMethod != preflightAmbiguousMatch) {

                request.setAttribute(BEST_MATCHING_HANDLER_ATTRIBUTE, bestMatch.getHandlerMethod());
                handleMatch(bestMatch.mapping, lookupPath, request);

                handlerMethod = bestMatch.getHandlerMethod();
            }

        } else {
            handlerMethod = handleNoMatch(registrations.keySet(), lookupPath, request);
        }

        // RepositoryRestHandlerMapping

        if (handlerMethod == null) {
            return processBeforeReturn(request, null, requestModifier);
        }

        /* 改造点 start ============================================= */

        String repositoryLookupPath = StringUtils.hasText(repositoryPath)
                ? repositoryPath
                : new BaseUri(configuration.getBasePath()).getRepositoryLookupPath(lookupPath);

        // Repository root resource
        if (!StringUtils.hasText(repositoryLookupPath)) {
            return processBeforeReturn(request, handlerMethod, requestModifier);
        }

        String repositoryBasePath = getRepositoryBasePath(repositoryLookupPath);

        if (!mappings.exportsTopLevelResourceFor(StringUtils.hasText(repositoryPath) ? repositoryPath : repositoryBasePath)) {
            return processBeforeReturn(request, null, requestModifier);
        }

        invokeDelegateMethod("exposeEffectiveLookupPathKey", new ParameterPairs(
                new Class[]{ HandlerMethod.class, HttpServletRequest.class, String.class },
                new Object[]{ handlerMethod, request, repositoryBasePath }), true);

        /* 改造点 end ============================================= */

        return processBeforeReturn(request, handlerMethod, requestModifier);
    }

    @Nullable
    private HandlerMethod processBeforeReturn(
            HttpServletRequest request,
            @Nullable HandlerMethod handlerMethod,
            @Nullable RequestModifier modifier) {

        // 如果改变过Request，这里做一下还原，避免出现其他bug
        if (modifier != null && modifier.modified() && handlerMethod == null) {
            modifier.reset();
            ServletRequestPathUtils.parseAndCache(request);
        }

        return handlerMethod;
    }

    @Nullable
    private String getMultiplyPath(String servletPath) {
        return multiplyPaths.stream()
                .map(Path::toString)
                .filter(servletPath::startsWith)
                .findAny()
                .orElse(null);
    }

    public static String getRepositoryBasePath(String repositoryLookupPath) {

        int secondSlashIndex = repositoryLookupPath.indexOf('/', repositoryLookupPath.startsWith("/") ? 1 : 0);
        return secondSlashIndex == -1 ? repositoryLookupPath : repositoryLookupPath.substring(0, secondSlashIndex);
    }

    /**
     * {@link RepositoryRestHandlerMapping#setEmbeddedValueResolver}
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        delegate.setEmbeddedValueResolver(resolver);
    }

    /**
     * {@link RepositoryRestHandlerMapping}
     */
    @Nullable // NOSONAR
    @Override
    protected HandlerMethod handleNoMatch(
            Set<RequestMappingInfo> infos, String lookupPath,
            HttpServletRequest request) {

        return invokeDelegateMethod("handleNoMatch", new ParameterPairs(
                new Class[]{ Set.class, String.class, HttpServletRequest.class },
                new Object[]{ infos, lookupPath, request }), true);
    }

    /**
     * {@link RepositoryRestHandlerMapping}
     */
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return invokeDelegateMethod("isHandler", new ParameterPairs(
                new Class[]{ Class.class },
                new Object[]{ beanType }), false);
    }

    /**
     * {@link RepositoryRestHandlerMapping}
     */
    @Override
    protected void extendInterceptors(List<Object> interceptors) {
        invokeDelegateMethod("extendInterceptors", new ParameterPairs(
                new Class[]{ List.class },
                new Object[]{ interceptors }), true);
    }

    /**
     * {@link RepositoryRestHandlerMapping}
     */
    @Nullable // NOSONAR
    @Override
    protected CorsConfiguration getCorsConfiguration(Object handler, HttpServletRequest request) {
        return invokeDelegateMethod("getCorsConfiguration", new ParameterPairs(
                new Class[]{ Object.class, HttpServletRequest.class },
                new Object[]{ handler, request }), true);
    }

    /**
     * {@link org.springframework.data.rest.webmvc.BasePathAwareHandlerMapping}
     */
    @Override
    protected boolean hasCorsConfigurationSource(Object handler) {
        return invokeDelegateMethod("hasCorsConfigurationSource", new ParameterPairs(
                new Class[]{ Object.class },
                new Object[]{ handler }), false);
    }

    /**
     * {@link org.springframework.data.rest.webmvc.BasePathAwareHandlerMapping}
     */
    @Nullable
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        return invokeDelegateMethod("getMappingForMethod", new ParameterPairs(
                new Class[]{ Method.class, Class.class },
                new Object[]{ method, handlerType }), true);
    }

    @Contract("_, _, false -> !null")
    @Nullable
    private <T> T invokeDelegateMethod(String methodName, ParameterPairs parameterPairs, boolean nullable) {

        return invokeTargetMethod(delegate, methodName, parameterPairs, nullable);
    }

    @SuppressWarnings("unchecked")
    @Contract("_, _, _, false -> !null")
    @Nullable
    private <T> T invokeTargetMethod(Object target, String methodName, ParameterPairs parameterPairs,
            boolean nullable) {

        Method method = ReflectionUtils.findMethod(target.getClass(), methodName, parameterPairs.getParameterTypes());
        Assert.notNull(method, "找不到方法: " + methodName);

        ReflectionUtils.makeAccessible(method);
        T result = (T) ReflectionUtils.invokeMethod(method, target, parameterPairs.getArguments());

        if (!nullable) {
            Assert.notNull(result, methodName + " 返回不能为空");
        }

        return result;
    }

    private <T> T getDelegateField(String fieldName) {
        return getTargetField(delegate, fieldName);
    }

    @SuppressWarnings("unchecked")
    private <T> T getTargetField(Object target, String fieldName) {

        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        Assert.notNull(field, "找不到属性: " + fieldName);

        ReflectionUtils.makeAccessible(field);
        Object result = ReflectionUtils.getField(field, target);

        Assert.notNull(result, fieldName + " 不能为空");

        return (T) result;
    }

    static class ParameterPairs {

        private final Class<?>[] parameterTypes;

        private final Object[] arguments;

        public ParameterPairs(Class<?>[] parameterTypes, Object[] arguments) {
            this.parameterTypes = parameterTypes;
            this.arguments = arguments;
        }

        public Class<?>[] getParameterTypes() {
            return parameterTypes;
        }

        public Object[] getArguments() {
            return arguments;
        }
    }

    private static class RequestModifier {

        private final MessageBytes path;
        private final MessageBytes uri;

        private final String originPath;
        private final String originUri;

        public RequestModifier(HttpServletRequest request) {

            Request req = ServletUtils.unwrapRequiredRequestForType(request, Request.class);

            MessageBytes wrapperPath = FieldUtils.getField(req, "mappingData.wrapperPath");
            MessageBytes uriMB = FieldUtils.getField(req, "coyoteRequest.uriMB");

            assert wrapperPath != null;
            assert uriMB != null;

            this.path = wrapperPath;
            this.uri = uriMB;

            this.originPath = wrapperPath.getString();
            this.originUri = uriMB.getString();
        }

        public void replace(String newPath) {
            path.setString(newPath);
            uri.setString(newPath);
        }

        public void reset() {
            path.setString(originPath);
            uri.setString(originUri);
        }

        public boolean modified() {
            return !path.getString().equals(originPath) || !uri.getString().equals(originUri);
        }
    }

    class Match {

        private final RequestMappingInfo mapping;

        private final Object registration;

        public Match(RequestMappingInfo mapping, Object registration) {
            this.mapping = mapping;
            this.registration = registration;
        }

        public HandlerMethod getHandlerMethod() {
            return invokeTargetMethod(this.registration, "getHandlerMethod",
                    new ParameterPairs(new Class[]{}, new Object[]{}), false);
        }

        public boolean hasCorsConfig() {
            return invokeTargetMethod(this.registration, "hasCorsConfig",
                    new ParameterPairs(new Class[]{}, new Object[]{}), false);
        }

        @Override
        public String toString() {
            return this.mapping.toString();
        }
    }


    private static class MatchComparator implements Comparator<Match> {

        private final Comparator<RequestMappingInfo> comparator;

        public MatchComparator(Comparator<RequestMappingInfo> comparator) {
            this.comparator = comparator;
        }

        @Override
        public int compare(Match match1, Match match2) {
            return this.comparator.compare(match1.mapping, match2.mapping);
        }
    }

    static class CustomAcceptHeaderHttpServletRequest extends HttpServletRequestWrapper {

        private final List<MediaType> acceptMediaTypes;
        private final List<String> acceptMediaTypeStrings;

        /**
         * Creates a new {@link CustomAcceptHeaderHttpServletRequest} for the given delegate {@link HttpServletRequest} and
         * the list of {@link MediaType}.
         *
         * @param request          must not be {@literal null}.
         * @param acceptMediaTypes must not be {@literal null} or empty.
         */
        public CustomAcceptHeaderHttpServletRequest(HttpServletRequest request, List<MediaType> acceptMediaTypes) {

            super(request);

            Assert.notEmpty(acceptMediaTypes, "MediaTypes must not be empty!");

            this.acceptMediaTypes = acceptMediaTypes;

            this.acceptMediaTypeStrings = acceptMediaTypes.stream()
                    .map(MediaType::toString)
                    .collect(Collectors.toList());
        }

        /*
         * (non-Javadoc)
         * @see javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
         */
        @Override
        public String getHeader(String name) {

            return HttpHeaders.ACCEPT.equalsIgnoreCase(name) && acceptMediaTypes != null //
                    ? StringUtils.collectionToCommaDelimitedString(acceptMediaTypes) //
                    : super.getHeader(name);
        }

        /*
         * (non-Javadoc)
         * @see javax.servlet.http.HttpServletRequestWrapper#getHeaders(java.lang.String)
         */
        @Override
        public Enumeration<String> getHeaders(String name) {

            return HttpHeaders.ACCEPT.equalsIgnoreCase(name) && acceptMediaTypes != null //
                    ? Collections.enumeration(acceptMediaTypeStrings) //
                    : super.getHeaders(name);
        }
    }
}
