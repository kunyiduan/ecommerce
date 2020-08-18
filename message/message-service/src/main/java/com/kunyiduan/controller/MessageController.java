package com.kunyiduan.controller;

import com.kunyiduan.bean.points.PointsVO;
import com.kunyiduan.service.PointsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/message")
@Api(description = "积分",tags = {"message"})
public class PointsController {



}
