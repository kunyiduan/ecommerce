package com.kunyiduan.repository;

import com.kunyiduan.bean.SearchParam;
import com.kunyiduan.bean.SearchVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/10 10:15:00
 */
@Component
public interface SearchRepository extends ElasticsearchRepository<SearchRepository,Long> {

    List<SearchVO> listProductByName(SearchParam searchParam);

}
