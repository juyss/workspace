package com.juyss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juyss.common.utils.DataResult;
import com.juyss.entity.SysFilesEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: SysFilesService
 * @Desc: 文件上传服务
 * @package com.juyss.service
 * @project manager
 * @date 2021/1/20 16:35
 */
public interface SysFilesService extends IService<SysFilesEntity> {

    DataResult saveFile(MultipartFile file);

    void removeByIdsAndFiles(List<String> ids);
}

