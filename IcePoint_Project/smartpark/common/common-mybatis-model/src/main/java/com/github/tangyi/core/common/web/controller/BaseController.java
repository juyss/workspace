package com.github.tangyi.core.common.web.controller;

import com.github.tangyi.core.common.util.CoderUtils;
import com.github.tangyi.core.common.util.IOUtils;
import com.github.tangyi.core.common.util.LogUtils;
import com.github.tangyi.core.common.web.BaseResultCode;
import com.github.tangyi.core.common.web.ResultBean;
import com.github.tangyi.core.common.web.ResultCode;
import com.github.tangyi.core.common.context.ApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 基础controller
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public abstract class BaseController {

    /**
     * 返回成功结果
     *
     * @return
     */
    protected ResultBean returnSuccess() {
        return returnSuccess(null, null);
    }

    /**
     * 返回失败
     *
     * @return
     */
    protected ResultBean returnFailed() {
        return returnResult(BaseResultCode.failure, null, null);
    }


    /**
     * @param failedContext  失败方案描述
     * @return 失败数据
     */
    protected ResultBean returnFailed(String failedContext) {
        return new ResultBean(BaseResultCode.failure.getCode(),
                failedContext,
                failedContext,
                null,
                true);
    }

    /**
     * 返回成功结果
     *
     * @param obj
     * @return
     */
    protected ResultBean returnSuccess(Object obj) {
        return returnSuccess(obj, null);
    }

    /**
     * 返回成功结果，defaultData为Map
     *
     * @param data
     * @return
     */
    protected ResultBean returnSuccessObject(Object data) {
        return returnSuccess(data, new Object());
    }

    /**
     * 返回成功结果，defaultData为Array
     *
     * @param data
     * @return
     */
    protected ResultBean returnSuccessArray(Object data) {
        return returnSuccess(data, new Object[0]);
    }

    /**
     * 返回成功结果，defaultData为0
     *
     * @param data
     * @return
     */
    protected ResultBean returnSuccessInt(Object data) {
        return returnSuccess(data, 0);
    }

    /**
     * 返回成功结果，defaultData为false
     *
     * @param data
     * @return
     */
    protected ResultBean returnSuccessBoolean(Object data) {
        return returnSuccess(data, false);
    }

    /**
     * 返回成功结果，defaultData为空字符串
     *
     * @param data
     * @return
     */
    protected ResultBean returnSuccessString(Object data) {
        return returnSuccess(data, "");
    }

    /**
     * 返回成功结果，defaultData为0
     *
     * @param data
     * @return
     */
    protected ResultBean returnSuccessLong(Object data) {
        return returnSuccess(data, 0);
    }

    /**
     * 返回成功结果
     *
     * @param data
     * @param defaultData
     * @return
     */
    protected ResultBean returnSuccess(Object data,
                                       Object defaultData) {
        return returnResult(BaseResultCode.success,
                data,
                defaultData);
    }

    /**
     * 返回结果
     *
     * @param resultCode
     * @param data
     * @param defaultData
     * @param args
     * @return
     */
    protected ResultBean returnResult(ResultCode resultCode,
                                      Object data,
                                      Object defaultData,
                                      String... args) {
        return new ResultBean(resultCode.getCode(),
                ApplicationContext.getMessage(resultCode.getMessage(), args),
                data,
                defaultData,
                true);
    }

    /**
     * 导出Excel
     *
     * @param template
     * @param data
     */
//    protected void exportExcel(String template,
//                               Object data) {
//        exportExcel(template, data, DateUtils.formatDate(new Date()) + ".xlsx");
//    }

    /**
     * 导出Excel
     *
     * @param template
     * @param data
     * @param fileName
     */
//    protected void exportExcel(String template,
//                               Object data,
//                               String fileName) {
//        HttpServletResponse response = ApplicationContext.getResponse();
//        try (OutputStream os = response.getOutputStream()) {
//            response.addHeader("Content-Disposition", "attachment;filename="
//                    + CoderUtils.encode(fileName));
//            response.setContentType("application/octet-stream");
//
//            ExcelUtils.export(template, data, os);
//            os.flush();
//        } catch (Exception e) {
//            LogUtils.error(e);
//        }
//    }

    /**
     * 下载文件
     *
     * @param file
     */
    protected void downloadFile(File file) {
        if (file == null) {
            return;
        }

        downloadFile(file, file.getName());
    }

    /**
     * 下载文件
     *
     * @param file
     * @param fileName
     */
    protected void downloadFile(File file,
                                String fileName) {
        if (file == null) {
            return;
        }

        try (FileInputStream in = new FileInputStream(file)) {
            downloadFile(in, fileName);
        } catch (Exception e) {
            LogUtils.error(e);
        }
    }

    /**
     * 下载文件
     *
     * @param in
     * @param fileName
     */
    protected void downloadFile(InputStream in,
                                String fileName) {
        HttpServletResponse response = ApplicationContext.getResponse();
        response.addHeader("Content-Disposition", "attachment;filename="
                + CoderUtils.encode(fileName));
        response.setContentType("application/octet-stream;charset=UTF-8");

        try (OutputStream os = response.getOutputStream()) {
            IOUtils.copy(in, os);
            os.flush();
        } catch (Exception e) {
            LogUtils.error(e);
        }
    }

}
