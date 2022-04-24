package com.icepoint.base.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanDto {

    String fileName;
    String intelligence;
    List<String> deptIdList;
    Integer page;
    Integer size;

}
