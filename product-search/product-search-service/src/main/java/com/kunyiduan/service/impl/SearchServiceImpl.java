package com.kunyiduan.service.impl;

import com.kunyiduan.bean.SearchParam;
import com.kunyiduan.bean.SearchVO;
import com.kunyiduan.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/10 11:45:00
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<SearchVO> listProductByName(SearchParam searchParam) {
        return null;
    }
}
