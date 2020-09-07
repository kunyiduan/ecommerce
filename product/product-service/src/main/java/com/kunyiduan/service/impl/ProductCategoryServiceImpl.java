package com.kunyiduan.service.impl;

import com.kunyiduan.entity.ProductCategory;
import com.kunyiduan.mapper.ProductCategoryMapper;
import com.kunyiduan.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类信息表 服务实现类
 * </p>
 *
 * @author achilles
 * @since 2020-09-07
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

}
