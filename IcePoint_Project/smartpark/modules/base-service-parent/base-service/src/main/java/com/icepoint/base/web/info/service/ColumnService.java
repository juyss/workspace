package com.icepoint.base.web.info.service;

import com.icepoint.base.web.basic.service.CrudService;
import com.icepoint.base.web.info.entity.Column;

public interface ColumnService extends CrudService<Column, Long> {
    Integer maxSort(Column map);
}
