package com.icepoint.base.web.sys.service.impl;

import com.icepoint.base.api.vo.ResourceWithFilename;
import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.sys.entity.File;
import com.icepoint.base.web.sys.excpetion.FileException;
import com.icepoint.base.web.sys.mapper.FileMapper;
import com.icepoint.base.web.sys.properties.FileProperties;
import com.icepoint.base.web.sys.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl extends AntdPageService<FileMapper, File, Long> implements FileService {

    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public FileServiceImpl(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * 存储文件到系统
     *
     * @param file    文件
     * @param version
     * @return 文件名
     */
    @Override
    public File storeFile(MultipartFile file, String version) {
        File result = new File();
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); 因为重复的名称文件保存时会被覆盖，不能使用原始名称
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            result.setName(file.getOriginalFilename());
            result.setFileName(fileName);// 唯一名称
            getRepository().saveByMybatis(result);

            return result;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public ResourceWithFilename loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return new ResourceWithFilename(resource, getRepository().getOriginalName(fileName) + fileName.substring(fileName.lastIndexOf(".")));
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }

    @Override
    public List<String> getFileList(List<String> docNoList) {
        List<String> fileList = getRepository().getFileList(docNoList);
        return fileList.stream().filter(file -> file != null && !file.isEmpty()).map(file -> this.fileStorageLocation.resolve(file).normalize().toString()).collect(Collectors.toList());
    }

}