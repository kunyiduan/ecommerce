package com.kunyiduan.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by duankunyi on 2020/8/26.
 */
@ApiModel("service层输出业务对象")
@Data
@NoArgsConstructor
public class UserBO {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("手机号")
    private String telephone;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像地址")
    private String photo;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;

}

