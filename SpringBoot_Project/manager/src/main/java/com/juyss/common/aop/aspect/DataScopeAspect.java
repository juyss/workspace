package com.juyss.common.aop.aspect;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: DataScopeAspect
 * @Desc: 数据权限控制切面
 *      概念：最终控制列表中显示哪些 部门/人 创建的数据
 *      1、需要数据权限控制的列表， 需要有创建人字段， 示例：文章管理
 *      2、配置角色的数据范围（本部门，其他部门等）， 以及绑定的部门
 *      3、加个注解，用来查询当前等路人的多个角色（并集）， 根据角色数据范围， 获取绑定的部门id， 查关联的用户id
 *      4、在查某个模块的list或page的时候，手动queryWrapper.in(createId, 关联的用户id)
 * @package com.juyss.common.aop.aspect
 * @project manager
 * @date 2021/1/12 17:12
 */
public class DataScopeAspect {
}
