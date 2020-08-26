package com.kunyiduan.service;

import com.kunyiduan.bean.points.PointsParam;
import com.kunyiduan.entity.Points;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author achilles
 * @since 2020-07-13
 */
public interface PointsService extends IService<Points> {

    Boolean create(PointsParam pointsParam);

}
