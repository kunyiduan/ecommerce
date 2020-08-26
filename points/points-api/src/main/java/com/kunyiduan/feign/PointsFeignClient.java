package com.kunyiduan.feign;

import com.kunyiduan.bean.PointsParam;
import com.kunyiduan.fallback.PointsFeignFallback;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "POINTS", fallbackFactory = PointsFeignFallback.class)
@RequestMapping("/points")
@ResponseBody
@Api("积分对外API")
public interface PointsFeignClient {

    @ApiOperation("添加积分")
    @PostMapping(value = "/create")
    Boolean create(@RequestBody @Validated PointsParam pointsParam);

}
