package com.github.tangyi.core.common.util;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.req.BaseReq;
import com.github.tangyi.model.TrainCourse;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel工具
 *
 * @author hedongzhou
 * @since 2018/08/15
 */
public class ExcelUtils {

    /**
     * Excel模板文件
     */
    public static final String TEMPLATE_FILE = "templates/excel/%s.xlsx";

    /**
     * 获取模板
     *
     * @param template
     * @return
     * @throws Exception
     */
    public static InputStream getTemplate(String template) throws Exception {
        return IOUtils.loadResource(String.format(TEMPLATE_FILE, template)).getInputStream();
    }

    /**
     * 获取转换器
     *
     * @param template
     * @param os
     * @param <T>
     * @return
     */
    public static <T extends Transformer> T getTransformer(InputStream template,
                                                           OutputStream os) {
        return (T) JxlsHelper.getInstance().createTransformer(template, os);
    }

    /**
     * 导出
     *
     * @param template 模板名称
     * @param data     数据
     * @param os       输出
     * @throws Exception
     */
    public static void export(String template,
                              Object data,
                              OutputStream os) throws Exception {
        try (InputStream is = getTemplate(template)) {
            export(is, data, os);
        }
    }

    /**
     * 导出
     *
     * @param template 模板名称
     * @param data     数据
     * @param os       输出
     * @throws Exception
     */
    public static void export(InputStream template,
                              Object data,
                              OutputStream os) throws Exception {
        export(getTransformer(template, os), data);
    }

    /**
     * 导出
     *
     * @param transformer
     * @param data
     * @throws Exception
     */
    public static void export(Transformer transformer,
                              Object data) throws Exception {
        Context context = new Context();
        context.putVar("data", data);
        JxlsHelper.getInstance().processTemplate(context, transformer);
    }

    /**
     * 导出excel文件 到response
     *
     * @param data
     * @param filename
     * @param response
     */
    public static void export(Map<String, Object> data, String filename, String tmplate, HttpServletResponse response) {

        try {
            OutputStream os = response.getOutputStream();
            Throwable var6 = null;

            try {
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                response.addHeader("Content-Disposition", "attachment;filename=" + encodeFilename(filename) + ".xlsx");
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                ExcelUtils.export(tmplate, data, os);
                os.flush();
            } catch (Throwable t) {
                var6 = t;
                throw t;
            } finally {
                if (os != null) {
                    if (var6 != null) {
                        try {
                            os.close();
                        } catch (Throwable var15) {
                            var6.addSuppressed(var15);
                        }
                    } else {
                        os.close();
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String encodeFilename(String filename) {
        try {
            return java.net.URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "export_excel";
    }
//    public static void parseExcel(String xmlConfigName, MultipartFile file, Map<String, Object> beans) {
//        InputStream inputStream = null;
//        InputStream xmlConfig = null;
//        InputStream inputXML = null;
//        InputStream inputXLS = null;
//        try {
//            //上传的文件流
//            inputStream = file.getInputStream();
//            //xml配置的文件流
//            xmlConfig = IOUtils.loadResource(xmlConfigName).getInputStream();
//            //执行解析
//            inputXML = new BufferedInputStream(xmlConfig);
//            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
//            inputXLS = new BufferedInputStream(inputStream);
//            mainReader.read(inputXLS, beans);
//        } catch (XLSDataReadException e) {
//            LogUtils.error("数据转换异常:", e);
//            throw new BusinessException("数据转换异常");
//        } catch (InvalidFormatException e) {
//            LogUtils.error("无效格式异常:", e);
//            throw new BusinessException("无效格式异常");
//        } catch (FileNotFoundException e) {
//            LogUtils.error("文件未找到:", e);
//            throw new BusinessException("文件未找到");
//        } catch (SAXException e) {
//            LogUtils.error("SAX解析异常:", e);
//            throw new BusinessException("SAX解析异常");
//        } catch (IOException e) {
//            LogUtils.error("IO流异常:", e);
//            throw new BusinessException("IO流异常");
//        } finally {
//            try {
//                if (inputXLS != null)
//                    inputXLS.close();
//                if (inputXML != null)
//                    inputXML.close();
//                if (xmlConfig != null)
//                    xmlConfig.close();
//                if (inputStream != null)
//                    inputStream.close();
//            } catch (IOException e) {
//                LogUtils.error("parse excel error", e);
//            }
//        }
//    }

}
