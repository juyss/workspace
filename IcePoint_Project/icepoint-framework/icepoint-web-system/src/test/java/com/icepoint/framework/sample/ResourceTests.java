package com.icepoint.framework.sample;

import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ViewType;
import com.icepoint.framework.web.system.resource.builder.DefaultLookupBuilder;
import com.icepoint.framework.web.system.resource.query.Parameter;
import com.icepoint.framework.web.system.resource.query.Query;
import com.icepoint.framework.web.system.resource.query.ConditionCollectingVisitor;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class ResourceTests {

    void testBuidler() {

        Lookup lookup = DefaultLookupBuilder.of(null)
                .viewType(ViewType.SIMPLE)
                .query(q -> q
                        .andEq("test", 1)
                        .andGt("number", 3))
                .build();

        Query query = lookup.getQuery();

        ConditionCollectingVisitor visitor = new ConditionCollectingVisitor();
        query.accept(visitor);

        List<Parameter> parameters = visitor.getParameters();
    }
}
