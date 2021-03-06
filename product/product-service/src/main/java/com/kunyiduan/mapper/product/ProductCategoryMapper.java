package com.kunyiduan.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kunyiduan.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品分类信息表 Mapper 接口
 * </p>
 *
 * @author achilles
 * @since 2020-09-07
 */
@Mapper
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

}
