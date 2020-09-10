package com.kunyiduan.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/10 11:15:00
 */
@Data
@NoArgsConstructor
@ApiModel(value = "商品搜索Param")
public class SearchParam extends PaginationVO {

    @ApiModelProperty(value = "商品名字")
    @NotBlank(message = "商品名字 不能为空")
    private String name;

}
