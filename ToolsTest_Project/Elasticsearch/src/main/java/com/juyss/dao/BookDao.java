package com.juyss.dao;

import com.juyss.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BookDao
 * @Desc:
 * @package com.juyss.dao
 * @project elasticsearch
 * @date 2020/12/30 19:09
 */
@Repository
public interface BookDao extends ElasticsearchRepository<Book, Integer> {
}
