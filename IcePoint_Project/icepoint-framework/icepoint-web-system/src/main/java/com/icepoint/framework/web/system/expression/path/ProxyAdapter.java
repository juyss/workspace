package com.icepoint.framework.web.system.expression.path;

import com.icepoint.framework.web.system.expression.node.EntityPath;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public interface ProxyAdapter {

    Object proxy(Class<?> rootType, Object obj, List<EntityPath> paths, @Nullable PropertyPath propertyPath,
            @Nullable PersistentEntityResourceAssembler assembler, PersistentEntities entities);
}
