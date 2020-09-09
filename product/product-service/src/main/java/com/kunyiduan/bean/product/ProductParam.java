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

    @ApiModelProperty(required = true, position = 0, value = "商品名字")
    @NotBlank(message = "商品名字 不能为空")
    private String name;

    @ApiModelProperty(required = true, position = 1, value = "商品价格")
    @NotNull(message = "商品价格 不能为空")
    private BigDecimal price;

    @ApiModelProperty(required = false, position = 2, value = "促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(required = true, position = 3, value = "品牌ID")
    @NotNull(message = "品牌ID 不能为空")
    private int brandId;

    @ApiModelProperty(required = true, position = 4, value = "商品分类ID")
    @NotNull(message = "商品分类ID 不能为空")
    private int categoryId;

    @ApiModelProperty(required = true, position = 5, value = "是否送积分")
    @NotNull(message = "是否送积分标识 不能为空")
    private boolean sendPoints;

    @ApiModelProperty(required = true, position = 6, value = "可购买数量")
    @NotNull(message = "可购买数量 不能为空")
    private int quantity;

    @ApiModelProperty(required = false, position = 7, value = "商品图片地址")
    private String picture1;

    @ApiModelProperty(required = false, position = 8, value = "商品描述")
    private String description;

}
