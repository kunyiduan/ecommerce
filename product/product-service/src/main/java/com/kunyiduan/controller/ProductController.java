package com.kunyiduan.controller;


import com.kunyiduan.bean.product.ProductParam;
import com.kunyiduan.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author achilles
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/product")
@Slf4j
@Api(value = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("/创建商品")
    @PostMapping("/create")
    public boolean createProduct(@Validated @RequestBody ProductParam productParam) {
        final boolean result = productService.insertProduct(productParam);
        if(log.isDebugEnabled()){
            log.debug("result of the createProduct is ------------" + result);
        }
        return result;
    }



}
