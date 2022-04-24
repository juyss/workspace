package com.juyss.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Book
 * @Desc: book表实体类
 * @package com.juyss.pojo
 * @project SSM-Merge
 * @date 2020/9/13 15:46
 */
@Component
public class Book {

    private Integer id;
    private String name;
    private Integer count;
    private String detail;

    public Book() {
        System.out.println("调用了Book类无参构造器");
    }

    public Book(Integer id, String name, Integer count, String detail) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", detail='" + detail + '\'' +
                '}';
    }
}
