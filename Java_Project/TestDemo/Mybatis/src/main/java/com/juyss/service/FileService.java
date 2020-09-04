package com.juyss.service;

import com.juyss.pojo.File;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FileService
 * @Desc: file表的service接口层
 * @package com.juyss.service
 * @project TestDemo
 * @date 2020/8/25 22:38
 */
public interface FileService {

    /**
     * 获取所有数据
     *
     * @param sqlSession SqlSession 实例
     * @return List 信息集合
     */
    List<File> queryAll(SqlSession sqlSession);

    /**
     * 查询指定数据
     *
     * @param sqlSession SqlSession 实例
     * @param id         要查询的数据id
     * @return File 查询到的数据File实例
     */
    File queryFileById(SqlSession sqlSession, int id);

    /**
     * 添加一条数据
     *
     * @param sqlSession SqlSession 实例
     * @param file       要添加的File实例
     * @return Boolean 是否修改成功
     */
    Boolean insert(SqlSession sqlSession, File file);

    /**
     * 修改一条数据
     *
     * @param sqlSession SqlSession 实例
     * @param file       要修改的File实例
     * @return Boolean 是否修改成功
     */
    Boolean update(SqlSession sqlSession, File file);

    /**
     * 删除一条数据
     *
     * @param sqlSession SqlSession 实例
     * @param id         要删除的File的id
     * @return Boolean 是否修改成功
     */
    Boolean delete(SqlSession sqlSession, int id);
}
