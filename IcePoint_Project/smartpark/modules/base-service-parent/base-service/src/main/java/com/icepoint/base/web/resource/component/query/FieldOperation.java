package com.icepoint.base.web.resource.component.query;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Data
public class FieldOperation {

    private final @NonNull String field;

    private final @NonNull Map<Operation, Object> ops;

}
