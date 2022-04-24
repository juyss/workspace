package com.juyss.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: FileUploadProperties
 * @Desc:
 * @package com.juyss.common.config
 * @project manager
 * @date 2021/1/12 18:55
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {

    private String path;
    private String url;
    private String accessUrl;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;

        //set accessUrl
        if (StringUtils.isEmpty(url)) {
            this.accessUrl = null;
        }
        this.accessUrl = url.substring(url.lastIndexOf("/")) + "/**";
        System.out.println("accessUrl=" + accessUrl);
    }

    public String getAccessUrl() {
        return accessUrl;
    }

}