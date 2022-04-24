package com.icepoint.framework.web.system.resource;

import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.entity.TableMetadata;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public interface ResourceModelMetadata extends Serializable {

    Resource getResource();

    List<FieldMetadata> getFields();

    TableMetadata getTable();

    AssetDefine getAssetDefine();

}
