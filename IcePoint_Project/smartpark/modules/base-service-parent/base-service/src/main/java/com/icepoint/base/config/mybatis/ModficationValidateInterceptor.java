package com.icepoint.base.config.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

@Intercepts(
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
)
public class ModficationValidateInterceptor {
}
