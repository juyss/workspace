package com.github.tangyi.file.api.feign;

import com.github.tangyi.common.core.constant.ServiceConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.feign.config.CustomFeignConfig;
import com.github.tangyi.file.api.feign.factory.BaseServiceClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = ServiceConstant.BASE_SERVICE, configuration = CustomFeignConfig.class, fallbackFactory = BaseServiceClientFallbackFactory.class)
public interface BaseServiceClient {

    @GetMapping("institutionHistory/genericInstitutionHistory")
    String institutionHistoryPage(@RequestParam List<String> docNoList);

    @GetMapping("entp/field")
    ResponseBean<?> field();
}
