package com.kunyiduan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kunyiduan.bean.brand.BrandParam;
import com.kunyiduan.entity.Brand;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author achilles
 * @since 2020-09-07
 */
public interface BrandService extends IService<Brand> {

    boolean createBrand(BrandParam brandParam);
}
