package com.kunyiduan.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by duankunyi on 2020/7/12.
 */
@ApiModel("用户信息VO")
@Data
@NoArgsConstructor
public class UserInfoVO {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("手机号")
    private String telephone;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像地址")
    private String photo;

}
