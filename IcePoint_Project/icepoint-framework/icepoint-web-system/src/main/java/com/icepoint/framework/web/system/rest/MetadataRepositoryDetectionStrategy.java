package com.icepoint.framework.web.system.rest;

import com.icepoint.framework.web.system.util.EntityUrlUtils;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.util.StringUtils;

/**
 * @author Jiawei Zhao
 */
public class MetadataRepositoryDetectionStrategy implements RepositoryDetectionStrategy {

    @Override
    public boolean isExported(RepositoryMetadata metadata) {

        if (RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED.isExported(metadata)) {
            return true;
        }

        String url = EntityUrlUtils.getUrl(metadata.getDomainType());
        return StringUtils.hasText(url);
    }
}
