package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.exam.api.module.Examination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 考试Mapper
 *
 * @author tangyi
 * @date 2018/11/8 21:11
 */
@Mapper
public interface ExaminationMapper extends CrudMapper<Examination> {

    /**
     * 查询考试数量
     *
     * @param examination examination
     * @return int
     * @author tangyi
     * @date 2019/3/1 15:32
     */
    int findExaminationCount(Examination examination);

    /**
     * 查询参与考试人数
     *
     * @param examination examination
     * @return int
     * @author tangyi
     * @date 2019/10/27 20:08:58
     */
    int findExamUserCount(Examination examination);

    /**
     * 已命制试题总分
     * @param examinationId
     * @return
     */
    Double getItemScoreTotal(@Param("examinationId") Long examinationId);

    List<Map<String,Object>> myExaminations(@Param("sort") String sort,@Param("order") String order, Examination examination, @Param("userId")Long userId);
}
