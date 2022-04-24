package com.icepoint.framework.workorder.configuration.agilebpm.wf;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.id.IdGenerator;
import com.dstz.bpm.act.cache.ActivitiDefCache;
import com.dstz.bpm.act.id.ActivitiIdGenerator;
import com.dstz.bpm.act.listener.GlobalEventListener;
import com.dstz.bpm.api.engine.service.BpmEngineService;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.org.api.service.UserService;
import com.icepoint.framework.workorder.configuration.agilebpm.base.AgileBpmDataSourceProperties;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class AbBpmAutoConfiguration {

    private final AgileBpmDataSourceProperties properties;
    private final IdGenerator idGenerator;

    @Bean
    ProcessEngineFactoryBean processEngineFactory(SpringProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean processEngine = new ProcessEngineFactoryBean();
        processEngine.setProcessEngineConfiguration(processEngineConfiguration);
        return processEngine;
    }

    @Bean(
            name = {"repositoryService"}
    )
    RepositoryService processEngine(ProcessEngineFactoryBean processEngineFactory) throws Exception {
        ProcessEngine processEngine = processEngineFactory.getObject();
        assert processEngine != null;
        return processEngine.getRepositoryService();
    }

    @Bean(
            name = {"runtimeService"}
    )
    RuntimeService runtimeService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
        ProcessEngine processEngine = processEngineFactory.getObject();
        assert processEngine != null;
        return processEngine.getRuntimeService();
    }

    @Bean(
            name = {"taskService"}
    )
    TaskService taskService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
        ProcessEngine processEngine = processEngineFactory.getObject();
        assert processEngine != null;
        return processEngine.getTaskService();
    }

    @Bean(
            name = {"historyService"}
    )
    HistoryService historyService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
        ProcessEngine processEngine = processEngineFactory.getObject();
        assert processEngine != null;
        return processEngine.getHistoryService();
    }

    @Bean(
            name = {"managementService"}
    )
    ManagementService managementService(ProcessEngineFactoryBean processEngineFactory) throws Exception {
        ProcessEngine processEngine = processEngineFactory.getObject();
        assert processEngine != null;
        return processEngine.getManagementService();
    }

    @Bean
    SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager, ActivitiDefCache activitiDefCache, GlobalEventListener globalEventListener) {
        SpringProcessEngineConfiguration processConfiguration = new SpringProcessEngineConfiguration();
        processConfiguration.setDataSource(dataSource);
        processConfiguration.setDatabaseType(this.properties.getDbType().getKey());
        processConfiguration.setDbIdentityUsed(false);
        processConfiguration.setHistory("none");
        processConfiguration.setTransactionManager(transactionManager);
        processConfiguration.setDatabaseSchemaUpdate("true");
        processConfiguration.setJobExecutorActivate(true);
        processConfiguration.setIdGenerator(this.activitiIdGenerator());
        processConfiguration.setProcessDefinitionCache(activitiDefCache);
        processConfiguration.setLabelFontName("宋体");
        processConfiguration.setActivityFontName("宋体");
        processConfiguration.setEventListeners(Collections.singletonList(globalEventListener));
        return processConfiguration;
    }

    @Bean
    GlobalEventListener globalEventListener() {
        GlobalEventListener globalEventListener = new GlobalEventListener();
        Map<String, String> map = new HashMap<>();
        map.put("TASK_CREATED", "taskCreateListener");
        map.put("TASK_COMPLETED", "taskCompleteListener");
        map.put("PROCESS_STARTED", "instanceStartEventListener");
        map.put("PROCESS_COMPLETED", "instanceEndEventListener");
        map.put("ACTIVITY_STARTED", "activityStartedListener");
        map.put("ACTIVITY_COMPLETED", "activityComplatedListener");
        map.put("SEQUENCEFLOW_TAKEN", "sequenceFlowTakenListener");
        map.put("ACTIVITY_CANCELLED", "activitiCancelledListener");
        map.put("TIMER_FIRED", "automaticTaskListener");
        map.put("JOB_EXECUTION_SUCCESS", "jobExecutedListener");
        map.put("JOB_EXECUTION_FAILURE", "jobExecutedListener");
        globalEventListener.setHandlers(map);
        return globalEventListener;
    }

    ActivitiIdGenerator activitiIdGenerator() {
        ActivitiIdGenerator activitiIdGenerator = new ActivitiIdGenerator();
        activitiIdGenerator.setIdGenerator(this.idGenerator);
        return activitiIdGenerator;
    }

    @Bean
    BpmEngineService checkUserOrgEngineService(UserService userService) {
        BpmEngineService bpmEngineService = new BpmEngineService();
        if (userService == null) {
            throw new BusinessException("BPM 模块 依赖 ORG 服务，请检查组织服务是否注入成功！", BpmStatusCode.SYSTEM_ERROR);
        } else {
            bpmEngineService.setUserService(userService);
            return bpmEngineService;
        }
    }
}
