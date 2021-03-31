package com.kunyiduan.bean.points;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by duankunyi on 2020/7/15.
 */
@ApiModel("积分Param")
@Data
@NoArgsConstructor
public class PointsParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, position = 1, value = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty(required = true, position = 2, value = "积分")
    @Min(value = 0, message = "积分不能为负数")
    private Integer points;

    public PointsParam(@NotNull(message = "用户ID不能为空") Long userId, @Min(value = 0, message = "积分不能为负数") Integer points) {
        this.userId = userId;
        this.points = points;
    }

}
