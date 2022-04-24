package com.icepoint.base.web.sys.mapper;

import com.icepoint.base.web.basic.repository.MybatisMapper;
import com.icepoint.base.web.sys.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper extends MybatisMapper<File, Long> {


    void saveByMybatis(File file);

    List<String> getFileList(List<String> docNoList);

    String getOriginalName(String fileName);
}
