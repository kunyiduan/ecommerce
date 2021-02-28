package com.kunyiduan.bean.brand;

import com.kunyiduan.annotation.Landline;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/10/09 09:05:00
 */
@Data
@NoArgsConstructor
@ApiModel("创建品牌Param")
public class BrandParam {

    @ApiModelProperty(required = true, position = 0, value = "品牌名字")
    @NotBlank(message = "品牌名字 不能为空")
    private String name;

    @ApiModelProperty(required = true, position = 1, value = "联系方式")
    @Landline
    private String landline;

    @ApiModelProperty(required = true, position = 2, value = "国家")
    @NotBlank(message = "国家 不能为空")
    private String country;

    @ApiModelProperty(required = true, position = 3, value = "省份")
    @NotBlank(message = "省份 不能为空")
    private String province;

    @ApiModelProperty(required = true, position = 4, value = "城市")
    @NotBlank(message = "城市 不能为空")
    private String city;

    @ApiModelProperty(required = true, position = 5, value = "详细地址")
    @NotBlank(message = "详细地址 不能为空")
    private String address;

    @ApiModelProperty(required = false, position = 6, value = "图片地址")
    private String picture;

}
