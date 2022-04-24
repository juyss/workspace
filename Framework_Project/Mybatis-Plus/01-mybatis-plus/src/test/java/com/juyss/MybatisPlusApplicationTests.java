package com.juyss;

import com.juyss.mapper.BookMapper;
import com.juyss.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Resource
    BookMapper bookMapper;

    @Test
    void contextLoads() {
        List<Book> list = bookMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
