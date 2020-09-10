package com.kunyiduan.service;

import com.kunyiduan.bean.SearchParam;
import com.kunyiduan.bean.SearchVO;

import java.util.List;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/10 11:44:00
 */
public interface SearchService {

    List<SearchVO> listProductByName(SearchParam searchParam);

}
