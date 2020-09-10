package com.kunyiduan.repository.impl;

import com.kunyiduan.bean.SearchParam;
import com.kunyiduan.bean.SearchVO;
import com.kunyiduan.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.List;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/10 11:09:00
 */
public class SearchRepositoryImpl implements SearchRepository {

    @Override
    public List<SearchVO> listProductByName(SearchParam searchParam) {

        return null;
    }
}
