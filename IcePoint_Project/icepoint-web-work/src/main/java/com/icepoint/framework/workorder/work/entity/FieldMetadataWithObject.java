package com.icepoint.framework.workorder.work.entity;

import com.icepoint.framework.web.system.entity.FieldMetadata;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName FieldMetadataWithObject
 * @description
 * @since 2021-07-20 16:14
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FieldMetadataWithObject extends FieldMetadata {

    private Object uiTypeObject;
}
