package com.github.tangyi.file.util;

import com.liumapp.workable.converter.WorkableConverter;
import com.liumapp.workable.converter.core.ConvertPattern;
import com.liumapp.workable.converter.exceptions.ConvertFailedException;
import com.liumapp.workable.converter.factory.CommonConverterManager;
import com.liumapp.workable.converter.factory.ConvertPatternManager;
import org.jodconverter.document.DefaultDocumentFormatRegistry;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * word文档转为pdf
 */
public class WordToPdfUtils {
    public static void wordToPdf(String wordAddress,String pdfAddress) throws IOException, ConvertFailedException {
        WorkableConverter converter = new WorkableConverter();
        ConvertPattern pattern = ConvertPatternManager.getInstance();
        //得到原文件的名字
         File file = new File(wordAddress);
       // String fileName = file.getName();
        //去掉后缀
       // String tempFilePath = fileName.replaceAll("[.][^.]+$", "");
        pattern.streamToStream(new FileInputStream(wordAddress), new FileOutputStream(pdfAddress));
        pattern.setSrcFilePrefix(DefaultDocumentFormatRegistry.DOCX);
        pattern.setDestFilePrefix(DefaultDocumentFormatRegistry.PDF);
        converter.setConverterType(CommonConverterManager.getInstance());
        boolean result = converter.convert(pattern.getParameter());
    }


}
