package com.juyss.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Book
 * @Desc: 图书实体类
 * @package com.juyss.pojo
 * @project elasticsearch
 * @date 2020/12/30 18:48
 */
@Component
@Document(indexName = "es-demo") //储存的索引名称
@Data
public class Book {

    @Id
    private Integer id;

    // Text类型搜索会进行分词,其他类型不分词
    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date)
    private Date publishedTime;

}
