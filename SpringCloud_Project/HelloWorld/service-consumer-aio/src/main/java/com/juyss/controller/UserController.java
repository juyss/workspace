package com.juyss.controller;

import com.juyss.dao.UserDao;
import com.juyss.pojo.Movie;
import com.juyss.pojo.User;
import com.juyss.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MovieController
 * @Desc:
 * @package com.juyss.controller
 * @project HelloCloud
 * @date 2020/10/30 16:51
 */
@RestController
public class UserController {

    private UserDao userDao;

    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/buy/{id}")
    public Map<String,Object> buy(@PathVariable("id")Integer id){
        User user = userDao.getUser(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", user);
        Movie movie = movieService.getMovie(id);
        map.put("Movie", movie);
        System.out.println("map=====>"+map);
        return map;
    }

}
