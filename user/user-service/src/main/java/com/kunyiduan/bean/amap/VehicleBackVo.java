package com.kunyiduan.bean.amap;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class VehicleBackVo {

    private BigDecimal distance;

    private String vehicleCode;

    private BigDecimal duration;

}
