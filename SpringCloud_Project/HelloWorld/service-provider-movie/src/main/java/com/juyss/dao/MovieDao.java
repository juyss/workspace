package com.juyss.dao;

import com.juyss.pojo.Movie;
import org.springframework.stereotype.Repository;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MovieDao
 * @Desc:
 * @package com.juyss.dao
 * @project HelloWorld
 * @date 2020/10/29 20:28
 */
@Repository
public class MovieDao {

    /**
     * 获取Movie
     */
    public Movie getMovie(Integer id){
        Movie movie = new Movie();
        movie.setId(id);
        movie.setName("第"+id+"号Movie");
        return movie;
    }
}
