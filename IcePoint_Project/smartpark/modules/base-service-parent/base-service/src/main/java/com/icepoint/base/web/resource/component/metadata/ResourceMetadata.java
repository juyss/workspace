package com.icepoint.base.web.resource.component.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icepoint.base.api.entity.LinkTab;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.entity.MetaTab;
import com.icepoint.base.api.entity.Resource;
import com.icepoint.base.web.sys.entity.Dict;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * 资源元数据对象，作为整个通用资源服务的核心对象之一，
 * 在不同形式的对象和存储形式的转换中担任重要的角色。
 * 不建议自行构建此对象，应该通过{@link com.icepoint.base.web.resource.service.complex.ResourceMetadataService#get}来获取
 *
 * @author Jiawei Zhao
 */
@Builder
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceMetadata implements Serializable {

    private @NonNull MetaTab metaTab;

    private @NonNull List<MetaField> metaFields;

    private @NonNull List<LinkTab> linkFields;

    private @NonNull List<LinkTab> virtualFields;

    private @NonNull Resource resource;

    private @NonNull Dict dict;

    private @NonNull String key;

    private @NonNull String name;
}
