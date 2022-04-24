package com.icepoint.base.api.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkDataSeries {

    private String name;

    private String type = "line";

    private List<Integer> data;

}
