package com.github.tangyi.webcast.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.constant.MqConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.Assert;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.user.api.dto.UserInfoDto;
import com.github.tangyi.user.api.feign.UserServiceClient;
import com.github.tangyi.webcast.api.dto.resp.LiveListChannelVideoLibraryWithPlaybackUrlResponse;
import com.github.tangyi.webcast.api.model.*;
import com.github.tangyi.webcast.mapper.ChannelVideoMapper;
import com.github.tangyi.webcast.mapper.ChannelWhiteListMapper;
import com.github.tangyi.webcast.utils.PageConvertUtil;
import com.spire.xls.core.IFont;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.entity.channel.operate.LiveChannelInfoResponse;
import net.polyv.live.v1.entity.channel.playback.*;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthRequest;
import net.polyv.live.v1.entity.web.auth.LiveChannelAuthResponse;
import net.polyv.live.v1.service.channel.impl.LiveChannelPlaybackServiceImpl;
import net.polyv.live.v1.service.web.impl.LiveWebAuthServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.xml.ws.handler.LogicalHandler;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 频道service
 *
 * @author Xiang Longfei
 * @date 2021/01/12
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChannelVideoService {

    @Autowired
    private ChannelPlaybackVideoService channelPlaybackVideoService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ChannelWhiteListMapper channelWhiteListMapper;

    @Autowired
    private ChannelVideoMapper channelVideoMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ChannelSessionService channelSessionService ;


    /**
     * 回放设置key前缀
     */
    private static final String KEY_PREFIX_CHANNEL_VIDEO = "channelVideo";

    /**
     * 查询频道的回放开关状态
     */
    public String getChannelPlayBack(String id) {
        LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest =
                new LiveChannelPlaybackEnabledInfoRequest();
        String liveChannelPlaybackEnabledInfoResponse;
        try {
            liveChannelPlaybackEnabledInfoRequest.setChannelId(id)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackEnabledInfoResponse = new LiveChannelPlaybackServiceImpl().getChannelPlayBackEnabledInfo(
                    liveChannelPlaybackEnabledInfoRequest);
            if ("Y".equals(liveChannelPlaybackEnabledInfoResponse)) {
                //to do something ......
                log.debug("查询频道的回放开关状态成功{}", liveChannelPlaybackEnabledInfoResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelPlaybackEnabledInfoResponse;
    }

    /**
     * 设置后台回放开关
     */
    @CachePut(value = KEY_PREFIX_CHANNEL_VIDEO)
    public Boolean updateChannelPlayBack(LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest) {
        String liveChannelPlaybackEnabledResponse;
        try {
            liveChannelPlaybackEnabledRequest.setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackEnabledResponse = new LiveChannelPlaybackServiceImpl().updateChannelPlayBackEnabledSetting(
                    liveChannelPlaybackEnabledRequest);
            if (liveChannelPlaybackEnabledResponse != null) {
                //to do something ......
                log.debug("设置频道的回放开关状态成功{}", liveChannelPlaybackEnabledResponse);
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return Boolean.TRUE;
    }


    public LiveListChannelVideoLibraryWithPlaybackUrlResponse listChannelVideoLibrary(String id, String pageNum, String pageSize, String listType) {
        LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest =
                new LiveListChannelVideoLibraryRequest();
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse;
        LiveListChannelVideoLibraryWithPlaybackUrlResponse liveListChannelVideoLibraryWithPlaybackUrlResponse = new LiveListChannelVideoLibraryWithPlaybackUrlResponse();
        try {
            liveListChannelVideoLibraryRequest.setChannelId(id)
                    .setListType(listType)
                    .setPageSize(Integer.parseInt(pageSize))
                    .setCurrentPage(Integer.parseInt(pageNum))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelVideoLibraryResponse = new LiveChannelPlaybackServiceImpl().listChannelVideoLibrary(
                    liveListChannelVideoLibraryRequest);

            if (liveListChannelVideoLibraryResponse != null) {
                //to do something ......
                List<LiveListChannelVideoLibraryWithPlaybackUrlResponse.ChannelVideoLibraryWithPlaybackUrl> channelVideoLibraryWithPlaybackUrlList = liveListChannelVideoLibraryResponse.getContents().stream().map(channelVideoLibrary -> {
                    LiveListChannelVideoLibraryWithPlaybackUrlResponse.ChannelVideoLibraryWithPlaybackUrl channelVideoLibraryWithPlaybackUrl = new LiveListChannelVideoLibraryWithPlaybackUrlResponse.ChannelVideoLibraryWithPlaybackUrl();
                    BeanUtils.copyProperties(channelVideoLibrary, channelVideoLibraryWithPlaybackUrl);
                    return channelVideoLibraryWithPlaybackUrl;
                }).collect(Collectors.toList());
                liveListChannelVideoLibraryWithPlaybackUrlResponse.setContents(channelVideoLibraryWithPlaybackUrlList);
                liveListChannelVideoLibraryWithPlaybackUrlResponse.setCurrentPage(liveListChannelVideoLibraryResponse.getCurrentPage());
                liveListChannelVideoLibraryWithPlaybackUrlResponse.setPageSize(liveListChannelVideoLibraryResponse.getPageSize());
                liveListChannelVideoLibraryWithPlaybackUrlResponse.setTotalItems(liveListChannelVideoLibraryResponse.getTotalItems());
                liveListChannelVideoLibraryWithPlaybackUrlResponse.setTotalPage(liveListChannelVideoLibraryResponse.getTotalPage());
                log.debug("查询视频库-回放列表成功{}", JSON.toJSONString(liveListChannelVideoLibraryResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveListChannelVideoLibraryWithPlaybackUrlResponse;
    }

    public Boolean updateChannelPlaybackSetting(LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest) {
        Boolean liveChannelPlaybackSettingResponse;
        try {
            liveChannelPlaybackSettingRequest.setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackSettingResponse = new LiveChannelPlaybackServiceImpl().updateChannelPlaybackSetting(
                    liveChannelPlaybackSettingRequest);
            if (liveChannelPlaybackSettingResponse) {
                //to do something ......
                log.debug("设置频道回放设置成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelPlaybackSettingResponse;
    }

    public Boolean channelVideoSort(LiveChannelVideoSortRequest liveChannelVideoSortRequest) {
        Boolean liveChannelVideoSortResponse;
        try {
            liveChannelVideoSortRequest.setRequestId(LiveSignUtil.generateUUID());
            liveChannelVideoSortResponse = new LiveChannelPlaybackServiceImpl().setChannelVideoSort(
                    liveChannelVideoSortRequest);
            if (liveChannelVideoSortResponse) {
                //to do something ......
                log.debug("设置视频库-回放列表排序成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelVideoSortResponse;
    }

    public Boolean deleteChannelPlaybackVideo(LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest) {
        Boolean liveDeleteChannelPlaybackVideoResponse;
        try {
            liveDeleteChannelPlaybackVideoRequest.setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelPlaybackVideoResponse = new LiveChannelPlaybackServiceImpl().deleteChannelPlaybackVideo(
                    liveDeleteChannelPlaybackVideoRequest);
            if (liveDeleteChannelPlaybackVideoResponse) {
                //to do something ......
                log.debug("删除视频库-回放列表中的视频成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveDeleteChannelPlaybackVideoResponse;
    }

    public LiveCreateChannelVideoPlaybackResponse createChannelVideoPlayback(LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest) {
        LiveCreateChannelVideoPlaybackResponse liveCreateChannelVideoPlaybackResponse;
        try {
            liveCreateChannelVideoPlaybackRequest.setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelVideoPlaybackResponse = new LiveChannelPlaybackServiceImpl().addChannelVideoPlayback(
                    liveCreateChannelVideoPlaybackRequest);
            if (liveCreateChannelVideoPlaybackResponse != null) {
                //to do something ......
                log.debug("将点播中的视频添加到视频库成功{}", JSON.toJSONString(liveCreateChannelVideoPlaybackResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveCreateChannelVideoPlaybackResponse;
    }

    public PageInfo<LiveChannelVideoListResponse.ChannelVedioInfo> listChannelVideo(String id, Integer pageNum, Integer pageSize) {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        LiveChannelVideoListResponse liveChannelVideoListResponse;
        PageInfo<LiveChannelVideoListResponse.ChannelVedioInfo> pageInfo = new PageInfo<>();
        try {
            liveChannelVideoListRequest.setChannelId(id)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelVideoListResponse = new LiveChannelPlaybackServiceImpl().listChannelVideo(
                    liveChannelVideoListRequest);
            if (liveChannelVideoListResponse != null) {
                //to do something ......
                PageConvertUtil.convertProperties(pageInfo, liveChannelVideoListResponse.getChannelVedioInfos().size(), pageNum, pageSize);
                int fromIndex = (pageNum - 1) * pageSize;
                int toIndex = pageNum * pageSize;
                if (toIndex > liveChannelVideoListResponse.getChannelVedioInfos().size()) {
                    toIndex = liveChannelVideoListResponse.getChannelVedioInfos().size();
                }
                pageInfo.setList(liveChannelVideoListResponse.getChannelVedioInfos().subList(fromIndex, toIndex));
                log.debug("查询视频库-直播暂存成功{}", JSON.toJSONString(liveChannelVideoListResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return pageInfo;
    }

    public Boolean convertChannelVideoListAsync(LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest) {
        Boolean liveConvertChannelVideoResponse;
        try {
            liveConvertChannelVideoListAsyncRequest.setRequestId(LiveSignUtil.generateUUID());
            liveConvertChannelVideoResponse = new LiveChannelPlaybackServiceImpl().convertChannelVideoListAsync(
                    liveConvertChannelVideoListAsyncRequest);
            if (liveConvertChannelVideoResponse) {
                //to do something ......
                log.debug("异步转存直播暂存到点播列表成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveConvertChannelVideoResponse;
    }

    public Boolean mergeChannelVideoAsync(LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest) {
        Boolean liveMergeChannelVideoAsyncResponse;
        try {
            liveMergeChannelVideoAsyncRequest.setRequestId(LiveSignUtil.generateUUID());
            liveMergeChannelVideoAsyncResponse = new LiveChannelPlaybackServiceImpl().mergeChannelVideoAsync(
                    liveMergeChannelVideoAsyncRequest);
            if (liveMergeChannelVideoAsyncResponse) {
                //to do something ......
                log.debug("异步合并直播暂存成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveMergeChannelVideoAsyncResponse;
    }

    public Boolean updatePlaybackTitle(LiveUpdatePlaybackTitleRequest liveUpdatePlaybackTitleRequest) {
        Boolean liveUpdatePlaybackTitleResponse;
        try {
            liveUpdatePlaybackTitleRequest.setRequestId(LiveSignUtil.generateUUID());
            liveUpdatePlaybackTitleResponse = new LiveChannelPlaybackServiceImpl().updatePlaybackTitle(
                    liveUpdatePlaybackTitleRequest);
            if (liveUpdatePlaybackTitleResponse) {
                //to do something ......
                log.debug("修改回放视频名称成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveUpdatePlaybackTitleResponse;
    }

    public Boolean deleteChannelVideo(String channelId, String sessionId, String startTime) {
        Boolean liveDeleteChannelVideoResponse;
        try {
            LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest = new LiveDeleteChannelVideoRequest().setChannelId(channelId).setSessionId(sessionId);
            if (StringUtils.hasText(startTime)) {
                liveDeleteChannelVideoRequest.setStartTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(startTime));
            }
            liveDeleteChannelVideoRequest.setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelVideoResponse = new LiveChannelPlaybackServiceImpl().deleteChannelVideo(
                    liveDeleteChannelVideoRequest);
            if (liveDeleteChannelVideoResponse) {
                //to do something ......
                log.debug("删除直播暂存中的录制文件");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            throw new CommonException("日期格式错误，请符合：yyyyMMddHHmmss格式");
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveDeleteChannelVideoResponse;
    }

    /**
     * 本地视频回放   增加权限
     *
     * @param channelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ChannelVideo> localVideo(String channelId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ChannelVideo example = new ChannelVideo();
        example.setChannelId(channelId);
        example.setObsUploaded("Y");
        //后台
        List<ChannelVideo> allList = channelPlaybackVideoService.queryByChannel(example);
        if ("".equals(channelId)) {
            allList = setUrl(allList);

        }
//        String user = SysUtil.getUser();
//        //未登录 返回所有公开视频
//        if ("anonymousUser".equals(user)) {
//            List<ChannelVideo> disposeChannelVido = setUrl(allList);
//            return new PageInfo<>(disposeChannelVido);
//        }
//        //已登录
//        ResponseBean<UserInfoDto> info = userServiceClient.info();
//        UserInfoDto data = info.getData();
//        List<Dept> depts = new ArrayList<>();
//        Long deptId = data.getDeptId();
//        if (ObjectUtils.isEmpty(deptId)) {
//            //通过用户id去查询 所有的部门
//            Long id = data.getId();
//            //部门不为空
//            depts = deptService.selectDeptByid(id);
//            System.out.println(depts);
//        } else {
//            Dept dept = new Dept();
//            dept.setId(deptId);
//            depts.add(dept);
//        }
//        //登录 查询部门 包含下级部门 不包含自己
//        allList = setUrl(allList);
//        Set<Long> set = new HashSet<>();
//        for (Dept dept : depts) {
//            List<Long> deptParentList = deptService.deptParentList(dept.getId());
//            deptParentList.add(dept.getId());
//            for (Long aLong : deptParentList) {
//                set.add(aLong);
//            }
//        }
//        List<Long> setToList = new ArrayList<>(set);
//        //根据部门id 查询频道
//        List<ChannelDept> channelDeptList = deptService.selectChonnelIds(setToList);
//        //通过id里面的查询每个channelVido
//        List<Long> channlIds = channelDeptList.stream().map(ChannelDept::getChannelId).collect(Collectors.toList());
//        if (!ObjectUtils.isEmpty(channlIds)) {
//            //把集合拷贝到另一个allList中
//            List<ChannelVideo> ChannelVideos = channelVideoMapper.findListChannelById(channlIds);
//            allList.addAll(ChannelVideos);
//            return new PageInfo<>(allList);
//        }
        return new PageInfo<>(allList);

    }

    public Boolean deleteLocalVideo(Long[] ids) {
        return channelPlaybackVideoService.deleteAll(ids) > 0;
    }

    public List<ChannelVideo> localVideoSession(String channelId, String channelSessionId) {
        ChannelVideo example = new ChannelVideo();
        example.setChannelId(channelId);
        example.setChannelSessionId(channelSessionId);
        example.setObsUploaded("Y");
        return channelPlaybackVideoService.findAllList(example);
    }

    public ResponseBean<String> getObsUrl(String type, String code, Long id) {
        //根据type 进行判断
        if (!ObjectUtils.isEmpty(type)) {
            //白名单
            if ("phone".equals(type)) {
                //根据id  webcast_channel_vido
                List<ChannelWhiteList> list = channelWhiteListMapper.selectById(id);
                if (!ObjectUtils.isEmpty(list)) {
                    boolean b = list.stream().anyMatch(a -> a.getPhone().equals(code));
                    if (b) {
                        return new ResponseBean<>(list.get(0).getObsUrl(), "验证成功");
                    }
                    return new ResponseBean<>(null, "验证失败");
                }
            }
            if ("code".equals(type)) {
                //验证码
                ChannelVideo channelVideo = channelVideoMapper.getChannelVideoByid(id);
                if (!ObjectUtils.isEmpty(channelVideo)) {
                    if (code.equals(channelVideo.getVerificationCode())) {
                        return new ResponseBean<>(channelVideo.getObsUrl(), "验证成功");
                    }
                }
            }
        }

        return new ResponseBean<>(null, "验证失败");
    }


    /**
     * 将url置为kong
     */
    public List<ChannelVideo> setUrl(List<ChannelVideo> list) {
        for (ChannelVideo channelVideo : list) {
            if (!ObjectUtils.isEmpty(channelVideo.getType())) {
                channelVideo.setObsUrl("");
                channelVideo.setVerificationCode("");
                channelVideo.setUrl("");
            }
        }
        return list;
    }

    public int insert(ChannelVideo channelVideo) {
        return this.channelVideoMapper.save(channelVideo);
    }


    public Boolean deleteAllChannelId(String channelId) {
        if (this.channelVideoMapper.deleteAllChannelId(channelId) > 0) {return true;}

        return false;
    }

    public Boolean callBack(String status, String fileId, String channelId, String fileUrl, String fileName) {
        log.info("进入插入");
        if ("success".equals(status)) {
            log.info("请求成功");
            ChannelVideo channelVideo = new ChannelVideo();
            channelVideo.setFileId(fileId);
            //判断fileId是否存在 存在就不能合并了 保利威也是这样设定的  再合并的话保利威直接从视频库取得原来的
            ChannelVideo channel = this.channelVideoMapper.selectByFileId(fileId);
            log.info("channel:{}", channel);
            if (!ObjectUtils.isEmpty(channel)) {
                return false;
            }
            channelVideo.setChannelName(fileName);
            channelVideo.setChannelId(channelId);
            channelVideo.setUrl(fileUrl);
            channelVideo.setFileName(fileName);
            log.info("channelVideo:{}", channelVideo);
            //再通过调取保利威的接口通过fileId和channelId返回实体
            LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest = new LiveChannelVideoOnlyRequest();
            LiveChannelVideoOnlyResponse liveChannelVideoOnlyResponse;
            ChannelSessionInfo channelSessionInfo = new ChannelSessionInfo();
            try {
                liveChannelVideoOnlyRequest.setRequestId(LiveSignUtil.generateUUID());
                liveChannelVideoOnlyRequest.setChannelId(channelId)
                        .setFileId(fileId);
                liveChannelVideoOnlyResponse = new LiveChannelPlaybackServiceImpl().getChannelVideoOnly(
                        liveChannelVideoOnlyRequest);
                if (liveChannelVideoOnlyResponse != null) {
                    //to do something ......
                    channelVideo.setChannelSessionId(liveChannelVideoOnlyResponse.getChannelSessionId());
                    channelVideo.setFileSize(liveChannelVideoOnlyResponse.getFilesize().toString());
                    channelVideo.setDuration(liveChannelVideoOnlyResponse.getDuration());
                    channelVideo.setCreateDate(liveChannelVideoOnlyResponse.getCreatedTime());
                    channelVideo.setStartTime(liveChannelVideoOnlyResponse.getStartTime());
                    //设置channelSessionInfo
                    channelSessionInfo.setSessionId(liveChannelVideoOnlyResponse.getChannelSessionId());
                    channelSessionInfo.setStartTime(liveChannelVideoOnlyResponse.getCreatedTime());
                    channelSessionInfo.setUploadStatus("N");
                    channelSessionInfo.setChannelId(liveChannelVideoOnlyResponse.getChannelId());
                    log.info("测试查询指定文件ID的录制文件信息成功{}", JSON.toJSONString(liveChannelVideoOnlyResponse));
                }
            } catch (Exception e) {
                //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
                log.error(e.getMessage(), e);
                // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            }
            //插入数据库 webcast_channel_video
            int save = this.channelVideoMapper.save(channelVideo);
            int insert = this.channelSessionService.insert(channelSessionInfo);
            String channelVideoJson = JSON.toJSONString(channelVideo);
            rabbitTemplate.convertAndSend(MqConstant.CHANNEL_VIDEO_QUEUE,channelVideoJson);
            log.info("发送消息{}",channelVideo);
            return true;
        }
        return false;
    }

}


