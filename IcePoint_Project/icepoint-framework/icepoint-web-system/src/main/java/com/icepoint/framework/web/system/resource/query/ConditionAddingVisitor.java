package com.icepoint.framework.web.system.resource.query;

import com.icepoint.framework.core.util.Streams;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 往{@link Query}对象添加查询参数的Visitor
 *
 * @author Jiawei Zhao
 */
public class ConditionAddingVisitor extends QueryVisitorAdapter {

    /**
     * 是否覆盖同名的查询参数
     */
    private boolean overwrite;

    /**
     * 要新增的查询参数列表
     */
    private List<Parameter> parameters;

    public ConditionAddingVisitor() {
        this(new ArrayList<>());
    }

    public ConditionAddingVisitor(List<Parameter> parameters) {
        this(parameters, false);
    }

    public ConditionAddingVisitor(List<Parameter> parameters, boolean overwrite) {
        this.overwrite = overwrite;
        this.parameters = parameters;
    }

    @Override
    protected void visit(DefaultQuery defaultQuery) {

        if (parameters.isEmpty()) {
            return;
        }

        List<Parameter> oldParameters = defaultQuery.getParameters();
        if (CollectionUtils.isEmpty(oldParameters)) {
            parameters.forEach(defaultQuery::addParameter);
        } else {

            List<Parameter> newParameters = new ArrayList<>(oldParameters);

            for (Parameter parameter : parameters) {

                Parameter duplicate = Streams.stream(oldParameters)
                        .filter(oldParameter -> oldParameter.getName().equals(parameter.getName()))
                        .findAny()
                        .orElse(null);

                if (duplicate != null) {
                    if (overwrite) {
                        newParameters.remove(duplicate);
                    } else {
                        continue;
                    }
                }

                newParameters.add(parameter);
            }

            defaultQuery.setParameters(newParameters);
        }
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(Parameter parameter) {
        Assert.notNull(parameter, "Parameter不能为空");
        this.parameters.add(parameter);
    }

    public void addParameters(Iterable<Parameter> parameters) {
        Assert.notNull(parameters, "parameters不能为空");
        parameters.forEach(this::addParameter);
    }
}
