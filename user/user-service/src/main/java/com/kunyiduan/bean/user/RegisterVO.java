package com.kunyiduan.bean.user;

import com.kunyiduan.annotation.password.Password;
import com.kunyiduan.annotation.phone.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.Date;

@Getter
@Setter
@ApiModel("注册VO")
public class RegisterVO {

    @ApiModelProperty(required = true, position = 1, value = "电话号码")
    @Phone
    private String telephone;

    @ApiModelProperty(required = true, position = 2, value = "密码")
    @Password
    private String password;

    @ApiModelProperty(required = false, position = 3, value = "邮箱")
    @Email
    private String email;

    @ApiModelProperty(required = false, position = 4, value = "昵称")
    private String nickName;

    @ApiModelProperty(required = false, position = 5, value = "头像")
    private String photo;

    @ApiModelProperty(required = false, position = 6, value = "生日")
    private Date birthday;

}
