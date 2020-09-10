package com.kunyiduan.controller;

import com.kunyiduan.bean.SearchParam;
import com.kunyiduan.bean.SearchVO;
import com.kunyiduan.repository.SearchRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/10 09:34:00
 */
@RestController
@RequestMapping("/product/search")
@Api(value = "productSearch")
public class SearchController {

    @Autowired
    private SearchRepository searchRepository;

    @ApiOperation(value = "通过名字搜索商品列表-分页显示")
    @GetMapping("/listProduct")
    public List<SearchVO> listProductByName(@Validated @RequestBody SearchParam searchParam) {
        List<SearchVO> result = searchRepository.listProductByName(searchParam);
        return result;
    }

}
