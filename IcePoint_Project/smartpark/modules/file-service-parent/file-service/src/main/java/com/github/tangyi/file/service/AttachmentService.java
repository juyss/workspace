package com.github.tangyi.file.service;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.file.api.constant.AttachmentConstant;
import com.github.tangyi.file.api.model.Attachment;
import com.github.tangyi.file.mapper.AttachmentMapper;
import com.github.tangyi.file.uploader.UploadInvoker;
import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gaokx
 * @date 2020/11/30 23:55
 */
@Slf4j
@AllArgsConstructor
@Service
public class AttachmentService extends CrudService<AttachmentMapper, Attachment> {

	/**
	 * 根据id查询
	 *
	 * @param attachment attachment
	 * @return Attachment
	 */
//	@Cacheable(value = "attachment#" + CommonConstant.CACHE_EXPIRE, key = "#attachment.id")
	@Override
	public Attachment get(Attachment attachment) {
		return super.get(attachment);
	}

	/**
	 * 根据id更新
	 *
	 * @param attachment attachment
	 * @return int
	 */
	@Override
	@Transactional
	@CacheEvict(value = {"attachment", "attachment_preview"}, key = "#attachment.id")
	public int update(Attachment attachment) {
		return super.update(attachment);
	}

	/**
	 * 下载
	 *
	 * @param attachment attachment
	 * @return InputStream
	 */
	public InputStream download(Attachment attachment) throws Exception {
		// 下载附件
		return UploadInvoker.getInstance().download(attachment);
	}

	/**
	 * 删除
	 *
	 * @param attachment attachment
	 * @return int
	 */
	@Override
	@Transactional
	@CacheEvict(value = {"attachment", "attachment_preview"}, key = "#attachment.id")
	public int delete(Attachment attachment) {
		return super.delete(attachment);
	}

	/**
	 * 批量删除
	 *
	 * @param ids ids
	 * @return int
	 */
	@Override
	@Transactional
	@CacheEvict(value = {"attachment", "attachment_preview"}, allEntries = true)
	public int deleteAll(Long[] ids) {
		return super.deleteAll(ids);
	}

	/**
	 * 获取附件的预览地址
	 *
	 * @param attachment attachment
	 * @return String
	 * @author tangyi
	 * @date 2019/06/21 17:45
	 */
	@Cacheable(value = "attachment_preview#" + CommonConstant.CACHE_EXPIRE, key = "#attachment.id")
	public String getPreviewUrl(Attachment attachment) {
		attachment = this.get(attachment);
		if (attachment != null) {
			String preview = attachment.getPreviewUrl();
			if (StringUtils.isNotBlank(preview) && !preview.startsWith("http")) {
				preview = "http://" + preview;
			} else {
				preview = AttachmentConstant.ATTACHMENT_PREVIEW_URL + attachment.getId();
			}
			log.debug("GetPreviewUrl id: {}, preview url: {}", attachment.getId(), preview);
			return preview;
		}
		return "";
	}
}
