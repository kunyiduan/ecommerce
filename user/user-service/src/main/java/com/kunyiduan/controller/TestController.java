package com.kunyiduan.controller;

import com.kunyiduan.bean.amap.AmapResponse;
import com.kunyiduan.bean.amap.VehicleBackVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@Api(description = "高德测试", tags = {"amap"})
@Slf4j
public class TestController {

    @GetMapping("/amap")
    @ApiOperation("测试高德API")
    public void testAmapDistance() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://restapi.amap.com/v3/distance?origins=106.4978000,29.5044680|106.6271680,29.6255160|106.4607840,29.4000000|106.4607840,29.4000000|106.6271680,29.6255160|106.6271680,29.6255160|106.6271680,29.6255160&destination=106.4900,29.6700&output=json&key=bd8b66683ad4fbb2c330d8bb26f176b1";
        ResponseEntity<AmapResponse> entity = restTemplate.getForEntity(url, AmapResponse.class);
        if (HttpStatus.OK.equals(entity.getStatusCode()) && "1".equals(entity.getBody().getStatus()) && !"0".equals(entity.getBody().getCount())) {
            List<VehicleBackVo> temp = entity.getBody().getResults().stream().map(x -> {
                VehicleBackVo vehicleBackVo = VehicleBackVo.builder()
                        .distance(new BigDecimal(x.getDistance()))
                        .duration(new BigDecimal(x.getDuration()))
                        .build();
                return vehicleBackVo;
            }).collect(Collectors.toList());
            log.info("result ------------------ {}", temp);
        }
    }
}
