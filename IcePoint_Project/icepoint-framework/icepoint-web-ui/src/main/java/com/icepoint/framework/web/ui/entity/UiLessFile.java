package com.icepoint.framework.web.ui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ui_less_file
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ui_less_file")
public class UiLessFile extends LongStdEntity {

    private String fileName;

    private String url;



}
