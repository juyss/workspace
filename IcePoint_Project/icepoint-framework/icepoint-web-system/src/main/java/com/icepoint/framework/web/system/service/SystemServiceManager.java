package com.icepoint.framework.web.system.service;

/**
 * @author Jiawei Zhao
 */
public interface SystemServiceManager {

    AssetService getAssetService();

    AttributeService getAttributeService();

    FieldMetadataService getFieldMetadataService();

    GenericDataSequenceService getGenericDataSequenceService();

    GenericDataService getGenericDataService();

    ModuleService getModuleService();

    ProjectService getProjectService();

    ResourceMetadataService getResourceMetadataService();

    ResourceModelService getResourceModelService();

    ResourceService getResourceService();

    TableMetadataService getTableMetadataService();

    TableServiceService getTableServiceService();

    ParameterService getParameterService();
}
