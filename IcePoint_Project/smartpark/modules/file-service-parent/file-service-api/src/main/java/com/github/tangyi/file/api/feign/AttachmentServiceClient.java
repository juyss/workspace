package com.github.tangyi.file.api.feign;

import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.basic.vo.AttachmentVo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.constant.ServiceConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.feign.config.CustomFeignConfig;
import com.github.tangyi.file.api.feign.factory.AttachmentServiceClientFallbackFactory;
import com.github.tangyi.file.api.model.Attachment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 文件服务
 *
 * @author gaokx
 * @date 2020/11/30 13:07
 * todo 部分暴露接口待调整
 */
@FeignClient(value = ServiceConstant.FILE_SERVICE, configuration = CustomFeignConfig.class, fallbackFactory = AttachmentServiceClientFallbackFactory.class)
public interface AttachmentServiceClient {
    /**
     * 文件保存
     *
     * @param fileDto fileDto
     * @return ResponseBean
     */
    @PostMapping("/v1/file/upload")
    ResponseBean<?> upload(@RequestBody Attachment fileDto);

    /**
     * 文件保存
     *
     * @return ResponseBean
     */
    @PostMapping(value = "/v1/attachment/upload/feign", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseBean<?> upload(@RequestPart("file") MultipartFile file, @RequestParam("uploadType") Integer uploadType);

    /**
     * 获取文件详情
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/attachment/{id}")
    ResponseBean<Attachment> attachment(@PathVariable Long id);

    /**
     * 分页查询
     *
     * @param pageNum    pageNum
     * @param pageSize   pageSize
     * @param sort       sort
     * @param order      order
     * @param attachment attachment
     * @return PageInfo
     */
    @GetMapping("/v1/attachment/attachmentList")
    PageInfo<Attachment> attachmentList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
                                        @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
                                        @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                        @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                        Attachment attachment);

    /**
     * 文件删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/v1/attachment/{id}")
    ResponseBean<Boolean> delete(@PathVariable Long id);

    /**
     * 文件批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/v1/attachment/deleteAll")
    ResponseBean<Boolean> deleteAllAttachments(@RequestBody Long[] ids);

    /**
     * 批量查询文件信息
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "/v1/attachment/findById")
    ResponseBean<List<AttachmentVo>> findById(@RequestBody Long[] ids);


    /**
     * 查询文件是否支持预览
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/attachment/{id}/canPreview")
    ResponseBean<Boolean> canPreview(@PathVariable Long id);

    /**
     * 文件预览
     *
     * @param response
     * @param id
     */
    @GetMapping("/v1/attachment/preview")
    void preview(HttpServletResponse response, @RequestParam Long id);

}
