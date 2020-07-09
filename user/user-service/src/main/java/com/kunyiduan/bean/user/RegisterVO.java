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
@ApiModel("密码注册VO")
public class RegisterVO {

    @ApiModelProperty(required = true, position = 1, value = "电话号码")
    @Phone
    private String telephone;

    @ApiModelProperty(required = true, position = 2, value = "密码")
//    @Pattern(regexp = "[.*[A-Z]+.*[a-z]+.*\\d+.*]{8,}|[.*[A-Z]+.*[0-9]+.*[a-z]+.*]{8,}|[.*[a-z]+.*[0-9]+.*[A-Z]+.*]{8,}|" +
//            "[.*[a-z]+.*[A-Z]+.*[0-9]+.*]{8,}|[.*[0-9]+.*[A-Z]+.*[a-z]+.*]{8,}|[.*[0-9]+.*[a-z]+.*[A-Z]+.*]{8,}"
//            , message = "密码长度大于8，同时至少包含大小写字符和数字")
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
