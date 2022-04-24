package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.system.entity.AssetDefine;
import org.springframework.stereotype.Repository;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-07-21 21:27
 */
@Repository
public interface AssetRepository extends LongStdRepository<AssetDefine> {
}
