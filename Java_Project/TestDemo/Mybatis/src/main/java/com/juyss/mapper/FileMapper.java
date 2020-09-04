package com.juyss.mapper;

import com.juyss.pojo.File;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FileMapper
 * @Desc: File类数据库操作对象接口
 * @package com.juyss.mapper
 * @project TestDemo
 * @date 2020/8/25 22:21
 */
public interface FileMapper {

    /**
     * 获取所有文件信息
     *
     * @return List 所有文件信息的集合
     */
    List<File> getAllFile();

    /**
     * 获取指定id的File实例
     *
     * @param id Integer 目标文件id
     * @return File 目标文件实例
     */
    File getFileById(int id);

    /**
     * 添加一条数据
     *
     * @param file File 要添加的File对象
     * @return int 受影响的行数
     */
    int insertFile(File file);

    /**
     * 删除指定数据
     *
     * @param id 要删除的数据的id
     * @return int 受影响的行数
     */
    int deleteFileById(int id);

    /**
     * 更新指定数据
     *
     * @param file 更新的信息承载类
     * @return int 受影响的行数
     */
    int updateFile(File file);
}
