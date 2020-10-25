package com.juyss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: DatabaseTest
 * @Desc: 测试数据库
 * @package com.juyss.controller
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 0:20
 */
@RestController
public class DatabaseTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/get")
    public List<Map<String, Object>> getAll(){
        String sql = "select * from daily.person";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println("数据源:====>"+jdbcTemplate.getDataSource());
        System.out.println("获取数据====>"+list);
        return list;
    }

}
