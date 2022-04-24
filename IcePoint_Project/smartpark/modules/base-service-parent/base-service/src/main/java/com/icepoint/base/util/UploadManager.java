package com.icepoint.base.util;


import com.icepoint.base.bean.ItemFile;
import com.icepoint.base.bean.ItemPicture;
import com.icepoint.base.bean.StaticParam;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class UploadManager {

    public static String uploadMP3(MultipartFile file, String path, ItemFile itemFile) {
        if (file.getSize() == 0)
            return "";
        itemFile.setDuration(FileUtils.getMP3TrackLength(uploadFile(file, path, itemFile)));
        return itemFile.getFileName();
    }

    public static String uploadVideo(MultipartFile file, String path, ItemFile itemFile) {
        if (file.getSize() == 0)
            return "";
        uploadFile(file, path, itemFile);
        return itemFile.getFileName();
    }

    public static File uploadFile(MultipartFile file, String path, ItemFile itemFile) {
        String root = StaticParam.FILE_BASE_SOURCE + path + getPath();
        String fileName = file.getOriginalFilename();
        String newFileName = FileUtils.randomFileName() + FileUtils.getFileType(fileName);

        itemFile.setSource(root);
        itemFile.setSourceFileName(fileName);
        itemFile.setFileName(newFileName);
        itemFile.setFileType(FileUtils.getFileType(fileName));
        itemFile.setUrl(path + getPath() + newFileName);

        File targetFile = new File(root, newFileName);

        if (!targetFile.exists())
            targetFile.mkdirs();

        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetFile;
    }

    public static List<ItemPicture> uploadPic(MultipartFile[] files, String path, Long userId) {
        String newFileName = "";
        String root = StaticParam.FILE_BASE_SOURCE + path + getPath();
        if (files.length == 0)
            return null;
        List<ItemPicture> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file.isEmpty()) {
                continue;
            }
            ItemPicture pic = new ItemPicture();
            pic.setCreateUser(userId);
            pic.setSource(root);
            String fileName = file.getOriginalFilename();
            pic.setSourceFileName(fileName);
            newFileName = FileUtils.randomFileName() + FileUtils.getFileType(fileName);
            pic.setFileName(newFileName);
            if (i == 0)
                pic.setPicType(StaticParam.IMG_TYPE_COVER);
            else
                pic.setPicType(StaticParam.IMG_TYPE_NORMAL);
            pic.setUrl(path + getPath() + newFileName);
            File targetFile = new File(root, newFileName);

            if (!targetFile.exists())
                targetFile.mkdirs();

            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(pic);
        }
        return list;
    }


    public static List<com.icepoint.base.web.sys.entity.File> uploadFileLogs(MultipartFile[] files, String path, Long userId) {
        String newFileName = "";
        String root = StaticParam.FILE_BASE_SOURCE + path + getPath();
        if (files.length == 0)
            return null;
        List<com.icepoint.base.web.sys.entity.File> list = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            com.icepoint.base.web.sys.entity.File pic = new com.icepoint.base.web.sys.entity.File();
            pic.setCreateUser(userId);
            //pic.setFileKey(root);
            String fileName = file.getOriginalFilename();
            pic.setName(fileName);
            newFileName = FileUtils.randomFileName() + FileUtils.getFileType(fileName);
            pic.setFileName(newFileName);
    		/*if(i == 0)
    			pic.setPicType(StaticParam.IMG_TYPE_COVER);
    		else
    			pic.setPicType(StaticParam.IMG_TYPE_NORMAL);*/
            pic.setFileKey(path + getPath() + newFileName);
            File targetFile = new File(root, newFileName);

            if (!targetFile.exists())
                targetFile.mkdirs();

            try {
                file.transferTo(targetFile);
                MP3File f = (MP3File) AudioFileIO.read(targetFile);
                MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
                pic.setDuration(audioHeader.getTrackLength() * 60);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //pic.setDurationStr(FilesUtil.getMP3TrackLength(targetFile.getAbsolutePath()));
            list.add(pic);
        }
        return list;
    }

    public static boolean uploadPic(MultipartFile file, String path, Map<String, Object> map, ItemPicture picture) throws IOException {
        InputStream in = PictureUtils.resizeCover(file.getInputStream(), map);
        return upload(in, path, file.getOriginalFilename(), picture);
    }

    public static boolean upload(InputStream input, String path, String fileName, ItemPicture pic) throws IOException {
        boolean flag = false;

        String newFileName = FileUtils.randomFileName() + FileUtils.getFileType(fileName);
        pic.setUrl(path + getPath() + newFileName);
        pic.setSourceFileName(fileName);
        pic.setFileName(newFileName);
        path = StaticParam.FILE_BASE_SOURCE + path + getPath();
        pic.setSource(path);
        pic.setPicType(StaticParam.IMG_TYPE_COVER);
        File newFile = new File(path, newFileName);
        String dir = newFile.getParentFile().getPath();
        File folder = new File(dir);
        if (!folder.isDirectory())
            flag = folder.mkdirs();

        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            fos.write(IOUtils.toByteArray(input));
        }
        return flag;
    }

    public static String getPath() {
        Calendar a = Calendar.getInstance();
        return "/" + a.get(Calendar.YEAR) + "/" + (a.get(Calendar.MONTH) + 1) + "/";
    }

}
