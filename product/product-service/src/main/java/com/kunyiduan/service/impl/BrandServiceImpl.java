package com.kunyiduan.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyiduan.bean.brand.BrandParam;
import com.kunyiduan.entity.Brand;
import com.kunyiduan.mapper.brand.BrandMapper;
import com.kunyiduan.service.BrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author achilles
 * @since 2020-09-07
 */
@Service
@DS("brand")
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class, transactionManager = "brandTransactionManager")
    public boolean createBrand(BrandParam brandParam) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandParam, brand);
        if (!StringUtils.isEmpty(brandParam.getPicture())) {
            brand.setPicCrc((long) brandParam.getPicture().hashCode() + Integer.MAX_VALUE + 1);
        }
        brand.setCreateTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());
        final int count = brandMapper.insert(brand);
        return count == 1 ? true : false;
    }
}
