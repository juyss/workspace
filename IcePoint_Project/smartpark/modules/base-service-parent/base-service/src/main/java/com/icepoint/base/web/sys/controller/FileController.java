package com.icepoint.base.web.sys.controller;

import com.github.tangyi.common.core.exceptions.ServiceException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.icepoint.base.api.vo.ResourceWithFilename;
import com.icepoint.base.web.basic.controller.CrudController;
import com.icepoint.base.web.resource.util.ZipUtils;
import com.icepoint.base.web.sys.entity.File;
import com.icepoint.base.web.sys.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

@AllArgsConstructor
@RestController
@RequestMapping("sys/file")
public class FileController extends CrudController<FileService, File, Long> {

    private final FileService fileService;

    @CrossOrigin
    @PostMapping("/uploadFile")
    public ResponseBean<File> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value = "version", required = false) String version) {
        return new ResponseBean<>(fileService.storeFile(file, version));
    }


    /*@PostMapping("/uploadMultipleFiles")
    public List<ResponseBean<File>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }*/

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws UnsupportedEncodingException {

        ResourceWithFilename resource = fileService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getResource().getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new ServiceException(ex.getMessage());
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(resource.getFilename().getBytes("gb2312"), "ISO8859-1") + "\"")
                .body(resource.getResource());
    }

/*  该接口不再提供，而是将key拆开，分别独立提供。
    @GetMapping(value = "{key}/zip")
    public void downLoadZipFile(@PathVariable("key") String key, @RequestParam("docNo") List<String> docNoList, HttpServletResponse response) throws IOException {
        String zipName = "file.zip";
        List<String> fileList = fileService.getFileList(docNoList);//查询数据库中记录
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for (String file : fileList) {
                ZipUtils.doCompress(file, out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }*/

}
