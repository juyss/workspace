package com.github.tangyi.user.utils;

import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.basic.vo.RoleVo;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.user.api.dto.UserInfoDto;
import com.github.tangyi.user.api.module.Role;
import com.github.tangyi.user.api.module.User;
import com.github.tangyi.user.api.module.UserAuths;
import com.github.tangyi.user.excel.model.UserExcelModel;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户工具类
 *
 * @author tangyi
 * @date 2018/11/26 22:32
 */
public class UserUtils {

	private UserUtils() {
	}

	/**
	 * 转换对象
	 * @param userInfoDtos userInfoDtos
	 * @return List
	 */
	public static List<UserExcelModel> convertToExcelModel(List<UserInfoDto> userInfoDtos) {
		List<UserExcelModel> userExcelModels = new ArrayList<>(userInfoDtos.size());
		userInfoDtos.forEach(userInfoDto -> {
			UserExcelModel userExcelModel = new UserExcelModel();
			BeanUtils.copyProperties(userInfoDto, userExcelModel);
			if (!CollectionUtils.isEmpty(userInfoDto.getRoles())) {
				userExcelModel.setRoles(String.join(" ", userInfoDto.getRoles()));
			}
			userExcelModels.add(userExcelModel);
		});
		return userExcelModels;
	}

	/**
	 * Role 转 RoleVo
	 *
	 * @param roles roles
	 * @return List
	 * @author tangyi
	 * @date 2019/07/03 13:11:05
	 */
	public static List<RoleVo> rolesToVo(List<Role> roles) {
		return roles.stream().map(role -> {
			RoleVo roleVo = new RoleVo();
			roleVo.setRoleCode(role.getRoleCode());
			roleVo.setRoleName(role.getRoleName());
			roleVo.setRoleDesc(role.getRoleDesc());
			return roleVo;
		}).collect(Collectors.toList());
	}

	/**
	 * 转DTO
	 *
	 * @param userInfoDto userInfoDto
	 * @param user        user
	 * @param userAuths   userAuths
	 * @return UserInfoDto
	 * @author tangyi
	 * @date 2019/07/03 20:23:15
	 */
	public static void toUserInfoDto(UserInfoDto userInfoDto, User user, UserAuths userAuths) {
		BeanUtils.copyProperties(userAuths, userInfoDto);
		BeanUtils.copyProperties(user, userInfoDto);
	}

	/**
	 * 是否为管理员
	 *
	 * @param identifier identifier
	 * @return boolean
	 * @author tangyi
	 * @date 2019/07/04 00:25:11
	 */
	public static boolean isAdmin(String identifier) {
		SysProperties sysProperties = SpringContextHolder.getApplicationContext().getBean(SysProperties.class);
		return identifier.equals(sysProperties.getAdminUser());
	}
}
