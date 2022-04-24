package com.icepoint.base.web.park.service.impl;

import com.icepoint.base.api.entity.DataQuarter;
import com.icepoint.base.api.entity.DataYear;
import com.icepoint.base.api.vo.ParkData;
import com.icepoint.base.api.vo.ParkDataSeries;
import com.icepoint.base.web.park.mapper.DataMapper;
import com.icepoint.base.web.park.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    protected DataMapper dataMapper;

    @Override
    public ParkData getParkDataYearEcharts(Integer startYear, Integer endYear) {
        ParkData parkData = new ParkData();
        List<ParkDataSeries> series = new LinkedList<>();
        parkData.setSeries(series);

        ParkDataSeries salesIncomeSeries = new ParkDataSeries();
        salesIncomeSeries.setName("园区总营业收入");
        List<Integer> salesIncomeList = new LinkedList<>();
        salesIncomeSeries.setData(salesIncomeList);
        ParkDataSeries totalPretaxProfitsSeries = new ParkDataSeries();
        totalPretaxProfitsSeries.setName("利税总额");
        List<Integer> totalPretaxProfitsList = new LinkedList<>();
        totalPretaxProfitsSeries.setData(totalPretaxProfitsList);
        ParkDataSeries chemicalIncomeSeries = new ParkDataSeries();
        chemicalIncomeSeries.setName("化工企业营业收入");
        List<Integer> chemicalIncomeList = new LinkedList<>();
        chemicalIncomeSeries.setData(chemicalIncomeList);
        ParkDataSeries chemicalProfitSeries = new ParkDataSeries();
        chemicalProfitSeries.setName("化工企业营业利润");
        List<Integer> chemicalProfitList = new LinkedList<>();
        chemicalProfitSeries.setData(chemicalProfitList);
        ParkDataSeries iavEnergyQuotaSeries = new ParkDataSeries();
        iavEnergyQuotaSeries.setName("园区整体工业增加值能耗限额");
        List<Integer> iavEnergyQuotaList = new LinkedList<>();
        iavEnergyQuotaSeries.setData(iavEnergyQuotaList);
        ParkDataSeries energyEfficiencySeries = new ParkDataSeries();
        energyEfficiencySeries.setName("园区能效");
        List<Integer> energyEfficiencyList = new LinkedList<>();
        energyEfficiencySeries.setData(energyEfficiencyList);

        series.add(salesIncomeSeries);
        series.add(totalPretaxProfitsSeries);
        series.add(chemicalIncomeSeries);
        series.add(chemicalProfitSeries);
        series.add(iavEnergyQuotaSeries);
        series.add(energyEfficiencySeries);

        // 查询X轴
        parkData.setXAxis(dataMapper.getParkDataYearXAxisList(startYear, endYear));
        // 查询所有数据
        List<DataYear> dataYearList = dataMapper.listDataYear(startYear, endYear);

        for (String xAxis : parkData.getXAxis()) {
            DataYear match = dataYearList.stream().filter(dataYear ->
                    xAxis.equals(dataYear.getYear().toString())
            ).findAny().get();

            salesIncomeList.add(Integer.valueOf(match.getSalesIncome()));
            totalPretaxProfitsList.add(Integer.valueOf(match.getTotalPretaxProfits()));
            chemicalIncomeList.add(Integer.valueOf(match.getChemicalIncome()));
            chemicalProfitList.add(Integer.valueOf(match.getChemicalProfit()));
            iavEnergyQuotaList.add(Integer.valueOf(match.getIavEnergyQuota()));
            energyEfficiencyList.add(Integer.valueOf(match.getEnergyEfficiency()));
        }

        return parkData;
    }

    @Override
    public ParkData getParkDataQuarterEcharts(Integer startYear, Integer endYear, Integer quarter) {
        ParkData parkData = new ParkData();
        List<ParkDataSeries> series = new LinkedList<>();
        parkData.setSeries(series);

        ParkDataSeries salesIncomeSeries = new ParkDataSeries();
        salesIncomeSeries.setName("园区总营业收入");
        List<Integer> salesIncomeList = new LinkedList<>();
        salesIncomeSeries.setData(salesIncomeList);
        ParkDataSeries totalPretaxProfitsSeries = new ParkDataSeries();
        totalPretaxProfitsSeries.setName("利税总额");
        List<Integer> totalPretaxProfitsList = new LinkedList<>();
        totalPretaxProfitsSeries.setData(totalPretaxProfitsList);
        ParkDataSeries chemicalIncomeSeries = new ParkDataSeries();
        chemicalIncomeSeries.setName("化工企业营业收入");
        List<Integer> chemicalIncomeList = new LinkedList<>();
        chemicalIncomeSeries.setData(chemicalIncomeList);
        ParkDataSeries chemicalProfitSeries = new ParkDataSeries();
        chemicalProfitSeries.setName("化工企业营业利润");
        List<Integer> chemicalProfitList = new LinkedList<>();
        chemicalProfitSeries.setData(chemicalProfitList);
        ParkDataSeries iavEnergyQuotaSeries = new ParkDataSeries();
        iavEnergyQuotaSeries.setName("园区整体工业增加值能耗限额");
        List<Integer> iavEnergyQuotaList = new LinkedList<>();
        iavEnergyQuotaSeries.setData(iavEnergyQuotaList);
        ParkDataSeries energyEfficiencySeries = new ParkDataSeries();
        energyEfficiencySeries.setName("园区能效");
        List<Integer> energyEfficiencyList = new LinkedList<>();
        energyEfficiencySeries.setData(energyEfficiencyList);

        series.add(salesIncomeSeries);
        series.add(totalPretaxProfitsSeries);
        series.add(chemicalIncomeSeries);
        series.add(chemicalProfitSeries);
        series.add(iavEnergyQuotaSeries);
        series.add(energyEfficiencySeries);

        // 查询所有数据
        List<DataQuarter> dataQuarterList = dataMapper.listDataQuarter(startYear, endYear, quarter);

        List<String> xAxisList = new ArrayList<>();

        // 构造X轴和数据
        dataQuarterList.forEach(dataQuarter -> {
            StringBuilder xAxis = new StringBuilder(dataQuarter.getYear());
            switch (dataQuarter.getQuarter()) {
                case 1:
                    xAxis.append(dataQuarter.getYear());
                    xAxis.append("第一季度");
                    break;
                case 2:
                    xAxis.append(dataQuarter.getYear());
                    xAxis.append("第二季度");
                    break;
                case 3:
                    xAxis.append(dataQuarter.getYear());
                    xAxis.append("第三季度");
                    break;
                case 4:
                    xAxis.append(dataQuarter.getYear());
                    xAxis.append("第四季度");
                    break;
            }
            xAxisList.add(xAxis.toString());

            salesIncomeList.add(Integer.valueOf(dataQuarter.getSalesIncome()));
            totalPretaxProfitsList.add(Integer.valueOf(dataQuarter.getTotalPretaxProfits()));
            chemicalIncomeList.add(Integer.valueOf(dataQuarter.getChemicalIncome()));
            chemicalProfitList.add(Integer.valueOf(dataQuarter.getChemicalProfit()));
            iavEnergyQuotaList.add(Integer.valueOf(dataQuarter.getIavEnergyQuota()));
            energyEfficiencyList.add(Integer.valueOf(dataQuarter.getEnergyEfficiency()));

        });

        // 设置X轴
        parkData.setXAxis(xAxisList);

        return parkData;
    }

}
