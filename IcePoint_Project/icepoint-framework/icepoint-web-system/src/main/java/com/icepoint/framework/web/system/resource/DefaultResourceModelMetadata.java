package com.icepoint.framework.web.system.resource;

import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.entity.TableMetadata;
import lombok.Data;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
@Data
public class DefaultResourceModelMetadata implements ResourceModelMetadata {

    private final Resource resource;

    private final List<FieldMetadata> fields;

    private final TableMetadata table;

    private final AssetDefine assetDefine;

}
