package com.github.tangyi.office.mapper;

import com.github.tangyi.pub.api.module.office.SysCntLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface OfficeMapper {

    List<SysCntLink> officeList();

    List<SysCntLink> getLinkById(@Param("id") Long id,@Param("name") String name);

    Integer updateBySort(@Param("id") Long id,@Param("name") String name,@Param("idx") Integer idx);

    Integer deleteTree(@Param("childs") List<Long> childs);

    List<Long> queryChild(@Param("id") Long id);

    Integer updateByid(@Param("id") Long id, @Param("info") SysCntLink sysCntLink);

    Integer addNode(@Param("id") Long id,@Param("info") SysCntLink sysCntLink);

    Integer UpdateNode(@Param("id") Long id, @Param("info") SysCntLink sysCntLink);

    @Select("select parentId from sys_cnt_link where id = #{id}")
    Long queryById(@Param("id") Long id);

    SysCntLink selectById(@Param("id") Long id);

    SysCntLink selectByTop(@Param("parentId") Long parentId);

    @Update("update sys_cnt_link set idx = #{info.idx} where id =#{info.id}  ")
    Integer UpdateIdx(@Param("info") SysCntLink sysCntLink);

    SysCntLink selectByFloor(Long parentId);

    List<SysCntLink> queryAllList();

}
