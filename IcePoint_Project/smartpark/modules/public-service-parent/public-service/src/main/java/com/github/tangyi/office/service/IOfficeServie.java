package com.github.tangyi.office.service;

import com.github.tangyi.pub.api.dto.office.MoveIdxDto;
import com.github.tangyi.pub.api.module.office.SysCntLink;
import java.util.List;

/**
 * @author ck
 */
public interface IOfficeServie {
    List<SysCntLink> officeList();

    List<SysCntLink> getLinkById(Long id, String name);

    Integer updateBySort( Long id, String name, Integer idx);

    Integer deleteTree( Long id);

    Integer updateByid(Long id, SysCntLink sysCntLink);

    Integer addNode(SysCntLink sysCntLink);

    SysCntLink selectById(Long id);

    Boolean moveIdx(MoveIdxDto moveIdxDto);

    List<SysCntLink> queryAllList();
}
