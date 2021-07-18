package com.kunyiduan.service.impl;

import com.kunyiduan.bean.points.PointsParam;
import com.kunyiduan.entity.Points;
import com.kunyiduan.mapper.PointsMapper;
import com.kunyiduan.service.PointsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author achilles
 * @since 2020-07-13
 */
@Service
@Slf4j
public class PointsServiceImpl extends ServiceImpl<PointsMapper, Points> implements PointsService {

    @Autowired
    private PointsMapper pointsMapper;

    @Override
    //@Async默认使用newSingleThreadExecutor，无法重用线程，故通常自定义线程池
//    @Async("pointsPool")
    public Boolean create(PointsParam pointsParam) {
        Points points = new Points();
        BeanUtils.copyProperties(pointsParam, points);
        Date date = new Date();
        points.setCreateTime(date);
        points.setUpdateTime(date);
        int count = pointsMapper.insert(points);
        return count == 1 ? true : false;
    }
}
