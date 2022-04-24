package com.icepoint.base.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkData {

    private List<String> xAxis;

    private List<ParkDataSeries> series;
}
