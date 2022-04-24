package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.exam.api.module.Subject;
import com.github.tangyi.exam.api.module.SubjectChoices;
import org.apache.ibatis.annotations.Mapper;

/**
 * 选择题Mapper
 *
 * @author tangyi
 * @date 2018/11/8 21:15
 */
@Mapper
public interface SubjectMapper extends CrudMapper<Subject> {

    /**
     * 物理删除
     *
     * @param Subject subject
     * @return int
     * @author tangyi
     * @date 2019/06/16 22:44
     */
    int physicalDelete(Subject subject);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author tangyi
     * @date 2019/06/16 22:44
     */
    int physicalDeleteAll(Long[] ids);
}
