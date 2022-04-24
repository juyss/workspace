package com.icepoint.framework.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TreeUser  {
        private Integer id;
        private String name;
        private Integer age;
        private Integer parentId;
        private List<Object> childen;



}
