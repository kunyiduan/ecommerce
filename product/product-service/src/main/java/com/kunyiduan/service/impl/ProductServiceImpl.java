package com.kunyiduan.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyiduan.bean.product.ProductParam;
import com.kunyiduan.entity.Product;
import com.kunyiduan.mapper.product.ProductMapper;
import com.kunyiduan.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author achilles
 * @since 2020-07-13
 */
@Service
@DS("product")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class, transactionManager = "productTransactionManager")
    public boolean insertProduct(ProductParam productParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productParam, product);
        if (!StringUtils.isEmpty(productParam.getPicture1())) {
            //java hashCode越界后将为负数，MySQL有crc32函数-unsigned int-字段类型
            product.setPic1Crc((long) productParam.getPicture1().hashCode() + Integer.MAX_VALUE + 1);
        }
        product.setStatus(0);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        final int count = productMapper.insert(product);
        return count == 1 ? true : false;
    }
}
