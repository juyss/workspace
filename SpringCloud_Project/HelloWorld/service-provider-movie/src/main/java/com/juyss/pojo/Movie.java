package com.juyss.pojo;

import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Movie
 * @Desc:
 * @package com.juyss.pojo
 * @project HelloWorld
 * @date 2020/10/29 20:26
 */
@Component
public class Movie {

    private Integer id;
    private String name;

    public Movie() {
    }

    public Movie(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
