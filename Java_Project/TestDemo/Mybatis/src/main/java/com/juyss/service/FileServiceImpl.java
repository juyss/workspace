package com.juyss.service;

import com.juyss.mapper.FileMapper;
import com.juyss.pojo.File;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: FileServiceImpl
 * @Desc: FileService实现类
 * @package com.juyss.service
 * @project TestDemo
 * @date 2020/8/25 22:39
 */
public class FileServiceImpl implements FileService {

    /**
     * 获取所有数据
     *
     * @param sqlSession SqlSession 实例
     * @return List 信息集合
     */
    @Override
    public List<File> queryAll(SqlSession sqlSession) {
        List<File> fileList = null;
        try {
            FileMapper mapper = sqlSession.getMapper(FileMapper.class);
            fileList = mapper.getAllFile();
        } finally {
            sqlSession.close();
            return fileList;
        }
    }

    /**
     * 查询指定数据
     *
     * @param sqlSession SqlSession 实例
     * @param id         要查询的数据id
     * @return File 查询到的数据File实例
     */
    @Override
    public File queryFileById(SqlSession sqlSession, int id) {
        File file = null;
        try {
            FileMapper mapper = sqlSession.getMapper(FileMapper.class);
            file = mapper.getFileById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
            return file;
        }
    }

    /**
     * 添加一条数据
     *
     * @param sqlSession SqlSession 实例
     * @param file       要添加的File实例
     * @return Boolean 是否修改成功
     */
    @Override
    public Boolean insert(SqlSession sqlSession, File file) {
        Boolean flag = false;
        try {
            FileMapper mapper = sqlSession.getMapper(FileMapper.class);
            int i = mapper.insertFile(file);
            if (i==1){
                System.out.println("插入数据成功!");
                sqlSession.commit();
                flag = true;
            }else{
                System.out.println("插入数据失败!");
                sqlSession.rollback();
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
            return flag;
        }
    }

    /**
     * 修改一条数据
     *
     * @param sqlSession SqlSession 实例
     * @param file       要修改的File实例
     * @return Boolean 是否修改成功
     */
    @Override
    public Boolean update(SqlSession sqlSession, File file) {
        Boolean flag = false;
        try {
            FileMapper mapper = sqlSession.getMapper(FileMapper.class);
            int i = mapper.updateFile(file);
            if (i==1){
                System.out.println("修改数据成功!");
                sqlSession.commit();
                flag = true;
            }else{
                System.out.println("修改数据失败!");
                sqlSession.rollback();
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
            return flag;
        }
    }

    /**
     * 删除一条数据
     *
     * @param sqlSession SqlSession 实例
     * @param id         要删除的File的id
     * @return Boolean 是否修改成功
     */
    @Override
    public Boolean delete(SqlSession sqlSession, int id) {
        Boolean flag = false;
        try {
            FileMapper mapper = sqlSession.getMapper(FileMapper.class);
            int i = mapper.deleteFileById(id);
            if (i==1){
                System.out.println("删除数据成功!");
                sqlSession.commit();
                flag = true;
            }else{
                System.out.println("删除数据失败!");
                sqlSession.rollback();
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
            return flag;
        }
    }
}
