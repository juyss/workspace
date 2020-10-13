package com.juyss.mapper;

import com.juyss.bean.AdministrableRoleAuthorizations;
import com.juyss.bean.AdministrableRoleAuthorizationsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministrableRoleAuthorizationsMapper {
    int countByExample(AdministrableRoleAuthorizationsExample example);

    int deleteByExample(AdministrableRoleAuthorizationsExample example);

    int insert(AdministrableRoleAuthorizations record);

    int insertSelective(AdministrableRoleAuthorizations record);

    List<AdministrableRoleAuthorizations> selectByExample(AdministrableRoleAuthorizationsExample example);

    int updateByExampleSelective(@Param("record") AdministrableRoleAuthorizations record, @Param("example") AdministrableRoleAuthorizationsExample example);

    int updateByExample(@Param("record") AdministrableRoleAuthorizations record, @Param("example") AdministrableRoleAuthorizationsExample example);
}