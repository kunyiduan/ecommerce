package com.kunyiduan.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kunyiduan.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author achilles
 * @since 2020-07-13
 */
@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {

}
