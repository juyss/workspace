package com.github.tangyi.webcast.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.tangyi.webcast.api.dto.req.LiveClipChannelVideoAsyncRequest;
import com.github.tangyi.webcast.properties.WebcastProperties;
import com.github.tangyi.webcast.utils.HttpClientUtil;
import com.github.tangyi.webcast.utils.PolyvTool;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.entity.channel.playback.LiveMergeChannelVideoAsyncRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PolyvApiService {

    @Autowired
    private WebcastProperties webcastProperties;

    @Value("${poy.callBackUrl}")
    private String callBackUrl;

    public JSONObject listDailySummary(String channelId, String startDate, String endDate) {
        String url = "http://api.polyv.net/live/v2/statistics/" + channelId + "/summary";

        Map<String, String> params = new HashMap<>();
        params.put("startDay", startDate);
        params.put("endDay", endDate);
        params.put("appId", webcastProperties.getAppId());
        params.put("timestamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
        // 调用Polyv的工具类方法设置sign
        PolyvTool.setLiveSign(params, webcastProperties.getAppId(), webcastProperties.getAppSecret());
        // 调用Polyv的httpClient工具类发送请求
        String content = HttpClientUtil.getInstance()
                .sendHttpGet(url + "?" + PolyvTool.mapJoinNotEncode(params));
        return JSON.parseObject(content);
    }

    public boolean mergeChannelVideoAsync(LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest) {
        String url = "http://api.polyv.net/live/v3/channel/record/merge";
        liveMergeChannelVideoAsyncRequest.setCallbackUrl(callBackUrl);
        Map<String, String> params = new HashMap<>();
        params.put("channelId", liveMergeChannelVideoAsyncRequest.getChannelId());
        params.put("fileIds", liveMergeChannelVideoAsyncRequest.getFileIds());
        if (StringUtils.hasText(liveMergeChannelVideoAsyncRequest.getFileName())) {
            params.put("fileName", liveMergeChannelVideoAsyncRequest.getFileName());
        }
        if (StringUtils.hasText(liveMergeChannelVideoAsyncRequest.getCallbackUrl())) {
            params.put("callbackUrl", liveMergeChannelVideoAsyncRequest.getCallbackUrl());
        }
        if (StringUtils.hasText(liveMergeChannelVideoAsyncRequest.getAutoConvert())) {
            params.put("autoConvert", liveMergeChannelVideoAsyncRequest.getAutoConvert());
        }
        if (StringUtils.hasText(liveMergeChannelVideoAsyncRequest.getMergeMp4()))
            params.put("appId", webcastProperties.getAppId());
        params.put("mergeMp4", "Y");
        params.put("timestamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
        // 调用Polyv的工具类方法设置sign
        PolyvTool.setLiveSign(params, webcastProperties.getAppId(), webcastProperties.getAppSecret());
        // 调用Polyv的httpClient工具类发送请求
        String content = HttpClientUtil.getInstance()
                .sendHttpPost(url, params);
        log.info("合并响应：{}", content);
        JSONObject jsonObject = JSON.parseObject(content);
        if ("200".equals(jsonObject.get("code").toString())) {
            //合并成功后 保存到本地视频库
            return true;
        }
        return false;
    }

    public boolean clipChannelVideoAsync(LiveClipChannelVideoAsyncRequest liveClipChannelVideoAsyncRequest) {
        String url = "https://api.polyv.net/live/v3/channel/record/clip";
        Map<String, String> params = new HashMap<>();
        liveClipChannelVideoAsyncRequest.setCallbackUrl(callBackUrl);
        params.put("channelId", liveClipChannelVideoAsyncRequest.getChannelId());
        params.put("fileId", liveClipChannelVideoAsyncRequest.getFileId());
        params.put("deleteTimeFrame", liveClipChannelVideoAsyncRequest.getDeleteTimeFrame());
        if (StringUtils.hasText(liveClipChannelVideoAsyncRequest.getFileName())) {
            params.put("fileName", liveClipChannelVideoAsyncRequest.getFileName());
        }
        if (StringUtils.hasText(liveClipChannelVideoAsyncRequest.getCallbackUrl())) {
            params.put("callbackUrl", liveClipChannelVideoAsyncRequest.getCallbackUrl());
        }
        if (StringUtils.hasText(liveClipChannelVideoAsyncRequest.getAutoConvert())) {
            params.put("autoConvert", liveClipChannelVideoAsyncRequest.getAutoConvert());
        }
        params.put("mergeMp4", "Y");
        params.put("appId", webcastProperties.getAppId());
        params.put("timestamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
        // 调用Polyv的工具类方法设置sign
        PolyvTool.setLiveSign(params, webcastProperties.getAppId(), webcastProperties.getAppSecret());
        // 调用Polyv的httpClient工具类发送请求
        String content = HttpClientUtil.getInstance()
                .sendHttpPost(url, params);
        log.info("裁剪响应：{}", content);
        JSONObject jsonObject = JSON.parseObject(content);
        if ("200".equals(jsonObject.get("code").toString())) {
            return true;
        }
        return false;
    }

    public boolean playbackGlobalSetting(String channelId, String globalSettingEnabled) {
        String url = "http://api.polyv.net/live/v3/channel/playback/set-setting";

        Map<String, String> params = new HashMap<>();
        params.put("channelId", channelId);
        params.put("globalSettingEnabled", globalSettingEnabled);
        params.put("appId", webcastProperties.getAppId());
        params.put("timestamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
        // 调用Polyv的工具类方法设置sign
        PolyvTool.setLiveSign(params, webcastProperties.getAppId(), webcastProperties.getAppSecret());
        // 调用Polyv的httpClient工具类发送请求
        String content = HttpClientUtil.getInstance()
                .sendHttpPost(url, params);
        log.info("设置频道回放设置响应：{}", content);
        JSONObject jsonObject = JSON.parseObject(content);
        if ("200".equals(jsonObject.get("code").toString())) {
            return true;
        }
        return false;
    }

    /* 测试代码
    public void testBasic() {
        String url = "http://api.polyv.net/live/v3/channel/basic/get";

        // 查询频道号
        String channelId = "415174";

        Map<String, String> params = new HashMap<>();
        params.put("channelId", channelId);
        // 调用Polyv的工具类方法设置sign
        PolyvTool.setLiveSign(params, appId, appSecret);
        // 调用Polyv的httpClient工具类发送请求
        String content = HttpClientUtil.getInstance()
                .sendHttpGet(url + "?" + PolyvTool.mapJoinNotEncode(params));
        System.out.println(content);
    }

    public static void main(String[] args) {
        String url = "http://api.polyv.net/live/v2/statistics/2086568/summary";

        // 查询频道号
        String channelId = "2086568";

        Map<String, String> params = new HashMap<>();
        params.put("startDay", "2021-01-18");
        params.put("endDay", "2021-01-19");
        params.put("appId", "fu8nu88dq4");
        params.put("timestamp", "1611041316");
        // 调用Polyv的工具类方法设置sign
        PolyvTool.setLiveSign(params, "fu8nu88dq4", "df72b6e943d240a0a3ebc0929d485076");
        // 调用Polyv的httpClient工具类发送请求
        String content = HttpClientUtil.getInstance()
                .sendHttpGet(url + "?" + PolyvTool.mapJoinNotEncode(params));
        System.out.println(content);
    }*/

    /*public static void main(String[] args) throws IOException {
        int width = 334, height = 370;
        //创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        //基于图片对象打开绘图
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke());
        int titleX = width / 2;
        int titleY = 30;
        graphics.drawString("课程直播", titleX, titleY);

        BufferedImage splashImg = ImageIO.read(Resources.getResourceAsStream("defaultSplashImg.png"));
        int splashImgX = (width - splashImg.getWidth()) / 2;
        graphics.drawImage(splashImg, splashImgX, 50, null);

        int subTitleY = titleY + splashImg.getHeight() + 50;
        graphics.drawString("课程直播", 30, subTitleY);

        int publisherY = subTitleY + 30;
        graphics.drawString("主持人：", 30, publisherY);

        int liveTimeY = publisherY + 30;
        graphics.drawString("直播时间：", 30, liveTimeY);


        String qrCodeContent = "https://live.polyv.cn/watch/2111109";
        BufferedImage qrCodeImage = QRCodeUtils.qRCodeCommon(qrCodeContent, "PNG", 3);
        int qrCodeImageX = width - qrCodeImage.getWidth() - 25;
        int qrCodeImageY = subTitleY;
        graphics.drawImage(qrCodeImage, qrCodeImageX, qrCodeImageY, null);
        ImageIO.write(image, "PNG", new FileOutputStream("/home/park/attach/tempDir/test.png"));
    }*/
}
