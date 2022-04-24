package com.github.tangyi.webcast.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

    private FileUtil() {
    }

    public static File multipartFileToFile(MultipartFile multipartFile) {
        int n;
        // 创建文件
        String tempDir = "/home/park/attach/tempDir";
        File tempDirFile = new File(tempDir);
        if (!tempDirFile.exists()) {
            tempDirFile.mkdirs();
        }
        File f = new File(tempDirFile, multipartFile.getOriginalFilename());

        try (InputStream in = multipartFile.getInputStream(); OutputStream os = new FileOutputStream(f)) {
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            while ((n = in.read(buffer, 0, 4096)) != -1) {
                os.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    public static MultipartFile fileToMultipartFile(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), "text/plain", IOUtils.toByteArray(input));
        if (input == null) {
            input.close();
        }
        return multipartFile;
    }


}
