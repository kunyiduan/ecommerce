package com.kunyiduan.controller;


import com.kunyiduan.bean.brand.BrandParam;
import com.kunyiduan.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author achilles
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/brand")
@Api(value = "brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiOperation(value = "创建品牌")
    @PostMapping("/create")
    public boolean createBrand(@Validated @RequestBody BrandParam brandParam) {
        boolean result = brandService.createBrand(brandParam);
        return result;
    }

}
