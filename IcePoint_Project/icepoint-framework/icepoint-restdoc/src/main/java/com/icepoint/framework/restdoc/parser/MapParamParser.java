package com.icepoint.framework.restdoc.parser;

import com.icepoint.framework.restdoc.constants.DocGlobalConstants;
import com.icepoint.framework.restdoc.model.ApiParam;
import com.icepoint.framework.restdoc.util.DocClassUtil;
import com.power.common.util.StringUtil;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.expression.TypeRef;
import com.thoughtworks.qdox.model.impl.DefaultJavaAnnotation;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class MapParamParser implements RequestParamParser {

    private final JavaAnnotation mapParam;
    private static final String MAP_PARAM = "MapParam";

    public MapParamParser(JavaAnnotation mapParam) {
        String name = mapParam.getType().getName();
        if (!MAP_PARAM.equals(name)) {
            throw new IllegalStateException(String.format("注解解析类型不匹配, 期望值: %s, 实际值: %s", MAP_PARAM, name));
        }
        this.mapParam = mapParam;
    }

    @Override
    public List<ApiParam> parse() {

        List<DefaultJavaAnnotation> params = (List<DefaultJavaAnnotation>) mapParam.getProperty("params")
                .getParameterValue();

        AtomicInteger size = new AtomicInteger(0);
        return params.stream()
                .map(param -> {

                    String name = StringUtil.removeQuotes((String) param.getProperty("name").getParameterValue());
                    String type = ((TypeRef) param.getProperty("type")).getType().getCanonicalName();
                    String description = (String) param.getProperty("description").getParameterValue();
                    String r = param.getProperty("required") == null ? "true" : (String) param.getProperty("required")
                            .getParameterValue();

                    String realName = name.replace("--", "");
                    String levelSigns = name.replace("--", DocGlobalConstants.FIELD_SPACE).replace(realName, "");
                    String field = levelSigns + (name.startsWith("--") ? "└─" : "") + realName;

                    return ApiParam.of()
                            .setField(field)
                            .setType(DocClassUtil.processTypeNameForParams(type))
                            .setId(size.incrementAndGet())
                            .setPathParam(false)
                            .setQueryParam(false)
                            .setDesc(StringUtil.removeQuotes(description))
                            .setRequired(Boolean.parseBoolean(r))
                            .setVersion(DocGlobalConstants.DEFAULT_VERSION);

                })
                .collect(Collectors.toList());

//        LinkedList<LeveledApiParam> result = new LinkedList<>();
//
//        for (int i = 0; i < apiParams.size(); i++) {
//
//            LeveledApiParam apiParam = apiParams.get(i);
//            int level = apiParam.getLevel();
//
//            if (level == 0) {
//                result.add(apiParam);
//            } else {
//                LeveledApiParam parent = result.getLast();
//                apiParam.setPid(parent.getId());
//                result.add(apiParam);
////                i += setChildren(parent, apiParams.subList(i, apiParams.size()), 1);
//            }
//        }
//
//        return (List) result;

    }

    private int setChildren(LeveledApiParam parent, List<LeveledApiParam> subList, int childrenLevel) {

        int i, subListSize;
        List<LeveledApiParam> children = new LinkedList<>();
        for (i = 0, subListSize = subList.size(); i < subListSize; i++) {
            LeveledApiParam param = subList.get(i);

            int level = param.getLevel();

            if (level > childrenLevel) {
                return i;
            } else if (level == childrenLevel) {
                param.setPid(parent.getId());
                children.add(param);
            } else {
                throw new UnsupportedOperationException();
            }
        }

        if (!children.isEmpty()) {
            parent.setChildren((List) children);
        }
        return i;
    }

    private int getLevel(String name, int parentLevel) {
        if (name.startsWith("--")) {
            return getLevel(name.substring(2), ++parentLevel);
        } else {
            return parentLevel;
        }
    }

    private static class LeveledApiParam extends ApiParam {

        private int level;

        public int getLevel() {
            return level;
        }

        public LeveledApiParam setLevel(int level) {
            this.level = level;
            return this;
        }
    }
}
