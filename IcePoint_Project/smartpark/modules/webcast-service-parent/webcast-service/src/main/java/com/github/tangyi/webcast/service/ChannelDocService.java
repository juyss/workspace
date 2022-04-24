package com.github.tangyi.webcast.service;

import com.alibaba.fastjson.JSON;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.webcast.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.channel.doc.*;
import net.polyv.live.v1.service.channel.impl.LiveChannelDocServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 频道service
 *
 * @author Xiang Longfei
 * @date 2021/01/12
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChannelDocService {


    public LiveCreateChannelDocResponse createChannelDoc(MultipartFile docFile, LiveCreateChannelDocRequest liveCreateChannelDocRequest) {
        LiveCreateChannelDocResponse liveCreateChannelDocResponse;
        File doc = FileUtil.multipartFileToFile(docFile);
        try {
            liveCreateChannelDocRequest.setFile(doc)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelDocResponse = new LiveChannelDocServiceImpl().createChannelDoc(
                    liveCreateChannelDocRequest);
            if (liveCreateChannelDocResponse != null) {
                //to do something ......
                log.debug("上传频道文档成功，{}", liveCreateChannelDocResponse);
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        } finally {
            doc.delete();
        }
        return liveCreateChannelDocResponse;
    }

    public LiveChannelDocStatusResponse getChannelDocStatus(LiveChannelDocStatusRequest liveChannelDocStatusRequest) {
        LiveChannelDocStatusResponse liveChannelDocStatusResponse;
        try {
            liveChannelDocStatusRequest.setRequestId(LiveSignUtil.generateUUID());
            liveChannelDocStatusResponse = new LiveChannelDocServiceImpl().getChannelDocStatus(liveChannelDocStatusRequest);
            if (liveChannelDocStatusResponse != null) {
                //to do something ......
                log.debug("测查询频道文档转换状态成功，{}", JSON.toJSONString(liveChannelDocStatusResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelDocStatusResponse;
    }

    public LiveListChannelDocResponse listChannelDoc(LiveListChannelDocRequest liveListChannelDocRequest) {
        LiveListChannelDocResponse liveListChannelDocResponse;
        try {
            liveListChannelDocRequest.setRequestId(LiveSignUtil.generateUUID());
            liveListChannelDocResponse = new LiveChannelDocServiceImpl().listChannelDoc(liveListChannelDocRequest);
            if (liveListChannelDocResponse != null) {
                //to do something ......
                log.debug("获取频道文档列表成功，{}", JSON.toJSONString(liveListChannelDocResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveListChannelDocResponse;
    }

    public Boolean deleteChannelDoc(LiveDeleteChannelDocRequest liveDeleteChannelDocRequest) {
        Boolean liveDeleteChannelDocResponse;
        try {
            liveDeleteChannelDocRequest.setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelDocResponse = new LiveChannelDocServiceImpl().deleteChannelDoc(
                    liveDeleteChannelDocRequest);
            if (liveDeleteChannelDocResponse) {
                //to do something ......
                log.debug("删除频道文档成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveDeleteChannelDocResponse;
    }
}


