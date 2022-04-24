package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.api.entity.Head;
import com.icepoint.base.api.entity.TabSeq;
import com.icepoint.base.api.vo.ParkData;
import com.icepoint.base.api.vo.ParkDataSeries;
import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.resource.mapper.HeadMapper;
import com.icepoint.base.web.resource.mapper.TabSeqMapper;
import com.icepoint.base.web.resource.service.simple.HeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class HeadServiceImpl extends AntdPageService<HeadMapper, Head, Long> implements HeadService {

    private final TabSeqMapper tabSeqMapper;

    @Override
    public List<Head> listBy(Integer docType, Long id) {
        return getRepository().listBy(docType, id);
    }

    @Override
    public List<Head> listByDocNos(Integer docType, Collection<String> docNos) {
        return getRepository().listByDocNos(docType, docNos);
    }

    @Override
    public Page<String> getDocNoPage(@Nullable Object queryEntity, Integer docType, Pageable pageable) {
        return getRepository().getDocNoList(queryEntity, docType, pageable);
    }

    @Override
    public Long getNextId(Integer docType) {
        return getRepository().nextId(docType);
    }

    @Override
    public String getNextDocNo(Integer docType, String resourceName) {
        return getRepository().nextDocNo(docType, resourceName);
    }

    @Override
    public void update(Head entity) {
        getRepository().update(entity);
    }


    @Override
    public void updateForBig(Head head) {
        getRepository().updateForBig(head);
    }

    @Override
    public List<String> listPlanAnnex(List<String> docNoList) {
        return getRepository().listPlanAnnex(docNoList);
    }

    @Override
    public List<String> listMgtInstututionAnnex(List<String> docNoList) {
        return getRepository().listMgtInstututionAnnex(docNoList);
    }

    @Override
    public int updateAll(List<Head> entities) {
        return getRepository().updateAll(entities);
    }

    @Override
    public List<Head> addAllBig(List<Head> entities) {
        getRepository().addAllBig(entities);
        return entities;
    }

    @Override
    public void deleteAll(Long id, Integer docType) {
        getRepository().deleteBatch(id, docType);
        getRepository().deleteSeq(id, docType);
    }

    @Override
    public ParkData getParkDataYear() {
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
        salesIncomeSeries.setData(totalPretaxProfitsList);
        ParkDataSeries chemicalIncomeSeries = new ParkDataSeries();
        chemicalIncomeSeries.setName("化工企业营业收入");
        List<Integer> chemicalIncomeList = new LinkedList<>();
        salesIncomeSeries.setData(chemicalIncomeList);
        ParkDataSeries chemicalProfitSeries = new ParkDataSeries();
        chemicalProfitSeries.setName("化工企业营业利润");
        List<Integer> chemicalProfitList = new LinkedList<>();
        salesIncomeSeries.setData(chemicalProfitList);
        ParkDataSeries iavEnergyQuotaSeries = new ParkDataSeries();
        iavEnergyQuotaSeries.setName("园区整体工业增加值能耗限额");
        List<Integer> iavEnergyQuotaList = new LinkedList<>();
        salesIncomeSeries.setData(iavEnergyQuotaList);
        ParkDataSeries energyEfficiencySeries = new ParkDataSeries();
        energyEfficiencySeries.setName("园区能效");
        List<Integer> energyEfficiencyList = new LinkedList<>();
        salesIncomeSeries.setData(energyEfficiencyList);

        series.add(salesIncomeSeries);
        series.add(totalPretaxProfitsSeries);
        series.add(chemicalIncomeSeries);
        series.add(chemicalProfitSeries);
        series.add(iavEnergyQuotaSeries);
        series.add(energyEfficiencySeries);

        // 查询X轴
        parkData.setXAxis(getRepository().getParkDataYearXAxisList());
        // 查询Y轴
        List<Map> parkDataYearXAxisMap = getRepository().getParkDataYearMetadata();

        List<Head> all = getRepository().findAll(Head.builder().docType(33).build());// 33是年数据

        for (String xAxis : parkData.getXAxis()) {
            Map match = parkDataYearXAxisMap.stream().filter(map ->
                    xAxis.equals(map.get("value").toString())
            ).findAny().get();

            String docNo = match.get("docNo").toString();

            salesIncomeList.add(Integer.valueOf(all.stream().filter(head -> docNo.equals(head.getDocNo()) && "salesIncome".equals(head.getName())).findAny().get().getValue()));
            totalPretaxProfitsList.add(Integer.valueOf(all.stream().filter(head -> docNo.equals(head.getDocNo()) && "totalPretaxProfits".equals(head.getName())).findAny().get().getValue()));
            chemicalIncomeList.add(Integer.valueOf(all.stream().filter(head -> docNo.equals(head.getDocNo()) && "chemicalIncome".equals(head.getName())).findAny().get().getValue()));
            chemicalProfitList.add(Integer.valueOf(all.stream().filter(head -> docNo.equals(head.getDocNo()) && "chemicalProfit".equals(head.getName())).findAny().get().getValue()));
            iavEnergyQuotaList.add(Integer.valueOf(all.stream().filter(head -> docNo.equals(head.getDocNo()) && "iavEnergyQuota".equals(head.getName())).findAny().get().getValue()));
            energyEfficiencyList.add(Integer.valueOf(all.stream().filter(head -> docNo.equals(head.getDocNo()) && "energyEfficiency".equals(head.getName())).findAny().get().getValue()));
        }

        return parkData;
    }

    @Override
    public List<Head> addAll(List<Head> entities) {
        List<Head> result = super.addAll(entities);
        Head idHead = result.stream()
                .filter(head -> head.getName().equals("id"))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("找不到id的属性"));

        TabSeq tabSeq = TabSeq.builder()
                .docNo(idHead.getDocNo())
                .docType(idHead.getDocType())
                .id(Long.valueOf(idHead.getValue()))
                .deleted(0)
                .build();
        tabSeqMapper.add(tabSeq);
        return result;
    }


}