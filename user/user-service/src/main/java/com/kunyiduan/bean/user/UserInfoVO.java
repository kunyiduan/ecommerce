package com.kunyiduan.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by duankunyi on 2020/7/12.
 */
@ApiModel("响应用户信息VO")
@Data
public class UserInfoVO {

    @ApiModelProperty("手机号")
    private String telephone;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像地址")
    private String photo;

}
