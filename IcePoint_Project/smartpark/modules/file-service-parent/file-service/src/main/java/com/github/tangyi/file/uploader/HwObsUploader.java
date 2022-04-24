package com.github.tangyi.file.uploader;

import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.file.api.model.Attachment;
import com.github.tangyi.oss.service.HwObsService;

import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import com.liumapp.workable.converter.WorkableConverter;
import com.liumapp.workable.converter.core.ConvertPattern;
import com.liumapp.workable.converter.factory.CommonConverterManager;
import com.liumapp.workable.converter.factory.ConvertPatternManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.springframework.stereotype.Service;

/**
 * 上传到华为obs云存储
 *
 * @author gaokx
 */
@Slf4j
@Service
public class HwObsUploader extends AbstractUploader {

    private HwObsService hwObsService;

    public HwObsUploader(HwObsService hwObsService) {
        this.hwObsService = hwObsService;
    }

    /**
     * 这个地方用的字节数组  如果文件过大 会oom 暂时不改
     *
     * @param attachment
     * @param bytes
     * @return
     */
    @Override
    public Attachment upload(Attachment attachment, byte[] bytes) {
        String oldName = attachment.getAttachName().replaceAll(" ", "");
        //处理路径符号
        attachNameHandle(attachment);
        //上传至华为obs 返回地址
        String result = hwObsService.upload(bytes, attachment.getAttachName());
        //设置文件上传状态
        attachment.setUploadResult(result);
        //获取文件后缀名
        String tempFilenameSuffix = attachment.getAttachName().substring(attachment.getAttachName().lastIndexOf(".") + 1);
        //如果上传的是一个pdf或者是其他类型  就不用转换 上传类型
        List<String> list = new ArrayList();
        list.add("doc");
        list.add("docx");
        list.add("xls");
        list.add("xlsx");
        list.add("ppt");
        list.add("pptx");
        boolean b = list.stream().anyMatch(o -> o.equals(tempFilenameSuffix));
        if (b) {

            StringBuilder baseFileRealDirectory = new StringBuilder(SpringContextHolder.getApplicationContext().getBean(SysProperties.class).getAttachPath())
                    .append(File.separator)
                    .append(SysUtil.getSysCode())
                    .append(File.separator)
                    .append("pdf-temp")
                    .append(oldName);
            File file = new File(baseFileRealDirectory.toString());
            try {
                FileUtils.writeByteArrayToFile(file, bytes, true);
                log.info("临时文件生成成功");
            } catch (IOException e) {
                e.printStackTrace();
                log.error("生成临时文件失败");
            }
            String absolutePath = file.getAbsolutePath();
            //去掉后缀
            String pdfFilePath = absolutePath.replaceAll("[.][^.]+$", "");
            oldName = oldName.replaceAll("[.][^.]+$", "") + ".pdf";
            //修改后缀为pdf格式
            pdfFilePath += ".pdf";
            //转pdf
            String pdfadds = wordToPdf(absolutePath, pdfFilePath);
            log.info("pdf地址为 {}", pdfadds);
            //得到临时pdf文件
            File pdfFile = new File(pdfadds);
            //上传至华为obs
            try {
                byte[] pdfBytes = FileUtils.readFileToByteArray(pdfFile);
                String upload = hwObsService.upload(pdfBytes, oldName);
                attachment.setPreviewUrl(upload);
                //删除临时文件
                pdfFile.delete();
                file.delete();
                log.info(upload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            attachment.setPreviewUrl(result);
            attachment.setPreviewUrlSource(result);
        }
        try {
            attachment.setPreviewUrlSource(java.net.URLDecoder.decode(attachment.getUploadResult(), "UTF-8"));
            //https替换为http
            attachment.setPreviewUrlSource(attachment.getPreviewUrlSource().replace("https", "http"));
            attachment.setPreviewUrlSource(attachment.getPreviewUrlSource().replace(":443", ""));

            if (org.springframework.util.StringUtils.isEmpty(attachment.getPreviewUrl())) {
                attachment.setPreviewUrl(attachment.getPreviewUrlSource());
            } else {
                attachment.setPreviewUrl(attachment.getPreviewUrl().replace("https", "http"));
                attachment.setPreviewUrl(attachment.getPreviewUrl().replace(":443", ""));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    @Override
    public Attachment uploadFile(Attachment attachment, File file) {
        attachNameHandle(attachment);
        String s = hwObsService.uloadFile(file, attachment.getAttachName());
        attachment.setPreviewUrl(s);
        return attachment;
    }

    @Override
    public InputStream download(Attachment attachment) {
        return hwObsService.downloadFile(attachment.getAttachName());
    }

    @Override
    public boolean delete(Attachment attachment) {
        return hwObsService.delete(attachment.getAttachName());
    }

    @Override
    public boolean deleteAll(Attachment attachment) {
        return false;
    }

    private void attachNameHandle(Attachment attachment) {
        String name = "";
        if (StringUtils.isNotBlank(attachment.getBusiModule())) {
            name = attachment.getBusiModule() + "/";
        }
        if (StringUtils.isNotBlank(attachment.getAttachType())) {
            name += attachment.getAttachType() + "/";
        }
        name += attachment.getAttachName();
        attachment.setAttachName(name);
    }

    /**
     * word转pdf
     *
     * @param wordAddress
     * @param pdfAddress
     * @return pdf地址
     */

    public String wordToPdf(String wordAddress, String pdfAddress) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(wordAddress);
            fileOutputStream = new FileOutputStream(pdfAddress);
            log.info("word转pdf开始");
            WorkableConverter converter = new WorkableConverter();
            ConvertPattern pattern = ConvertPatternManager.getInstance();
            pattern.streamToStream(fileInputStream, fileOutputStream);
            pattern.setSrcFilePrefix(DefaultDocumentFormatRegistry.DOCX);
            pattern.setDestFilePrefix(DefaultDocumentFormatRegistry.PDF);
            converter.setConverterType(CommonConverterManager.getInstance());
            boolean result = converter.convert(pattern.getParameter());
            //转换完成
            log.info("pdf转换完后");
            //根据路径和名字得到文件路径
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return pdfAddress;
    }

}
