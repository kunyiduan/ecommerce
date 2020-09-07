package com.kunyiduan.mapper;

import com.kunyiduan.entity.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author achilles
 * @since 2020-09-07
 */
@Mapper
@Repository
public interface BrandMapper extends BaseMapper<Brand> {

}
