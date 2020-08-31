package com.kunyiduan.bean.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/08/31 13:43:00
 */
@ApiModel("创建商品Param")
@Data
@NoArgsConstructor
public class ProductParam {

    @ApiModelProperty(required = true, position = 1, value = "商品名字")
    @NotBlank(message = "商品名字 不能为空")
    private String name;

    @ApiModelProperty(required = true, position = 2, value = "商品价格")
    @NotNull(message = "商品价格 不能为空")
    private BigDecimal price;

    @ApiModelProperty(required = true, position = 3, value = "市场价格")
    @NotNull(message = "市场价格 不能为空")
    private BigDecimal marketPrice;

    @ApiModelProperty(required = true, position = 4, value = "是否送积分")
    @NotNull(message = "是否送积分标识 不能为空")
    private boolean sendPoints;

    @ApiModelProperty(required = true, position = 5, value = "可购买数量")
    @NotNull(message = "可购买数量 不能为空")
    private int quantity;

    @ApiModelProperty(required = false, position = 6, value = "商品描述")
    private String description;

}
