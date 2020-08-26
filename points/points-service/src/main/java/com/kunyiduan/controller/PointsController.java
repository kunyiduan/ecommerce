package com.kunyiduan.controller;

import com.kunyiduan.bean.points.PointsParam;
import com.kunyiduan.service.PointsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author achilles
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/points")
@Api(description = "积分",tags = {"points"})
public class PointsController {

    @Autowired
    private PointsService pointsService;

    @ApiOperation("添加积分")
    @PostMapping("/create")
    public Boolean create(@RequestBody @Validated PointsParam pointsParam){
        Boolean flag = pointsService.create(pointsParam);
        return flag;
    }

}
