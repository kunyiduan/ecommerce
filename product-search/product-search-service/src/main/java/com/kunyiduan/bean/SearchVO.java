package com.kunyiduan.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/10 10:29:00
 */
@Data
@NoArgsConstructor
@ApiModel(value = "商品搜索结果VO")
public class SearchVO {

    @ApiModelProperty("商品名称")
    private String productName;

}
