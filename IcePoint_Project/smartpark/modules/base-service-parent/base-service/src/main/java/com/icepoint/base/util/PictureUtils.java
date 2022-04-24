package com.icepoint.base.util;

import com.icepoint.base.api.util.Maths;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

//import org.springframework.web.multipart.MultipartFile;

public class PictureUtils {

    public static InputStream resizeCover(InputStream in, Map<String, Object> map) {
        int x = Maths.halfUp(map.get("x").toString());
        int y = Maths.halfUp(map.get("y").toString());
        int w = Maths.halfUp(map.get("width").toString());
        int h = Maths.halfUp(map.get("height").toString());
        return cutImg(in, x, y, w, h);
    }

    public static InputStream cutImg(InputStream in, int x, int y, int w, int h) {
        InputStream result = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buff = IOUtils.toByteArray(in);
            ByteArrayInputStream sourceStream = new ByteArrayInputStream(buff);
            Thumbnails.of(sourceStream).sourceRegion(x, y, w, h).size(w, h).toOutputStream(outputStream);
            result = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            //MyLog.error(e);
        }
        return result;
    }

}
