package com.itheima.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.QuestionDao;
import com.itheima.domain.store.Question;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.QuestionService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zxq
 * @create 2020-08-28 17:19
 */
public class QuestionServiceImpl implements QuestionService {
    @Override
    public String save(Question question, boolean flag) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类对象
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);

            //初始设置
            //id:设置题目id
            String id = UUID.randomUUID().toString();
            question.setId(id);

            //state:设置新创建的题目的默认审核状态为未审核
            question.setReviewStatus("0");

            //createTime:设置创建时间为当前时间
            question.setCreateTime(new Date());

            //检测前端是否上传文件了，是则记录文件名，否则不记录
            if (flag) {
                //picture:设置当前存储的图片的名称为id
                question.setPicture(id);
            }

            //3.调用接口的方法
            questionDao.save(question);

            //4.提交事务
            TransactionUtil.commit(sqlSession);

            return id;

        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Question question) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);

            //3.调用接口的方法
            questionDao.delete(question);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Question question, boolean flag) {

        SqlSession sqlSession = null;

        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获得接口实现类
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);

            //检测前端是否上传文件了，是则记录文件名，否则不记录
            if (flag) {
                //picture:设置当前存储的图片的名称为id
                question.setPicture(question.getId());
            }

            //3.调用接口的方法
            questionDao.update(question);

            //4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //回滚事务
            TransactionUtil.rollback(sqlSession);
            //抛出异常，交给上级
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Question findById(String id) {
        SqlSession sqlSession = null;
        Question question = null;
        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口对象
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);

            //3.调用接口的方法
            question = questionDao.findById(id);

        } catch (Exception e) {
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return question;
    }

    @Override
    public List<Question> findAll() {
        List<Question> list = null;
        SqlSession sqlSession = null;
        try {
            //1.获取SqLSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);

            //3.调用接口的方法
            list = questionDao.findAll();


        } catch (Exception e) {
            //抛出异常，交给上级
            throw new RuntimeException(e);
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);

            //3.调用接口的方法
            PageHelper.startPage(page, size);
            List<Question> all = questionDao.findAll();
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;

        } catch (Exception e) {
            //抛出异常，交给上级
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public ByteArrayOutputStream getReport() throws IOException {

        //获取对应要展示的数据
        SqlSession sqlSession = null;
        List<Question> questionList = null;
        try {
            //1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();

            //2.获取接口实现类
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);

            //3.调用接口的方法
            questionList = questionDao.findAll();
        } catch (Exception e) {
            //抛出异常，交给上级
            throw new RuntimeException(e);
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //1.获取到对应的Excel文件，工作簿文件
        Workbook wb = new XSSFWorkbook();

        //2.创建工作表
        Sheet sheet = wb.createSheet("数据文件");

        //设置通用配置
        CellStyle cellStyle_field = wb.createCellStyle();
        cellStyle_field.setAlignment(HorizontalAlignment.CENTER);
        cellStyle_field.setBorderTop(BorderStyle.THIN);
        cellStyle_field.setBorderRight(BorderStyle.THIN);
        cellStyle_field.setBorderBottom(BorderStyle.THIN);
        cellStyle_field.setBorderLeft(BorderStyle.THIN);

        //3.制作标题
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 12));

        //单元格填值
        Row row_1 = sheet.createRow(1);
        Cell cell_1_1 = row_1.createCell(1);
        cell_1_1.setCellValue("在线试题导出信息");

        //水平垂直居中
        CellStyle cellStyle_title = wb.createCellStyle();
        cellStyle_title.setAlignment(HorizontalAlignment.CENTER);
        cellStyle_title.setVerticalAlignment(VerticalAlignment.CENTER);
        cell_1_1.setCellStyle(cellStyle_title);


        //4.制作表头
        String[] fields = {"题目ID", "所属公司ID", "所属目录ID", "题目简介", "题干描述", "题干配图",
                "题目分析", "题目类型", "题目难度", "是否经典题", "题目状态", "审核状态"};


        //单元格填值
        Row row_2 = sheet.createRow(2);

        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_temp = row_2.createCell(1 + i);
            cell_2_temp.setCellValue(fields[i]);

            //水平居中
            cell_2_temp.setCellStyle(cellStyle_field);
        }


        //5.制作数据区
        //单元格填值
        int row_index = 0;
        for (Question q : questionList) {
            Row row_temp = sheet.createRow(3 + row_index++);
            int cell_index = 0;

            Cell cell_data_1 = row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());
            cell_data_1.setCellStyle(cellStyle_field);

            Cell cell_data_2 = row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getCompanyId());
            cell_data_2.setCellStyle(cellStyle_field);

            Cell cell_data_3 = row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getCatalogId());
            cell_data_3.setCellStyle(cellStyle_field);

            Cell cell_data_4 = row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getRemark());
            cell_data_4.setCellStyle(cellStyle_field);

            Cell cell_data_5 = row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getSubject());
            cell_data_5.setCellStyle(cellStyle_field);

            Cell cell_data_6 = row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getPicture());
            cell_data_6.setCellStyle(cellStyle_field);

            Cell cell_data_7 = row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getAnalysis());
            cell_data_7.setCellStyle(cellStyle_field);

            Cell cell_data_8 = row_temp.createCell(1 + cell_index++);
            cell_data_8.setCellValue(q.getType());
            cell_data_8.setCellStyle(cellStyle_field);

            Cell cell_data_9 = row_temp.createCell(1 + cell_index++);
            cell_data_9.setCellValue(q.getDifficulty());
            cell_data_9.setCellStyle(cellStyle_field);

            Cell cell_data_10 = row_temp.createCell(1 + cell_index++);
            cell_data_10.setCellValue(q.getIsClassic());
            cell_data_10.setCellStyle(cellStyle_field);

            Cell cell_data_11 = row_temp.createCell(1 + cell_index++);
            cell_data_11.setCellValue(q.getState());
            cell_data_11.setCellStyle(cellStyle_field);

            Cell cell_data_12 = row_temp.createCell(1 + cell_index++);
            cell_data_12.setCellValue(q.getReviewStatus());
            cell_data_12.setCellStyle(cellStyle_field);
        }




        //将内存中的workbook数据写入流中
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        wb.close();
        return os;
    }

}
