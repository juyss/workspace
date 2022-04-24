package com.icepoint.base.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.File;
import java.util.UUID;

public class FileUtils {

    public static String getMP3TrackLength(String url) {
        File f = new File(url);
        return getMP3TrackLength(f);
    }

    public static String getMP3TrackLength(File f) {
        try {
            MP3File file = (MP3File) AudioFileIO.read(f);
            MP3AudioHeader audioHeader = (MP3AudioHeader) file.getAudioHeader();
            System.out.println();
            return audioHeader.getTrackLengthAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "00:00";
    }

    public static String getMP4TrackLength(String url) {
        File f = new File(url);
        return getMP4TrackLength(f);
    }

    public static String getMP4TrackLength(File f) {
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo m = encoder.getInfo(f);
            long ls = m.getDuration() / 1000;
            System.out.println(ls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String randomFileName() {
        return UUID.randomUUID().toString().substring(7, 13);
    }

}
