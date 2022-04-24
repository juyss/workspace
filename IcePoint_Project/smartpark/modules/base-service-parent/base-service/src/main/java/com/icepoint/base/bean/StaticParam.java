package com.icepoint.base.bean;

//import com.ynapp.frame.Setting;

public class StaticParam {

    // Picture Type
    public static final String IMG_TYPE_COVER = "cover";
    public static final String IMG_TYPE_NORMAL = "normal";

    // File source
//    public static final String SOURCE_MP3 = Setting.getString("mp3_source");
//    public static final String SOURCE_IMG = Setting.getString("img_source");
//    public static final String SOURCE_VIDEO = Setting.getString("video_source");
    public static final String SOURCE_MP3 = "mp3";
    public static final String SOURCE_IMG = "img";
    public static final String SOURCE_VIDEO = "video";

    //public static final String FILE_BASE_SOURCE = Setting.getString("file_base_source");
    public static final String FILE_BASE_SOURCE = "/data/application/res/";
    public static final String ITEM_SOURCE_INTO_BANNER = "intoBanner";
    public static final int LENGTH = 10;
    public static final String LENGTHS = "10";
    public static final int PRICELENG = 10;
    public static final String COLOR1 = "#388E8E";
    public static final String COLOR2 = "#CD2990";
    public static final String COLOR3 = "#C9C9C9";
    public static final String COLOR4 = "#C1FFC1";
    public static final String COLOR5 = "#1E90FF";
    public static final String COLOR6 = "#8B4726";
    public static final String COLOR7 = "#8EE5EE";
    public static final String COLOR8 = "#9A32CD";
    public static final String COLOR9 = "#A0522D";
    public static final String COLOR10 = "#EEE685";
    public static boolean CHECKED_ISOK = false;

    public static boolean checkedIsOK(int isOK) {
        CHECKED_ISOK = isOK != 0;
        return CHECKED_ISOK;
    }


}
