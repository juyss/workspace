package com.icepoint.base.web.park.mapper;

import com.icepoint.base.api.entity.DataQuarter;
import com.icepoint.base.api.entity.DataYear;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DataMapper {

    List<String> getParkDataYearXAxisList(Integer startYear, Integer endYear);

    List<DataYear> listDataYear(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);

    List<String> getParkDataQuarterXAxisList();

    List<DataQuarter> listDataQuarter(Integer startYear, Integer endYear, Integer quarter);
}
