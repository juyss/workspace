package com.juyss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juyss.entity.SysDept;
import com.juyss.vo.resp.DeptRespNodeVO;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: DeptService
 * @Desc:
 * @package com.juyss.service
 * @project manager
 * @date 2021/1/20 16:28
 */
public interface DeptService extends IService<SysDept> {

    /**
     * 添加部门
     *
     * @param vo vo
     */
    void addDept(SysDept vo);

    /**
     * 更新部门
     *
     * @param vo vo
     */
    void updateDept(SysDept vo);

    /**
     * 删除部门
     *
     * @param id id
     */
    void deleted(String id);

    /**
     * 部门树形列表
     *
     * @param deptId   deptId
     * @param disabled 最顶级是否可用
     * @return 树形列表
     */
    List<DeptRespNodeVO> deptTreeList(String deptId, Boolean disabled);
}

