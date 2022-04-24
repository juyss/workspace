package com.icepoint.base.web.park.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.GenericProperty;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.vo.ParkData;
import com.icepoint.base.web.park.service.DataService;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.service.complex.upper.GenericEntityService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Longfei Xiang
 */
@SuppressWarnings("rawtypes")
@Api(tags = "园区数据管理接口")
@RequestMapping("park/data")
@RestController
@RequiredArgsConstructor
public class ParkDataController {

    private final DataService dataService;

    private final GenericEntityService genericEntityService;

    @GetMapping("year")
    public ResponseBean<ParkData> getParkDataYear(Integer startYear, Integer endYear) {
        return ResponseBeanUtils.queryOne(dataService.getParkDataYearEcharts(startYear, endYear));
    }

    @GetMapping("quarter")
    public ResponseBean<ParkData> getParkDataQuarter(Integer startYear, Integer endYear, Integer quarter) {
        return ResponseBeanUtils.queryOne(dataService.getParkDataQuarterEcharts(startYear, endYear, quarter));
    }

    /**
     * 驾驶舱最新资讯接口，分别构建
     * @param pageable 分页参数
     * @return 获得产业资讯、行业动态、前沿科技最新数据，数量由前端指定
     */
    @GetMapping("info")
    public ResponseBean<List<GenericEntity>> getInfo(Pageable pageable){

        //-----------------------产业资讯------------------------
        GenericQueryParameter lndustryInformationParameter = new GenericQueryParameter(); //产业资讯请求参数
        //构建请求参数
        HashMap<Operation,Object> lndustryInformationOperationHashMap = new HashMap<>();
        lndustryInformationOperationHashMap.put(Operation.CONTAINS,"");

        FieldOperation lndustryInformationFieldOperation = new FieldOperation("name", lndustryInformationOperationHashMap);

        HashMap<String, FieldOperation> lndustryInformationFieldOperationMap = new HashMap<>();
        lndustryInformationFieldOperationMap.put("name", lndustryInformationFieldOperation);
        Match lndustryInformationMatch = new Match(lndustryInformationFieldOperationMap);

        lndustryInformationParameter.setMatch(lndustryInformationMatch);

        Page<GenericEntity> lndustryInformationPage = genericEntityService.page(lndustryInformationParameter, "lndustryInformation", SerializeType.VALUE, pageable);
        //得到产业资讯的最新数据
        List<GenericEntity> lndustryInformationContent = lndustryInformationPage.getContent();

        //-----------------------行业动态------------------------
        GenericQueryParameter affairOpenParameter = new GenericQueryParameter(); //行业动态请求参数
        //构建请求参数
        HashMap<Operation,Object> affairOpenOperationHashMap = new HashMap<>();
        affairOpenOperationHashMap.put(Operation.CONTAINS,"行业动态");
        affairOpenOperationHashMap.put(Operation.EQ,"1");

        FieldOperation affairOpenFieldOperation = new FieldOperation("plate", affairOpenOperationHashMap);
        FieldOperation affairOpenFieldOperation1 = new FieldOperation("releaseStatus", affairOpenOperationHashMap);

        HashMap<String, FieldOperation> affairOpenFieldOperationMap = new HashMap<>();
        affairOpenFieldOperationMap.put("plate", affairOpenFieldOperation);
        affairOpenFieldOperationMap.put("releaseStatus", affairOpenFieldOperation1);
        Match affairOpenMatch = new Match(affairOpenFieldOperationMap);

        affairOpenParameter.setMatch(affairOpenMatch);

        Page<GenericEntity> affairOpenPage = genericEntityService.page(affairOpenParameter, "affairOpen", SerializeType.VALUE, pageable);
        //得到行业动态的最新数据
        List<GenericEntity> affairOpenContent = affairOpenPage.getContent();

        //-----------------------前沿科技------------------------
        GenericQueryParameter parameter = new GenericQueryParameter(); //前沿科技请求参数
        //构建请求参数
        HashMap<Operation,Object> operationObjectHashMap = new HashMap<>();
        operationObjectHashMap.put(Operation.CONTAINS,"前沿科技");
        operationObjectHashMap.put(Operation.EQ,"1");

        FieldOperation fieldOperation = new FieldOperation("plate", operationObjectHashMap);
        FieldOperation fieldOperation1 = new FieldOperation("releaseStatus", operationObjectHashMap);

        HashMap<String, FieldOperation> stringFieldOperationHashMap = new HashMap<>();
        stringFieldOperationHashMap.put("plate", fieldOperation);
        stringFieldOperationHashMap.put("releaseStatus", fieldOperation1);
        Match match = new Match(stringFieldOperationHashMap);

        parameter.setMatch(match);

        Page<GenericEntity> page = genericEntityService.page(parameter, "affairOpen", SerializeType.VALUE, pageable);
        //得到前沿科技的最新数据
        List<GenericEntity> content = page.getContent();

        ArrayList<GenericEntity> entities = new ArrayList<>();
        entities.addAll(lndustryInformationContent);
        entities.addAll(affairOpenContent);
        entities.addAll(content);

        return ResponseBeanUtils.queryMany(entities);
    }

