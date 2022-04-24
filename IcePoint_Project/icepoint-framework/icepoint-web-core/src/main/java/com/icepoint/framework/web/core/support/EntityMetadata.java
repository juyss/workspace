package com.icepoint.framework.web.core.support;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

/**
 * @author Jiawei Zhao
 */
@AllArgsConstructor
@Data
public class EntityMetadata<T, ID> {

    private final JpaEntityInformation<T, ID> entityInformation;

    private final TableInfo tableInfo;
}
