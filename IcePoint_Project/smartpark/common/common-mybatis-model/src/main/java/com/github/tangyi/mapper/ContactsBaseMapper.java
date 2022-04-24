package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.Contacts;
import org.apache.ibatis.annotations.Mapper;

/**
 * contacts 通讯录
 *
 * @author xh
 * @since 2020/11/13
 */
@Mapper
public interface ContactsBaseMapper extends CommonDaoMapper<Contacts> {
}
