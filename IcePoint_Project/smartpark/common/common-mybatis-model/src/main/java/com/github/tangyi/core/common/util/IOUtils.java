package com.github.tangyi.core.common.util;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PatternMatchUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * IO工具类
 *
 * @author hedongzhou
 */
public class IOUtils extends org.apache.commons.io.IOUtils {

    /**
     * 资源解析
     */
    public final static PathMatchingResourcePatternResolver RESOLVER = new PathMatchingResourcePatternResolver();

    /**
     * 路径匹配
     *
     * @param pattern
     * @param path
     * @return
     */
    public static boolean match(String pattern, String path) {
        return PatternMatchUtils.simpleMatch(pattern, path);
    }

    /**
     * 路径匹配
     *
     * @param pattern
     * @param path
     * @return
     */
    public static boolean match(String[] pattern, String path) {
        return PatternMatchUtils.simpleMatch(pattern, path);
    }

    /**
     * 获取classpath下的配置信息
     *
     * @param pathPattern
     * @return
     * @throws IOException
     */
    public static Properties loadProperty(String pathPattern) throws IOException {
        Resource resource = loadResource(pathPattern);

        if (isExist(resource)) {
            InputStream is = null;
            try {
                is = resource.getInputStream();
                Properties pro = new Properties();
                pro.load(is);
                return pro;
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        return new Properties();
    }

    /**
     * 判断资源是否存在
     *
     * @param resource
     * @return
     */
    public static boolean isExist(Resource resource) {
        if (resource == null || !resource.exists()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取classpath下的一个资源
     *
     * @param pathPattern
     * @return
     */
    public static Resource loadResource(String pathPattern) {
        Resource[] resources = loadResources(pathPattern);

        if (CollectionUtils.isNotEmpty(resources)) {
            return resources[0];
        } else {
            return null;
        }
    }

    /**
     * 获取classpath下的资源
     *
     * @param pathPatterns
     * @return
     */
    public static Resource[] loadResources(Collection<String> pathPatterns) {
        return loadResources(CollectionUtils.toArray(pathPatterns, String.class));
    }

    /**
     * 获取classpath下的资源
     *
     * @param pathPatterns
     * @return
     */
    public static Resource[] loadResources(String... pathPatterns) {
        if (CollectionUtils.isNotEmpty(pathPatterns)) {
            List<Resource> list = new ArrayList<>(pathPatterns.length);
            try {
                for (String pathPattern : pathPatterns) {
                    Resource[] arr = RESOLVER.getResources(pathPattern);

                    for (Resource one : arr) {
                        if (isExist(one)) {
                            list.add(one);
                        }
                    }
                }
            } catch (Exception e) {
            }

            if (list.size() > 0) {
                return list.toArray(new Resource[0]);
            }
        }

        return new Resource[0];
    }

    /**
     * 下载文件
     *
     * @param url
     * @param file
     * @throws Exception
     */
    public static void downloadFile(String url, File file) throws Exception {
        FileUtils.copyURLToFile(new URL(url), file);
    }

    /**
     * 根据文件流判断图片类型
     *
     * @param is
     * @return jpg/png/gif/bmp
     */
    public static String getImgType(InputStream is) {
        byte[] b = new byte[4];
        try {
            is.read(b, 0, b.length);
            String type = StringUtils.bytesToHexString(b).toUpperCase();
            if (type.contains("FFD8FF")
                    || type.contains("52494646")) {
                return "jpg";
            } else if (type.contains("89504E47")) {
                return "png";
            } else if (type.contains("47494638")) {
                return "gif";
            } else if (type.contains("424D")) {
                return "bmp";
            } else {
                return null;
            }
        } catch (Exception e) {
            LogUtils.error(e);
        }
        return null;
    }

    /**
     * 获取文档，若不存在，直接创建
     *
     * @param filePath
     * @return
     */
    public static File getDirAndMake(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取路径
     *
     * @param path
     * @return
     */
    public static String getPath(String... path) {
        return StringUtils.join(path, File.separator);
    }

    /**
     * 复制
     *
     * @param buffer
     * @param out
     * @return
     * @throws Exception
     */
    public static int copy(byte[] buffer,
                           OutputStream out) throws Exception {
        try (InputStream in = new ByteArrayInputStream(buffer)) {
            return copy(in, out);
        }
    }

}
