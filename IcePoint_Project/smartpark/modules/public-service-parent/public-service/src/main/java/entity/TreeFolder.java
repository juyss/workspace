package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icepoint.base.api.domain.BasicEntity;
import com.icepoint.base.api.domain.OrderedParentIdHierarchy;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TreeFolder extends BasicEntity<Long> implements OrderedParentIdHierarchy {

    private String name;
    private Long parentId;
    private String type;
    private Integer deleted;

    @JsonIgnore
    @Override
    public int getOrder() {
        return getId().intValue();
    }

}
