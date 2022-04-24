package com.icepoint.framework.workorder.configuration.agilebpm;

import com.dstz.base.db.api.table.DbType;
import com.dstz.base.db.datasource.DataSourceUtil;
import com.dstz.base.db.datasource.DbContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Component
@Aspect
public class DynamicDataSourceAdapterAspect {

    private static final String BPM = "com.dstz";
    private static final String ACTIVITI = "org.activiti";

    @Before("execution(public * org.activiti.engine..*.*(..))")
    public void before(JoinPoint point) {

        if ("getObjectType".equals(point.getSignature().getName())) {
            return;
        }

        String packageName = point.getTarget().getClass().getPackage().getName();

        String dbType = DbType.MYSQL.getKey();
        if (packageName.startsWith(ACTIVITI)) {
            DbContextHolder.setDataSource(DataSourceUtil.BPM_DATASOURCE, dbType);
        } else if (DbContextHolder.isEmpty()) {
            String dataSourceKey = packageName.startsWith(BPM) || packageName.startsWith(ACTIVITI)
                    ? DataSourceUtil.BPM_DATASOURCE
                    : DataSourceUtil.DEFAULT_DATASOURCE;

            DbContextHolder.setDataSource(dataSourceKey, dbType);
        }

    }
}
