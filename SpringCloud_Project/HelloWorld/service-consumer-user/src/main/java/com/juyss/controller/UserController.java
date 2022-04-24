package com.juyss.controller;

import com.juyss.pojo.Movie;
import com.juyss.pojo.User;
import com.juyss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserController
 * @Desc:
 * @package com.juyss.controller
 * @project HelloWorld
 * @date 2020/10/29 21:19
 */
@RestController
public class UserController {

    private UserService userService;


    private RestTemplate restTemplate;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id")Integer id){

        return userService.getUser(id);
    }

    @GetMapping("/buy/{id}")
    public Map<String,Object> buyTicket(@PathVariable("id")Integer id){
        HashMap<String, Object> map = new HashMap<>();
        User user = userService.getUser(id);
        Movie movie = restTemplate.getForObject("http://service-provider-movie/movie/" + id, Movie.class);
        System.out.println("user=====>"+user);
        System.out.println("movie=====>"+movie);
        map.put("User", user);
        map.put("Movie", movie);
        return map;
    }
}
