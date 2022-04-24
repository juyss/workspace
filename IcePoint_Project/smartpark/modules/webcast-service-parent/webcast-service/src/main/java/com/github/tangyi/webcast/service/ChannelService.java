package com.github.tangyi.webcast.service;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.zxing.QRCodeUtils;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.file.api.feign.AttachmentServiceClient;
import com.github.tangyi.msc.api.dto.SmsDto;
import com.github.tangyi.msc.api.feign.MscServiceClient;
import com.github.tangyi.user.api.feign.UserServiceClient;
import com.github.tangyi.webcast.api.dto.req.LiveChannelSettingWithMobileRequest;
import com.github.tangyi.webcast.api.dto.req.LiveChannelWithDeptRequest;
import com.github.tangyi.webcast.api.dto.resp.ChannelBasicInfo;
import com.github.tangyi.webcast.api.dto.resp.LiveChannelDetailWithPosterUrl;
import com.github.tangyi.webcast.api.model.*;
import com.github.tangyi.webcast.mapper.ChannelDeptMapper;
import com.github.tangyi.webcast.mapper.DeptMapper;
import com.github.tangyi.webcast.utils.FileUtil;
import com.github.tangyi.webcast.utils.PageConvertUtil;
import com.github.tangyi.webcast.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.account.LiveAccountUserDurationsRequest;
import net.polyv.live.v1.entity.account.LiveAccountUserDurationsResponse;
import net.polyv.live.v1.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.v1.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.v1.entity.channel.operate.*;
import net.polyv.live.v1.entity.web.info.LiveUpdateChannelLogoRequest;
import net.polyv.live.v1.entity.web.interact.LiveGetChannelWxShareRequest;
import net.polyv.live.v1.entity.web.interact.LiveGetChannelWxShareResponse;
import net.polyv.live.v1.entity.web.interact.LiveUpdateChannelWxShareRequest;
import net.polyv.live.v1.entity.web.setting.LiveChannelGlobalSwitchRequest;
import net.polyv.live.v1.service.account.impl.LiveAccountServiceImpl;
import net.polyv.live.v1.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.v1.service.web.impl.LiveWebInfoServiceImpl;
import net.polyv.live.v1.service.web.impl.LiveWebInteractServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


