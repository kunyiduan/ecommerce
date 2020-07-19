package com.kunyiduan.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@ApiModel("修改密码VO")
@Getter
@Setter
@NoArgsConstructor
public class PasswordVO {

    @ApiModelProperty(required = true,position = 1,value = "当前密码")
    @Length(min = 64, max = 64,message = "密码加密错误")
    private String currentPassword;

    @ApiModelProperty(required = true,position = 2,value = "新密码")
    @Length(min = 64, max = 64,message = "密码加密错误")
    private String newPassword;

}
