package com.icepoint.base.web.sys.service;

import com.icepoint.base.api.vo.ResourceWithFilename;
import com.icepoint.base.web.basic.service.CrudService;
import com.icepoint.base.web.sys.entity.File;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS)
public interface FileService extends CrudService<File, Long> {

    File storeFile(MultipartFile file, String version);

    ResourceWithFilename loadFileAsResource(String fileName);

    List<String> getFileList(List<String> docNoList);
}
