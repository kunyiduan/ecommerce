package com.kunyiduan.service.impl;

import com.kunyiduan.bean.points.PointsVO;
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
    //@Async默认使用单线程化的线程池，无法重用线程，故通常会常见可缓存的线程池
    @Async("pointsPool")
    public Boolean create(PointsVO pointsVO) {
        Points points = new Points();
        BeanUtils.copyProperties(pointsVO,points);
        Date date = new Date();
        points.setCreateTime(date);
        points.setUpdateTime(date);
        log.info(points.toString());
        int count = pointsMapper.insert(points);
        return count == 1 ? true : false;
    }
}
