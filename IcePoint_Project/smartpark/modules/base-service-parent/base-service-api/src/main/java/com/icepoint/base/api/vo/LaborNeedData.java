package com.icepoint.base.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LaborNeedData {

    private List<String> xAxis;

    private List<Integer> series;
}
