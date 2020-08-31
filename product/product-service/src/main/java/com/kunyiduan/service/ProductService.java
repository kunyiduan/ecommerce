package com.kunyiduan.service;

import com.kunyiduan.bean.product.ProductParam;
import com.kunyiduan.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author achilles
 * @since 2020-07-13
 */
public interface ProductService extends IService<Product> {

    boolean insertProduct(ProductParam productParam);

}
