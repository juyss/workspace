package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.UserAuths;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserAuthsMapper
 *
 * @author tangyi
 * @date 2019/07/03 11:44
 */
@Mapper
public interface UserAuthsMapper extends CrudMapper<UserAuths> {

    /**
     * 根据唯一标识查询
     *
     * @param userAuths userAuths
     * @return UserAuths
     * @author tangyi
     * @date 2019/07/03 11:52:27
     */
    UserAuths getByIdentifier(UserAuths userAuths);

    /**
     * 根据用户id批量查询
     *
     * @param userIds userIds
     * @return List
     * @author tangyi
     * @date 2019/07/03 22:02:13
     */
    List<UserAuths> getListByUserIds(@Param("userIds") Long[] userIds);

    /**
     * 根据唯一标识删除
     *
     * @param userAuths userAuths
     * @return int
     * @author tangyi
     * @date 2019/07/04 11:39:50
     */
    int deleteByIdentifier(UserAuths userAuths);


    /**
     * 根据用户ID删除
     *
     * @param userAuths userAuths
     * @return int
     * @author tangyi
     * @date 2019/07/04 11:43:50
     */
    int deleteByUserId(UserAuths userAuths);

    /**
     * 批量插入
     *
     * @param userAuths userAuths
     * @return int
     * @author tangyi
     * @date 2019-09-03 13:07
     */
    int insertBatch(List<UserAuths> userAuths);

    /**
     * 根据用户ID查询用户授权
     * @param userId 用户id
     * @return 用户授权信息
     */
    UserAuths getIdentifierByUserId(@Param("userId") Long userId);
}
