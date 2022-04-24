package com.juyss.controller;

import com.juyss.pojo.Movie;
import com.juyss.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MovieController
 * @Desc: 提供服务
 * @package com.juyss.controller
 * @project HelloWorld
 * @date 2020/10/29 20:12
 */
@RestController
public class MovieController {

    @Value("${server.port}")
    private Integer port;

    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 获取Movie
     */
    @GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable("id")Integer id){
        System.out.println("访问了端口为"+port+"的分布式服务");
        System.out.println("获取参数=====>"+id);
        Movie movie = movieService.getMovie(id);
        System.out.println("movie=====>"+movie);
        return movie;
    }

}