    /**
     * 驾驶舱风险企业数量接口
     * @return 每个风险等级的企业数量
     */
    @GetMapping("countOfRiskRating")
    public ResponseBean<Map<String, Integer>> getCountOfRiskRating(){

        //构建空的查询参数
        GenericQueryParameter parameter = new GenericQueryParameter();
        parameter.setMatch(null);

        //安全评价报告数据
        List<GenericEntity> dataList = genericEntityService.list(parameter, "safetyEvaluation", SerializeType.VALUE);

        //数据字典风险等级编码
        List<GenericEntity> codeList = genericEntityService.list(parameter, "safetySupervisionAlarms", SerializeType.VALUE);

        //构建结果集
        HashMap<String, Integer> map = new HashMap<>();

        //统计每个风险等级的企业数量
        for (GenericEntity code : codeList) {
            String riskCode = (String) code.getProperty("code").getValue();
            String riskName = (String) code.getProperty("name").getValue();
            int count=0;
            for (GenericEntity data : dataList) {
                String riskData = (String) data.getProperty("risk_rating_code").getValue();
                if (riskCode != null && riskCode.equals(riskData)){
                    count++;
                }
                map.put(riskName,count);
            }
        }

        return new ResponseBean<>(map);
    }

    /**
     * 驾驶舱两重大一重点数量
     * @return 类别及其数量
     */
    @GetMapping("countOfImportantThing")
    public ResponseBean<Map<String, Object>> getCountOfImportantThing(Pageable pageable){


        //构建空的查询参数
        GenericQueryParameter parameter = new GenericQueryParameter();
        parameter.setMatch(null);

        //重点监管化工工艺(园区)
        List<GenericEntity> dangerousChemical = genericEntityService.list(parameter, "dangerousChemical", SerializeType.VALUE);
        //数量
        int dangerousChemicalSize = dangerousChemical.size();

        //重点监管化学品(园区)
        Page<GenericEntity> keyHazardousChemicals = genericEntityService.page(parameter, "keyHazardousChemicals", SerializeType.VALUE, pageable);
        //数量
        long keyHazardousChemicalsCount = keyHazardousChemicals.getTotalElements();

        //重大危险源
        Page<GenericEntity> majorDanger = genericEntityService.page(parameter, "majorDanger", SerializeType.VALUE, pageable);
        //数量
        long totalElementsCount = majorDanger.getTotalElements();

        //创建结果集
        HashMap<String, Object> map = new HashMap<>();
        map.put("重点监管工艺数量",dangerousChemicalSize);
        map.put("重点监管危化品数量",keyHazardousChemicalsCount);
        map.put("重大危险源数量",totalElementsCount);

        return new ResponseBean<>(map);
    }

}
