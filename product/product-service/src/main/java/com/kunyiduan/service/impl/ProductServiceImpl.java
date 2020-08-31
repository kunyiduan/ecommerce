package com.kunyiduan.service.impl;

import com.kunyiduan.bean.product.ProductParam;
import com.kunyiduan.entity.Product;
import com.kunyiduan.mapper.ProductMapper;
import com.kunyiduan.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author achilles
 * @since 2020-07-13
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean insertProduct(ProductParam productParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productParam,product);
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setStatus(0);
        final int count = productMapper.insert(product);
        return count == 1 ? true : false;
    }
}
