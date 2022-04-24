package com.github.tangyi.office.service.impl;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.utils.TreeUtil;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.office.mapper.OfficeMapper;
import com.github.tangyi.office.service.IOfficeServie;
import com.github.tangyi.pub.api.dto.office.MoveIdxDto;
import com.github.tangyi.pub.api.module.office.SysCntLink;
import com.github.tangyi.user.api.feign.UserServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OfficeServiceImpl implements IOfficeServie {

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private OfficeMapper officeMapper;

    /**
     * 查询列表 转树形结构
     *
     * @return
     */
    @Override
    public List<SysCntLink> officeList() {
        List<SysCntLink> officeList = officeMapper.officeList();
        if (ObjectUtils.isEmpty(officeList)) {
            return null;
        }
        List<SysCntLink> sysCntLinksSort = officeList.stream().sorted(Comparator.comparing(SysCntLink::getIdx)).collect(Collectors.toList());
        List<SysCntLink> sysCntLinks = TreeUtil.buildTree(sysCntLinksSort, 0L);
        return sysCntLinks;
    }

    /**
     * 查询下级节点列表
     *
     * @param id
     * @return
     */
    @Override
    public List<SysCntLink> getLinkById(Long id, String name) {
        return this.officeMapper.getLinkById(id, name);
    }

    @Override
    public Integer updateBySort(Long id, String name, Integer idx) {
        return this.officeMapper.updateBySort(id, name, idx);
    }

    @Override
    public Integer deleteTree(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return 0;
        }
        //通过id 查询出所有的子节点
        List<Long> childs = officeMapper.queryChild(id);
        childs.add(id);
        return this.officeMapper.deleteTree(childs);
    }

    @Override
    public Integer updateByid(Long id, SysCntLink sysCntLink) {
        if (ObjectUtils.isEmpty(id)) {
            return 0;
        }
        return this.officeMapper.updateByid(id, sysCntLink);
    }



    /**
     * 新增 修改
     *
     * @param sysCntLink
     * @return
     */
    @Override
    public Integer addNode(SysCntLink sysCntLink) {
        Long  id = sysCntLink.getParentId();
        String username = SysUtil.getUser();
        UserVo user = userServiceClient.findUserByIdentifier(username, SysUtil.getTenantCode()).getData();
        sysCntLink.setCreateUser(user.getUserId());
        sysCntLink.setUpdateUser(user.getUserId());
        //新增
        if (ObjectUtils.isEmpty(sysCntLink.getId())) {
            Integer leave = getLeave(id, 0);
            sysCntLink.setLevel(leave);
            List<SysCntLink> childs = officeMapper.getLinkById(id, null);
            //得到当前子节点最大的idx
            List<SysCntLink> childsSort = childs.stream()
                    .sorted(Comparator.comparing(SysCntLink::getIdx).reversed()).collect(Collectors.toList());
            if (ObjectUtils.isEmpty(childsSort)) {
                sysCntLink.setIdx(1.0);
            }else{
                sysCntLink.setIdx(childsSort.get(0).getIdx() + 1);
            }

            return this.officeMapper.addNode(id, sysCntLink);
        }
        //修改
        return this.officeMapper.UpdateNode(id, sysCntLink);


    }

    /**
     * 根据id 查询实体
     *
     * @param id
     * @return SysCntLink
     */

    @Override
    public SysCntLink selectById(Long id) {

        return this.officeMapper.selectById(id);
    }

    /**
     * 置顶 :top  置底 :floor 上移 :up 下移 :down
     *
     *
     * @param moveIdxDto
     * @return Boolean
     */

    @Override
    public Boolean moveIdx(MoveIdxDto moveIdxDto) {
        String type = moveIdxDto.getType();
        if (ObjectUtils.isEmpty(type)) return false;
        //根据id 查询出parentId
        SysCntLink sysCntLink = this.officeMapper.selectById(moveIdxDto.getId());

        //置顶  根据parentId查出最顶层的数据
        if ("top".equals(type)) {
            SysCntLink top = this.officeMapper.selectByTop(sysCntLink.getParentId());
            //如果传进来的 和 查询的 是一样的 直接返回
            //得到最顶层idx-0.1
            sysCntLink.setIdx(top.getIdx() - 0.1);
            //根据id 修改序号
            return this.officeMapper.UpdateIdx(sysCntLink) > 0;
        }
        //置底
        if ("floor".equals(type)) {
            SysCntLink floor = this.officeMapper.selectByFloor(sysCntLink.getParentId());
            sysCntLink.setIdx(floor.getIdx()+1);
            return this.officeMapper.UpdateIdx(sysCntLink) > 0;
        }
        //上移
        if ("up".equals(type)||"down".equals(type)) {
            //当前idx
            Double floorIdx = moveIdxDto.getIdx();
            //需要移动idx
            Double idx = moveIdxDto.getByIdx();
            sysCntLink.setIdx(idx);
            //需要移动的id
            SysCntLink topSys = new SysCntLink();
            topSys.setId(moveIdxDto.getById());
            topSys.setIdx(floorIdx);
            this.officeMapper.UpdateIdx(sysCntLink);
            this.officeMapper.UpdateIdx(topSys);
            return true;
        }
        return false;
    }

    @Override
    public List<SysCntLink> queryAllList() {
        return  this.officeMapper.queryAllList();

    }


    /**
     * @param id
     * @param leave 根节点id
     * @return
     */
    public Integer getLeave(Long id, Integer leave) {

        leave++;
        if (id == 0) {
            return leave;
        }
        return getLeave(officeMapper.queryById(id), leave);
    }
}
