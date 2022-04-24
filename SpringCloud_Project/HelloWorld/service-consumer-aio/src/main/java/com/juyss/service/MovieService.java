package com.juyss.service;

import com.juyss.pojo.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserService
 * @Desc:
 * @package com.juyss.service
 * @project HelloCloud
 * @date 2020/10/30 16:55
 */
@FeignClient("service-provider-movie")
public interface MovieService {

    @GetMapping("/movie/{id}")
    Movie getMovie(@PathVariable("id")Integer id);

}
