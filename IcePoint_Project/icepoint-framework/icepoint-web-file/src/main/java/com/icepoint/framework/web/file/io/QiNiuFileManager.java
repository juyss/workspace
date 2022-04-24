package com.icepoint.framework.web.file.io;

import com.icepoint.framework.core.io.Serializers;
import com.icepoint.framework.web.file.config.QiNiuConfig;
import com.icepoint.framework.web.file.entity.FileMetadata;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 七牛云
 * @author tangyi
 * @date 2019/12/8 20:25
 */
@Slf4j
public class QiNiuFileManager extends AbstractFileManager{

	private Auth auth;

	private UploadManager uploadManager;

	private BucketManager bucketManager;

	private QiNiuConfig qiNiuConfig;

	private static QiNiuFileManager instance;

	public synchronized static QiNiuFileManager getInstance() {
		if (instance == null) {
			instance = new QiNiuFileManager();
		}
		return instance;
	}

	public QiNiuFileManager() {
		if (StringUtils.isNotBlank(qiNiuConfig.getAccessKey()) && StringUtils.isNotBlank(qiNiuConfig.getSecretKey())) {
			instance = new QiNiuFileManager();
			instance.auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
			Configuration config = new Configuration(Region.region2());
			instance.uploadManager = new UploadManager(config);
			instance.bucketManager = new BucketManager(instance.auth, config);
		}
	}

	/**
	 * 获取七牛云token
	 *
	 * @return String
	 */
	public String getQiNiuToken() {
		return auth.uploadToken(getInstance().qiNiuConfig.getBucket());
	}

	/**
	 * 上传七牛云
	 *
	 * @param uploadBytes 文件
	 * @param fileName         文件名 默认不指定key的情况下，以文件内容的hash值作为文件名
	 * @return String
	 */
	public String upload(byte[] uploadBytes, String fileName) {
		try {
			Response response = uploadManager.put(uploadBytes, fileName, getQiNiuToken());
			//解析上传成功的结果
			DefaultPutRet putRet = Serializers.json().deserialize(response.bodyString(), DefaultPutRet.class);
			return qiNiuConfig.getDomainOfBucket() + "/" + putRet.key;
		} catch (QiniuException ex) {
			log.error("upload to qiniu exception: {}", ex.getMessage(), ex);
		}
		throw new IllegalArgumentException("上传失败");
	}

	/**
	 * 获取图片url
	 *
	 * @param fileName fileName
	 * @return String
	 */
	public String getDownloadUrl(String fileName) throws UnsupportedEncodingException {
		String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
		String publicUrl = String.format("%s/%s", qiNiuConfig.getDomainOfBucket(), encodedFileName);
		return auth.privateDownloadUrl(publicUrl, qiNiuConfig.getExpire());
	}

	/**
	 * 删除附件
	 * @param fileName fileName
	 * @return boolean
	 */
	public boolean delete(String fileName) {
		try {
			bucketManager.delete(qiNiuConfig.getBucket(), fileName);
			return Boolean.TRUE;
		} catch (Exception e) {
			log.error("delete attachment exception:{}", e.getMessage(), e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 获取域名
	 * @return String
	 */
	public String getDomainOfBucket() {
		return qiNiuConfig.getDomainOfBucket();
	}

	@Override
	public String upload(InputStream in, FileMetadata metadata) throws IOException {
		Response response = instance.uploadManager.put(in, qiNiuConfig.getAccessKey(), getQiNiuToken(), null, null);
		DefaultPutRet putRet =  Serializers.json().deserialize(response.bodyString(), DefaultPutRet.class);
		return null;
	}

	@Override
	public void delete(FileMetadata metadata) throws IOException {

	}
}