/**
 * 频道service
 *
 * @author gaokx
 * @date 2020/17/14 13:23
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChannelService {

    @Autowired
    private MscServiceClient mscServiceClient;

    @Autowired
    private AttachmentServiceClient attachmentServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ChannelDeptService channelDeptService;

    @Autowired
    private ChannelPosterService channelPosterService;

    @Autowired
    private DataReportService dataReportService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ChannelSettingService channelSettingService;

    @Autowired
    private ChannelMarqueeService channelMarqueeService;

    @Autowired
    private PolyvApiService polyvApiService;

    @Autowired
    private ChannelVideoService channelVideoService;

    @Autowired
    private ChannelDeptMapper channelDeptMapper;

    @Autowired
    private DeptMapper deptMapper;


    /**
     * 频道详情key前缀
     */
    private static final String KEY_PREFIX_CHANNEL = "channel";
    /**
     * 频道基本信息key前缀
     */
    private static final String KEY_PREFIX_CHANNEL_BASIC_INFO = "channelBasicInfo";
    /**
     * 频道分页key前缀
     */
    private static final String KEY_PREFIX_CHANNEL_PAGE = "channelPage";

    /**
     * 创建频道,删除缓存
     *
     * @author gaokx
     * @date 2019/1/3 14:21
     */
    @Caching(evict = {@CacheEvict(value = KEY_PREFIX_CHANNEL_PAGE, allEntries = true)})

    public LiveChannelResponse createChannel(LiveChannelRequest channelRequest) {
        LiveChannelResponse liveChannelResponse;
        try {
            //设置频道主题信息
            channelRequest
                    .setAutoPlay(LiveConstant.AutoPlay.AOTU_PLAY.getFlag())
                    .setMaxViewer(300)
//                    .setWatchLayout(LiveConstant.WatchLayout.PPT.getFlag())
                    .setReceive(LiveConstant.Flag.NO.getFlag())
                    .setRequestId(LiveSignUtil.generateUUID());
            //调用SDK请求保利威服务器
            liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(channelRequest);
            //正常返回做B端正常的业务逻辑
            if (liveChannelResponse != null) {
                // 保存部门与频道的关系
                ChannelDept channelDept = new ChannelDept(Long.valueOf(liveChannelResponse.getChannelId()), ((LiveChannelWithDeptRequest) channelRequest).getDeptId());
                channelDeptService.insert(channelDept);

                // 关闭广告设置、跑马灯、功能开关、观看条件、播放器、回放通用设置
                LiveChannelGlobalSwitchRequest advertGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest().setChannelId(liveChannelResponse.getChannelId()).setEnabled("N").setGlobalEnabledType("advert");
                advertGlobalSwitchRequest.setRequestId(LiveSignUtil.generateUUID());
                channelSettingService.setChannelGlobalSwitch(advertGlobalSwitchRequest);

                LiveChannelGlobalSwitchRequest marqueeGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest().setChannelId(liveChannelResponse.getChannelId()).setEnabled("N").setGlobalEnabledType("marquee");
                marqueeGlobalSwitchRequest.setRequestId(LiveSignUtil.generateUUID());
                channelSettingService.setChannelGlobalSwitch(marqueeGlobalSwitchRequest);

                LiveChannelGlobalSwitchRequest switchGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest().setChannelId(liveChannelResponse.getChannelId()).setEnabled("N").setGlobalEnabledType("switch");
                switchGlobalSwitchRequest.setRequestId(LiveSignUtil.generateUUID());
                channelSettingService.setChannelGlobalSwitch(switchGlobalSwitchRequest);

                LiveChannelGlobalSwitchRequest authGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest().setChannelId(liveChannelResponse.getChannelId()).setEnabled("N").setGlobalEnabledType("auth");
                authGlobalSwitchRequest.setRequestId(LiveSignUtil.generateUUID());
                channelSettingService.setChannelGlobalSwitch(authGlobalSwitchRequest);

                LiveChannelGlobalSwitchRequest playerGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest().setChannelId(liveChannelResponse.getChannelId()).setEnabled("N").setGlobalEnabledType("player");
                playerGlobalSwitchRequest.setRequestId(LiveSignUtil.generateUUID());
                channelSettingService.setChannelGlobalSwitch(playerGlobalSwitchRequest);

                polyvApiService.playbackGlobalSetting(liveChannelResponse.getChannelId(), "N");

                // 生成海报
                createPoster(liveChannelResponse.getChannelId(), null);

                return liveChannelResponse;
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelResponse;
    }

    /**
     * 获得频道基本信息
     * 部门有问题  先放一放 看部门接口怎么解决
     * 合并 裁剪 也要添加进部们表  可以考虑aop
     * @return
     */
    //@Cacheable(value = KEY_PREFIX_CHANNEL_BASIC_INFO + CommonConstant.CACHE_SPLIT_EXPIRE, key = "#channelId")
    public ChannelBasicInfo getChannelBasicInfo(String channelId) {
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        LiveChannelInfoResponse liveChannelInfoResponse;
        ChannelBasicInfo channelBasicInfo = new ChannelBasicInfo();
//        //获得频道部门id
//        ChannelDept channelDept = channelDeptMapper.queryByChannelId(channelId);
//
//
//        Long deptId = channelDept.getDeptId();
//        //查询部门
//        Dept depts = deptMapper.selectSysDeptByid(deptId);
//        if (!ObjectUtils.isEmpty(depts)) {
//            channelBasicInfo.setDeptName(depts.getDeptName());
//        }
        try {
            liveChannelBasicInfoRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelInfoRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelInfoResponse = new LiveChannelOperateServiceImpl().getChannelInfo(liveChannelInfoRequest);
            liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(liveChannelBasicInfoRequest);
            BeanUtils.copyProperties(liveChannelBasicInfoResponse, channelBasicInfo);
            channelBasicInfo.setScene(liveChannelInfoResponse.getScene());
            if (channelBasicInfo != null) {
                return channelBasicInfo;
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return channelBasicInfo;
    }


    /**
     * 获得频道信息
     *
     * @return
     */
    @Cacheable(value = KEY_PREFIX_CHANNEL + CommonConstant.CACHE_SPLIT_EXPIRE, key = "#channelId", unless = "#result == null")
    public LiveChannelInfoResponse getChannelInfo(String channelId) {
        LiveChannelInfoRequest request = new LiveChannelInfoRequest();
        LiveChannelInfoResponse response;
        try {
            request.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            response = new LiveChannelOperateServiceImpl().getChannelInfo(request);
            if (response != null) {
                return response;
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return response;
    }


    /**
     * 频道删除缓存
     *
     * @author gaokx
     * @date 2019/1/3 14:21
     */
    @Caching(evict = {
            @CacheEvict(value = KEY_PREFIX_CHANNEL_BASIC_INFO, allEntries = true),
            @CacheEvict(value = KEY_PREFIX_CHANNEL, allEntries = true),
            @CacheEvict(value = KEY_PREFIX_CHANNEL_PAGE, allEntries = true)})
    public Boolean deleteChannel(String channelId) {
        //2021-04-21修改   根据chanelId查询本地回放视频 修改本地视频状态 并逻辑删除
        boolean b = channelVideoService.deleteAllChannelId(channelId);
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
        Boolean liveDeleteChannelResponse;
        try {
            liveDeleteChannelRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelResponse = new LiveChannelOperateServiceImpl().deleteChannel(liveDeleteChannelRequest);
            if (liveDeleteChannelResponse) {
                log.debug("删除直播频道成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveDeleteChannelResponse;
    }

    /**
     * 开启直播
     *
     * @param id
     * @param liveChannelSettingRequestBasicSetting
     */
    /*@Caching(evict = {
            @CacheEvict(value = KEY_PREFIX_CHANNEL_BASIC_INFO, allEntries = true),
            @CacheEvict(value = KEY_PREFIX_CHANNEL, allEntries = true),
            @CacheEvict(value = KEY_PREFIX_CHANNEL_PAGE, allEntries = true)})*/
    public Boolean start(String id, LiveChannelSettingRequest.BasicSetting liveChannelSettingRequestBasicSetting) {
        // 更新频道信息
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        Boolean liveChannelSettingResponse;
        try {
            liveChannelSettingRequest.setChannelId(id)
                    .setBasicSetting(liveChannelSettingRequestBasicSetting)
                    .setAuthSettings(null)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                    liveChannelSettingRequest);
            if (liveChannelSettingResponse) {
                // 保存数据报表数据
                if (liveChannelSettingRequestBasicSetting.getStartTime() != 0) {
                    DataReport dataReport = new DataReport(id, liveChannelSettingRequestBasicSetting.getName(), new Date(liveChannelSettingRequestBasicSetting.getStartTime()));
                    dataReportService.insert(dataReport);
                }
                // 生成海报
                // createPoster(id, liveChannelSettingRequestBasicSetting);

                log.debug("修改频道的相关设置成功");
                //发送短信
                String webStartUrl = "https://live.polyv.net/web-start/?channelId=" + id;
                SmsDto smsDto = new SmsDto();
                String content = String.format("直播地址：%s，密码：%s，请访问后输入密码开启直播。", webStartUrl, liveChannelSettingRequestBasicSetting.getChannelPasswd());
                smsDto.setContent(content);
                smsDto.setReceiver(((LiveChannelSettingWithMobileRequest) liveChannelSettingRequestBasicSetting).getMobile());
                mscServiceClient.sendSms(smsDto);


            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelSettingResponse;
    }

    /**
     * 获得频道分页
     */
//    @Cacheable(value = KEY_PREFIX_CHANNEL_PAGE + CommonConstant.CACHE_SPLIT_EXPIRE, key = "{#request.categoryId == null ? '':#request.categoryId}+'_'+#request.keyword+'_'+#request.watchStatus+'_'+#request.currentPage+'_'+#request.pageSize")
    public PageInfo<LiveChannelDetailWithPosterUrl> pageListAccountDetails(LiveListAccountDetailRequest request, List<Long> deptIdList, String withoutEnd, String withoutPlayback) {
        PageInfo<LiveChannelDetailWithPosterUrl> pageInfo = new PageInfo<>();
        try {
            Integer pageSize = request.getPageSize();
            Integer currentPage = request.getCurrentPage();
            request.setRequestId(LiveSignUtil.generateUUID());
            // 最终目标结果，查出所有，然后再逻辑分页
            request.setCurrentPage(1);
            request.setPageSize(100);
            LiveListAccountDetailResponse response = new LiveAccountServiceImpl().listAccountDetail(request);

            int resultTotalSize = response.getTotalItems();

            // 如果排除playback状态的
            /*if ("Y".equals(withoutPlayback) && !StringUtils.hasText(request.getWatchStatus())) {
                // 查“回放中”状态的结果，用于过滤
                LiveListAccountDetailRequest playbackRequest = new LiveListAccountDetailRequest();
                playbackRequest.setWatchStatus("playback").setRequestId(LiveSignUtil.generateUUID());
                LiveListAccountDetailResponse playbackResponse = new LiveAccountServiceImpl().listAccountDetail(playbackRequest);
                // 过滤
                resultTotalSize -= playbackResponse.getTotalItems();

                List<LiveListAccountDetailResponse.LiveChannelDetail> withoutPlaybackResponse = response.getContents().stream().filter(liveChannelDetail -> !"playback".equals(liveChannelDetail.getWatchStatus())).collect(Collectors.toList());
                if (!"Y".equals(withoutEnd)) {
                    int fromIndex = (currentPage - 1) * pageSize;
                    int toIndex = currentPage * pageSize;
                    if (toIndex > withoutPlaybackResponse.size()) {
                        toIndex = withoutPlaybackResponse.size();
                    }
                    response.setContents(withoutPlaybackResponse.subList(fromIndex, toIndex));
                    response.setPageSize(pageSize);
                }
            }*/

            // 如果排除end状态的
            if ("Y".equals(withoutEnd) && !StringUtils.hasText(request.getWatchStatus())) {
                // 查“回放中”状态的结果，用于过滤
                LiveListAccountDetailRequest playbackRequest = new LiveListAccountDetailRequest();
                playbackRequest.setWatchStatus("playback").setRequestId(LiveSignUtil.generateUUID());
                LiveListAccountDetailResponse playbackResponse = new LiveAccountServiceImpl().listAccountDetail(playbackRequest);
                // 过滤
                resultTotalSize -= playbackResponse.getTotalItems();

                // 查“已结束”状态的结果，用于过滤
                LiveListAccountDetailRequest endRequest = new LiveListAccountDetailRequest();
                endRequest.setWatchStatus("end").setRequestId(LiveSignUtil.generateUUID());
                LiveListAccountDetailResponse endResponse = new LiveAccountServiceImpl().listAccountDetail(endRequest);
                // 过滤
                resultTotalSize -= endResponse.getTotalItems();

                List<LiveListAccountDetailResponse.LiveChannelDetail> withoutEndResponse = response.getContents().stream().filter(liveChannelDetail -> !"end".equals(liveChannelDetail.getWatchStatus()) && !"playback".equals(liveChannelDetail.getWatchStatus()))
                        .sorted(Comparator.comparing(LiveListAccountDetailResponse.LiveChannelDetail::getWatchStatus))
                        .collect(Collectors.toList());
                int fromIndex = (currentPage - 1) * pageSize;
                int toIndex = currentPage * pageSize;
                if (toIndex > withoutEndResponse.size()) {
                    toIndex = withoutEndResponse.size();
                }
                response.setContents(withoutEndResponse.subList(fromIndex, toIndex));
                response.setPageSize(pageSize);
            }

            if (response != null) {
                // 如果没有部门则返回空
                if (CollectionUtils.isEmpty(deptIdList)) {
                    return pageInfo;
                }

                // 查询posterUrl
                List<ChannelPoster> channelPosterList = channelPosterService.findList(null);
                // 添加posterUrl到列表
                List<LiveChannelDetailWithPosterUrl> liveChannelDetailWithPosterUrlList = response.getContents().stream().map(liveChannelDetail -> {
                    LiveChannelDetailWithPosterUrl liveChannelDetailWithPosterUrl = new LiveChannelDetailWithPosterUrl();
                    BeanUtils.copyProperties(liveChannelDetail, liveChannelDetailWithPosterUrl);
                    for (ChannelPoster channelPoster : channelPosterList) {
                        if (liveChannelDetail.getChannelId().equals(channelPoster.getChannelId())) {
                            liveChannelDetailWithPosterUrl.setPosterUrl(channelPoster.getPosterUrl());
                        }
                    }
                    return liveChannelDetailWithPosterUrl;
                }).collect(Collectors.toList());


                // 部门是-1或者超级管理员可查所有数据
                AtomicBoolean isAdmin = new AtomicBoolean(false);
                String username = SysUtil.getUser();
                log.info("SysUtils.getUser(): {}", username);

                UserVo user = userServiceClient.findUserByIdentifier(username, SysUtil.getTenantCode()).getData();
                log.info("userServiceClient.findUserByIdentifier(): {}", user);
                if (user != null) {
                    user.getRoleList().forEach(roleVo -> {
                        if ("role_admin".equals(roleVo.getRoleCode())) {
                            isAdmin.set(true);
                        }
                    });
                }
                log.info("isAdmin: {}", isAdmin.get());
                /**
                 * user.getRoleList() 这个是空的
                 */
                if (-1 == deptIdList.get(0) || isAdmin.get() || "admin".equals(user.getIdentifier())) {
                    // 查出所有频道
                    List<ChannelDept> channelDeptList = channelDeptService.listByDeptIdList(deptIdList);
                    // 查询部门
                    List<Dept> deptList = deptService.findList(new Dept());

                    List<LiveChannelDetailWithPosterUrl> result = liveChannelDetailWithPosterUrlList.stream().map(liveChannelDetailWithPosterUrl -> {
                        // 设置企业ID
                        for (ChannelDept channelDept : channelDeptList) {
                            if (channelDept.getChannelId().toString().equals(liveChannelDetailWithPosterUrl.getChannelId())) {
                                liveChannelDetailWithPosterUrl.setDeptId(channelDept.getDeptId());
                            }
                        }

                        for (Dept dept : deptList) {
                            if (dept.getId().equals(liveChannelDetailWithPosterUrl.getDeptId())) {
                                liveChannelDetailWithPosterUrl.setDeptName(dept.getDeptName());
                            }
                        }

                        return liveChannelDetailWithPosterUrl;
                    }).collect(Collectors.toList());


                    if ("Y".equals(withoutEnd)) {
                        pageInfo.setList(result);
                    } else {
                        int fromIndex = (currentPage - 1) * pageSize;
                        int toIndex = currentPage * pageSize;
                        if (toIndex > result.size()) {
                            toIndex = result.size();
                        }
                        pageInfo.setList(result.subList(fromIndex, toIndex));
                    }
                    PageConvertUtil.convertProperties(pageInfo, resultTotalSize, currentPage, pageSize);

                    // 设置主持人
                    pageInfo.getList().stream().forEach(liveChannelDetailWithPosterUrl -> {
                        liveChannelDetailWithPosterUrl.setPublisher("");
                        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
                        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
                        try {
                            liveChannelBasicInfoRequest.setChannelId(liveChannelDetailWithPosterUrl.getChannelId()).setRequestId(LiveSignUtil.generateUUID());
                            liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(liveChannelBasicInfoRequest);
                            if (liveChannelBasicInfoResponse != null) {
                                liveChannelDetailWithPosterUrl.setPublisher(liveChannelBasicInfoResponse.getPublisher());
                            }
                        } catch (PloyvSdkException e) {
                            log.error(e.getMessage(), e);
                            throw new CommonException(e.getMessage());
                        } catch (Exception e) {
                            log.error("SDK调用异常", e);
                            throw new CommonException("SDK调用异常");
                        }
                    });

                    return pageInfo;
                }

                // 查出自己所有的下级部门
                List<Long> allDeptId = new ArrayList<>();
                for (Long deptId : deptIdList) {
                    allDeptId = deptService.deptParentList(deptId);
                    allDeptId.add(deptId);
                }
                // 查出所有频道
                List<ChannelDept> channelDeptList = channelDeptService.listByDeptIdList(allDeptId);

                // 筛选符合部门ID的频道
                List<LiveChannelDetailWithPosterUrl> liveChannelDetailList = liveChannelDetailWithPosterUrlList.stream().filter(liveChannelDetail -> {
                            boolean result = false;
                            for (ChannelDept channelDept : channelDeptList) {
                                if (channelDept.getChannelId().toString().equals(liveChannelDetail.getChannelId())) {
                                    result = true;
                                    break;
                                }
                            }
                            return result;
                        }
                ).collect(Collectors.toList());

                int fromIndex = (currentPage - 1) * pageSize;
                int toIndex = currentPage * pageSize;
                if (toIndex > liveChannelDetailList.size()) {
                    toIndex = liveChannelDetailList.size();
                }
                PageConvertUtil.convertProperties(pageInfo, liveChannelDetailList.size(), currentPage, pageSize);
                List<LiveChannelDetailWithPosterUrl> list = liveChannelDetailList.subList(fromIndex, toIndex);
                pageInfo.setList(list);

                // 设置主持人
                list.stream().filter(liveChannelDetailWithPosterUrl -> "live".equals(liveChannelDetailWithPosterUrl.getWatchStatus())).forEach(liveChannelDetailWithPosterUrl -> {
                    liveChannelDetailWithPosterUrl.setPublisher("");
                    LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
                    LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
                    try {
                        liveChannelBasicInfoRequest.setChannelId(liveChannelDetailWithPosterUrl.getChannelId()).setRequestId(LiveSignUtil.generateUUID());
                        liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(liveChannelBasicInfoRequest);
                        if (liveChannelBasicInfoResponse != null) {
                            liveChannelDetailWithPosterUrl.setPublisher(liveChannelBasicInfoResponse.getPublisher());
                        }
                    } catch (PloyvSdkException e) {
                        log.error(e.getMessage(), e);
                        throw new CommonException(e.getMessage());
                    } catch (Exception e) {
                        log.error("SDK调用异常", e);
                        throw new CommonException("SDK调用异常");
                    }
                });

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

    /**
     * 更新频道
     *
     * @param id
     * @param liveChannelSettingRequestBasicSetting
     */
    @Caching(evict = {
            @CacheEvict(value = KEY_PREFIX_CHANNEL_BASIC_INFO, allEntries = true),
            @CacheEvict(value = KEY_PREFIX_CHANNEL, allEntries = true),
            @CacheEvict(value = KEY_PREFIX_CHANNEL_PAGE, allEntries = true)})
    public Boolean updateChannelSetting(String id, LiveChannelSettingRequest.BasicSetting liveChannelSettingRequestBasicSetting) {
        // 更新频道信息
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        Boolean liveChannelSettingResponse;
        try {
            liveChannelSettingRequest.setChannelId(id)
                    .setBasicSetting(liveChannelSettingRequestBasicSetting)
                    .setAuthSettings(null)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                    liveChannelSettingRequest);
            if (liveChannelSettingResponse) {
                if (StringUtils.hasText(liveChannelSettingRequestBasicSetting.getName())) {
                    // 如果名称不为空就生成海报
                    createPoster(id, liveChannelSettingRequestBasicSetting);
                }
                log.debug("修改频道的相关设置成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveChannelSettingResponse;
    }

    private void createPoster(String id, LiveChannelSettingRequest.BasicSetting liveChannelSettingRequestBasicSetting) throws IOException {
        String tempDir = "/home/park/attach/tempDir";
        String tempFilename = UUID.randomUUID().toString() + ".jpg";
        File file = drawPoster(id, liveChannelSettingRequestBasicSetting, tempDir, tempFilename);
        ResponseBean<?> uploadResponseBean = attachmentServiceClient.upload(FileUtil.fileToMultipartFile(file), 4);
        String posterUrl = "";
        if (uploadResponseBean == null) {
            log.error("生成海报时，保存失败");
        } else {
            posterUrl = ((Map) uploadResponseBean.getData()).get("id").toString();
        }
        // 保存海报URL
        ChannelPoster channelPosterExample = new ChannelPoster().setChannelId(id);
        ChannelPoster channelPoster = channelPosterService.get(channelPosterExample);
        if (channelPoster == null) {
            channelPoster = new ChannelPoster().setChannelId(id).setPosterUrl(posterUrl);
            channelPoster.setNewRecord(true);
        } else {
            channelPoster.setPosterUrl(posterUrl);
        }
        channelPosterService.save(channelPoster);
        file.delete();
    }

    private File drawPoster(String id, LiveChannelSettingRequest.BasicSetting liveChannelSettingRequestBasicSetting, String tempDir, String tempFilename) throws IOException {
        File tempDirFile = new File(tempDir);
        if (!tempDirFile.exists()) {
            tempDirFile.mkdirs();
        }

        /* 需求变更
        int width = 1000, height = 1256;
        //创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        //基于图片对象打开绘图
        Graphics2D graphics = image.createGraphics();

        Font titleFont = new Font("微软雅黑", Font.PLAIN, 60);
        Font contentFont = new Font("微软雅黑", Font.PLAIN, 38);
        graphics.setFont(titleFont);
        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke());

        int leftMargin = 80;

        // 标题
        *//*需求变更：不再需要
        int titleY = 40;
        String liveName = liveChannelSettingRequestBasicSetting.getName();
        Rectangle titleRectangle = new Rectangle(width, titleY);
        drawCenteredString(graphics, liveName, titleRectangle, titleFont);*//*

        // 引导页
        int splashImgY = 143;
        BufferedImage splashImg = null;
        if (StringUtils.hasText(liveChannelSettingRequestBasicSetting.getSplashImg())) {
            String splashImgUrl = "http:" + liveChannelSettingRequestBasicSetting.getSplashImg();
            splashImg = ImageIO.read(new URL(splashImgUrl));
        } else {
            splashImg = ImageIO.read(Resources.getResourceAsStream("defaultSplashImg.png"));
        }
        int splashImgX = (width - splashImg.getWidth()) / 2;
        graphics.drawImage(splashImg, splashImgX, splashImgY, null);

        // 子标题
        int subTitleY = 693;
        graphics.drawString(liveChannelSettingRequestBasicSetting.getName(), leftMargin, subTitleY);

        // 主持人图标
        int publisherImgY = 787 - 34;
        int publisherImgWidth = 36 + 6;
        BufferedImage publisherImg = ImageIO.read(Resources.getResourceAsStream("publisherImg.png"));
        graphics.drawImage(publisherImg, leftMargin, publisherImgY, null);


        graphics.setFont(contentFont);
        // 主持人
        int publisherY = 787;
        String publisher = "主持人：" + liveChannelSettingRequestBasicSetting.getPublisher();
        graphics.drawString(publisher, leftMargin + publisherImgWidth, publisherY);

        // 直播时间
        int liveTimeY = 860;
        String startTime = "直播时间：";
        if (liveChannelSettingRequestBasicSetting.getStartTime() != null && liveChannelSettingRequestBasicSetting.getStartTime() != 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            startTime += simpleDateFormat.format(new Date(liveChannelSettingRequestBasicSetting.getStartTime()));
        }
        graphics.drawString(startTime, leftMargin, liveTimeY);


        // 二维码
        String qrCodeContent = "https://live.polyv.cn/watch/" + id;
        int qrCodeSize = 12;
        BufferedImage qrCodeImage = QRCodeUtils.qRCodeCommon(qrCodeContent, "PNG", qrCodeSize);
        int qrCodeImageX = 700;
        int qrCodeImageY = 650;
        graphics.drawImage(qrCodeImage, qrCodeImageX, qrCodeImageY, null);

        // 直播介绍
        int liveIntroY = 948;
        String liveIntro = "直播介绍：";
        graphics.drawString(liveIntro, leftMargin, liveIntroY);

        graphics.setColor(Color.GRAY);
        LiveListChannelMenuResponse liveListChannelMenuResponse = channelSettingService.listChannelMenu(id);
        LiveListChannelMenuResponse.ChannelMenu menu = liveListChannelMenuResponse.getChannelMenus().stream().filter(channelMenu -> "直播介绍".equals(channelMenu.getName())).findFirst().orElse(new LiveListChannelMenuResponse.ChannelMenu());
        String liveIntroContent = menu.getContent();
        int liveIntroWidth = 190;
        graphics.drawString(liveIntroContent, leftMargin + liveIntroWidth, liveIntroY);*/

        BufferedImage image = ImageIO.read(Resources.getResourceAsStream("posterTemplate.png"));
        //基于图片对象打开绘图
        Graphics2D graphics = image.createGraphics();

        String qrCodeContent = "https://live.polyv.cn/watch/" + id;
        int qrCodeSize = 4;
        BufferedImage qrCodeImage = QRCodeUtils.qRCodeCommon(qrCodeContent, "PNG", qrCodeSize);
        int qrCodeImageX = 23;
        int qrCodeImageY = 23;
        graphics.drawImage(qrCodeImage, qrCodeImageX, qrCodeImageY, null);
        File result = new File(tempDir, tempFilename);
        ImageIO.write(image, "PNG", result);
        graphics.dispose();
        return result;
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g    The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    public String updateChannelLogo(String id, MultipartFile file) {
        LiveUpdateChannelLogoRequest liveUpdateChannelLogoRequest = new LiveUpdateChannelLogoRequest();
        String liveUpdateChannelLogoResponse;
        File imgfile = FileUtil.multipartFileToFile(file);
        try {
            liveUpdateChannelLogoRequest.setChannelId(id)
                    .setImgfile(imgfile)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelLogoResponse = new LiveWebInfoServiceImpl().updateChannelLogo(
                    liveUpdateChannelLogoRequest);
            if (liveUpdateChannelLogoResponse != null) {
                //to do something ......
                log.debug("设置频道图标成功,{}", liveUpdateChannelLogoResponse);
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        } finally {
            // 把暂存的文件删除
            imgfile.delete();
        }
        return liveUpdateChannelLogoResponse;
    }

    public LiveAccountUserDurationsResponse getUserDurations() {
        LiveAccountUserDurationsRequest liveAccountUserDurationsRequest = new LiveAccountUserDurationsRequest();
        LiveAccountUserDurationsResponse liveAccountUserDurationsResponse;
        try {
            liveAccountUserDurationsRequest.setRequestId(LiveSignUtil.generateUUID());
            liveAccountUserDurationsResponse = new LiveAccountServiceImpl().getUserDurations(
                    liveAccountUserDurationsRequest);
            if (liveAccountUserDurationsResponse != null) {
                //to do something ......
                log.debug("查询账户分钟数成功,{}", JSON.toJSONString(liveAccountUserDurationsResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveAccountUserDurationsResponse;
    }

    public Boolean updateChannelWxShare(LiveUpdateChannelWxShareRequest liveUpdateChannelWxShareRequest) {
        Boolean liveUpdateChannelWxShareResponse;
        try {
            liveUpdateChannelWxShareRequest.setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelWxShareResponse = new LiveWebInteractServiceImpl().updateChannelWxShare(
                    liveUpdateChannelWxShareRequest);
            if (liveUpdateChannelWxShareResponse) {
                //to do something ......
                log.debug("设置频道微信分享信息成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveUpdateChannelWxShareResponse;
    }

    public LiveGetChannelWxShareResponse getChannelWxShare(String id) {
        LiveGetChannelWxShareRequest liveGetChannelWxShareRequest = new LiveGetChannelWxShareRequest();
        LiveGetChannelWxShareResponse liveGetChannelWxShareResponse;
        try {
            liveGetChannelWxShareRequest.setChannelId(id).setRequestId(LiveSignUtil.generateUUID());
            liveGetChannelWxShareResponse = new LiveWebInteractServiceImpl().getChannelWxShare(
                    liveGetChannelWxShareRequest);
            if (liveGetChannelWxShareResponse != null) {
                //to do something ......
                log.debug("查询频道微信分享信息成功，{}", JSON.toJSONString(liveGetChannelWxShareResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveGetChannelWxShareResponse;
    }

    public LiveSonChannelInfoListResponse getSonChannelInfoList(String id) {
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse;
        try {
            liveSonChannelInfoListRequest.setChannelId(id).setRequestId(LiveSignUtil.generateUUID());
            liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().getSonChannelInfoList(
                    liveSonChannelInfoListRequest);
            if (liveSonChannelInfoResponse != null) {
                //to do something ......
                log.debug("查询频道号下所有子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return liveSonChannelInfoResponse;
    }

    public Boolean updateSubChannelInfo(LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest) {
        Boolean updateSonChannelInfoResponse;
        try {

            liveUpdateSonChannelInfoRequest.setRequestId(LiveSignUtil.generateUUID());
            updateSonChannelInfoResponse = new LiveChannelOperateServiceImpl().updateSonChannelInfo(
                    liveUpdateSonChannelInfoRequest);
            if (updateSonChannelInfoResponse) {
                //to do something ......
                log.debug("设置子频道信息成功");
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
        return updateSonChannelInfoResponse;
    }

    /**
     * <a href="https://dev.polyv.net/2017/liveproduct/l-manual/l-function-intro/l-my-live/bfqgl/diy_marquee/">详见</a>
     */
    public JSON getUrlMarquee(String vid, String uid, String code, String t) {
        ChannelMarquee example = new ChannelMarquee();
        example.setChannelId(vid);
        ChannelMarquee channelMarquee = channelMarqueeService.get(example);
        if (channelMarquee == null || !StringUtils.hasText(channelMarquee.getMarquee())) {
            return defaultMarquee(vid, uid, code, t);
        }
        JSONObject result = JSON.parseObject(channelMarquee.getMarquee());
        // 测试数据
        /*String username = "河北石家庄循环化工园区";
        String msg = "河北石家庄循环化工园区";
        int fontSize = 30;
        String fontColor = "0x000000";
        int speed = 200;
        String filter = "off";
        int setting = 1;
        float alpha = 1.0f;
        float filterAlpha = 1.0f;
        String filterColor = "0x000000";
        int blurX = 2;
        int blurY = 2;
        int interval = 5;
        int lifeTime = 3;
        int tweenTime = 1;
        int strength = 4;
        String show = "on";*/
        // 默认设置
        String msg = "跑马灯错误，请联系系统管理员。";
        int speed = 200;
        String filter = "off";
        float filterAlpha = 1.0f;
        String filterColor = "0x000000";
        int blurX = 2;
        int blurY = 2;
        int interval = 5;
        int lifeTime = 3;
        int tweenTime = 1;
        int strength = 4;
        // 用户配置设置
        String username = result.getString("username");
        int fontSize = result.getIntValue("fontSize");
        String fontColor = result.getString("fontColor");
        int setting = result.getIntValue("setting");
        float alpha = result.getFloatValue("alpha");
        String show = result.getString("show");
        String sign = "vid=" + vid
                + "&uid=" + uid
                + "&username=" + username
                + "&code=" + code
                + "&t=" + t
                + "&msg=" + msg
                + "&fontSize=" + fontSize
                + "&fontColor=" + fontColor
                + "&speed=" + speed
                + "&filter=" + filter
                + "&setting=" + setting
                + "&alpha=" + alpha
                + "&filterAlpha=" + filterAlpha
                + "&filterColor=" + filterColor
                + "&blurX=" + blurX
                + "&blurY=" + blurY
                + "&interval=" + interval
                + "&lifeTime=" + lifeTime
                + "&tweenTime=" + tweenTime
                + "&strength=" + strength
                + "&show=" + show;
        sign = DigestUtils.md5DigestAsHex(sign.getBytes());

        //result.put("username", username); //跑马灯显示的内容
        result.put("sign", sign); //通过MD5加密算法计算得到32位小写的值
        result.put("msg", msg); //自定义播放错误提示信息
        //result.put("fontSize", fontSize); //跑马灯文字字体大小
        //result.put("fontColor", fontColor); //跑马灯文字字体颜色
        result.put("speed", speed); //跑马灯文字移动指定像素所需时间
        result.put("filter", filter); //是否描边
        //result.put("setting", setting); //跑马灯样式
        result.put("alpha", alpha); //跑马灯文本透明度
        result.put("filterAlpha", filterAlpha); //跑马灯描边透明度
        result.put("filterColor", filterColor); //跑马灯描边颜色
        result.put("blurX", blurX); //跑马灯描边水平模糊量
        result.put("blurY", blurY); //跑马灯描边垂直模糊量
        result.put("interval", interval); //跑马灯文本隐藏间隔时间
        result.put("lifeTime", lifeTime); //跑马灯文本显示时间
        result.put("tweenTime", tweenTime); //跑马灯文本渐隐渐现时间
        result.put("strength", strength); //跑马灯描边强度
        //result.put("show", show); //是否显示跑马灯，默认显示
        return result;
    }

    private JSON defaultMarquee(String vid, String uid, String code, String t) {
        JSONObject result = new JSONObject();
        String username = "河北石家庄循环化工园区";
        String msg = "跑马灯错误，请联系系统管理员。";
        int fontSize = 30;
        String fontColor = "0x000000";
        int speed = 200;
        String filter = "off";
        int setting = 1;
        float alpha = 1.0f;
        float filterAlpha = 1.0f;
        String filterColor = "0x000000";
        int blurX = 2;
        int blurY = 2;
        int interval = 5;
        int lifeTime = 3;
        int tweenTime = 1;
        int strength = 4;
        String show = "on";
        String sign = "vid=" + vid
                + "&uid=" + uid
                + "&username=" + username
                + "&code=" + code
                + "&t=" + t
                + "&msg=" + msg
                + "&fontSize=" + fontSize
                + "&fontColor=" + fontColor
                + "&speed=" + speed
                + "&filter=" + filter
                + "&setting=" + setting
                + "&alpha=" + alpha
                + "&filterAlpha=" + filterAlpha
                + "&filterColor=" + filterColor
                + "&blurX=" + blurX
                + "&blurY=" + blurY
                + "&interval=" + interval
                + "&lifeTime=" + lifeTime
                + "&tweenTime=" + tweenTime
                + "&strength=" + strength
                + "&show=" + show;
        sign = DigestUtils.md5DigestAsHex(sign.getBytes());
        result.put("username", username); //跑马灯显示的内容
        result.put("sign", sign); //通过MD5加密算法计算得到32位小写的值
        result.put("msg", msg); //自定义播放错误提示信息
        result.put("fontSize", fontSize); //跑马灯文字字体大小
        result.put("fontColor", fontColor); //跑马灯文字字体颜色
        result.put("speed", speed); //跑马灯文字移动指定像素所需时间
        result.put("filter", filter); //是否描边
        result.put("setting", setting); //跑马灯样式
        result.put("alpha", alpha); //跑马灯文本透明度
        result.put("filterAlpha", filterAlpha); //跑马灯描边透明度
        result.put("filterColor", filterColor); //跑马灯描边颜色
        result.put("blurX", blurX); //跑马灯描边水平模糊量
        result.put("blurY", blurY); //跑马灯描边垂直模糊量
        result.put("interval", interval); //跑马灯文本隐藏间隔时间
        result.put("lifeTime", lifeTime); //跑马灯文本显示时间
        result.put("tweenTime", tweenTime); //跑马灯文本渐隐渐现时间
        result.put("strength", strength); //跑马灯描边强度
        result.put("show", show); //是否显示跑马灯，默认显示
        return result;
    }

    public String getSubChannelToken(String account) {
        LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest = new LiveCreateSonChannelTokenRequest();
        Boolean liveCreateSonChannelTokenResponse;
        String token = LiveSignUtil.generateUUID();
        try {
            liveCreateSonChannelTokenRequest.setAccount(account)
                    .setToken(token)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateSonChannelTokenResponse = new LiveChannelOperateServiceImpl().createSonChannelToken(
                    liveCreateSonChannelTokenRequest);
            if (liveCreateSonChannelTokenResponse) {
                //to do something ......
                log.debug("设置子频道单点登陆token成功，{}", token);
                return token;
            }
            // 不成功
            throw new CommonException("设置子频道单点登陆token失败，请重试");
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw new CommonException("SDK调用异常");
        }
    }


    public List<LiveListAccountDetailResponse.LiveChannelDetail> channelASLive() {
        LiveListAccountDetailRequest liveRequest = new LiveListAccountDetailRequest();
        LiveListAccountDetailResponse liveResponse;
        LiveListAccountDetailRequest waitingRequest = new LiveListAccountDetailRequest();
        LiveListAccountDetailResponse waitingResponse;
        try {
            //查询正在直播的频道信息
            liveRequest.setCategoryId(null).setKeyword(null).setRequestId(LiveSignUtil.generateUUID());
            liveRequest.setWatchStatus("live");
            liveResponse = new LiveAccountServiceImpl().listAccountDetail(
                    liveRequest);
            List<LiveListAccountDetailResponse.LiveChannelDetail> lives = liveResponse.getContents();
            //查询未开始的
            waitingRequest.setCategoryId(null).setKeyword(null).setRequestId(LiveSignUtil.generateUUID());
            waitingRequest.setWatchStatus("waiting");
            waitingResponse = new LiveAccountServiceImpl().listAccountDetail(
                    waitingRequest);
            List<LiveListAccountDetailResponse.LiveChannelDetail> waitings = waitingResponse.getContents();
            if (!ObjectUtils.isEmpty(lives)) {
                //按时间排序
                lives = lives.stream().sorted(Comparator.comparing(LiveListAccountDetailResponse.LiveChannelDetail::getStartTime).reversed()).
                        collect(Collectors.toList());
                lives.addAll(waitings);
                return lives;
            }
            if (!ObjectUtils.isEmpty(waitings)) {
                waitings = waitings.stream().
                        sorted(Comparator.comparing(LiveListAccountDetailResponse.LiveChannelDetail::getStartTime, Comparator.nullsFirst(Date::compareTo)).reversed()).
                        collect(Collectors.toList());
            }

            return waitings;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}


