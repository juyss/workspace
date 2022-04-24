package com.juyss.controller;

import com.juyss.mapper.UserMapper;
import com.juyss.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserController
 * @Desc:
 * @package com.juyss.controller
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 11:10
 */
@RestController
public class UserController {

    private UserMapper mapper;

    @Autowired
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @RequestMapping("/get")
    public List<Person> get(){
        List<Person> people = mapper.getAll();
        System.out.println(people);
        return people;
    }

}
