package com.juyss.service;

import com.juyss.dao.MovieDao;
import com.juyss.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MovieService
 * @Desc:
 * @package com.juyss.service
 * @project HelloWorld
 * @date 2020/10/29 20:32
 */
@Service
public class MovieService {

    private MovieDao movieDao;

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    /**
     * 获取Movie
     */
    public Movie getMovie(Integer id){
        return movieDao.getMovie(id);
    }
}
