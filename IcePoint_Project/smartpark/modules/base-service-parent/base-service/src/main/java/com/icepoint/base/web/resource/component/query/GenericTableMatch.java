package com.icepoint.base.web.resource.component.query;

import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.util.MatchSqlAssistants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
public class GenericTableMatch extends Match {

    private final String sql;

    private final Pageable pageable;

    public GenericTableMatch(Map<String, FieldOperation> fieldOps, ResourceMetadata metadata, Pageable pageable) {
        super(fieldOps);
        this.pageable = pageable;
        sql = MatchSqlAssistants.parseGenericTableSql(metadata, this);
    }
}
