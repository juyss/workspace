package com.icepoint.framework.web.system.service.impl;

import com.icepoint.framework.web.system.service.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiawei Zhao
 */
@Getter
@Setter(value = AccessLevel.PACKAGE, onMethod_ = @Autowired)
@Service
public class SystemServiceManagerImpl implements SystemServiceManager {

    private AssetService assetService;

    private AttributeService attributeService;

    private FieldMetadataService fieldMetadataService;

    private GenericDataSequenceService genericDataSequenceService;

    private GenericDataService genericDataService;

    private ModuleService moduleService;

    private ProjectService projectService;

    private ResourceMetadataService resourceMetadataService;

    private ResourceModelService resourceModelService;

    private ResourceService resourceService;

    private TableMetadataService tableMetadataService;

    private TableServiceService tableServiceService;

    private ParameterService parameterService;
}
