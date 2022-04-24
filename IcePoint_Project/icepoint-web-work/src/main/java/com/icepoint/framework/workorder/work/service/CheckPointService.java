package com.icepoint.framework.workorder.work.service;


import com.icepoint.framework.web.system.entity.AssetDefine;
import com.icepoint.framework.workorder.work.entity.CheckPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @author Administrator
 */
public interface CheckPointService {
	/**
	 * 查询打卡点
	 * @param assetDefId 资产id
	 * @param objId 对象id
	 * @return
	 */
	CheckPoint findCheckPoints(Long assetDefId,Long objId);

	/**
	 * 打卡点列表查询
	 * @param checkPoint
	 * @param pageable
	 * @return
	 */
    Page<CheckPoint> pageList(CheckPoint checkPoint, Pageable pageable);

	/**
	 * 修改打卡点
	 * @param checkPoint
	 * @return
	 */
	Boolean updateCheckPoint(CheckPoint checkPoint);

	/**
	 * 删除打卡点
	 * @param id 被删除的打卡点id
	 * @return
	 */
	Boolean deleteCheckPoint(Long id);

	/**
	 * 新增打卡点
	 * @param checkPoint 打卡点实体
	 * @return
	 */
	Boolean addCheckPoint(CheckPoint checkPoint);

	/**
	 * 查询未绑定打卡点的资产
	 * @return
	 */
	List<AssetDefine> queryCheckPointByAsset();

	/**
	 * 通过资产id 查询打卡点
	 * @param assetId
	 * @return
	 */
	List<CheckPoint> queryCheckPointByAssetId(Long assetId);
}
