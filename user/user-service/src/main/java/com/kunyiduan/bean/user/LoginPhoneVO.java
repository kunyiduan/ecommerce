package com.kunyiduan.bean.user;

import com.kunyiduan.annotation.phone.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Created by duankunyi on 2020/7/12.
 */
@ApiModel("手机号登录VO")
@Getter
@Setter
@NoArgsConstructor
public class LoginPhoneVO {

    @ApiModelProperty(required = true,position = 1,value = "手机号")
    @Phone
    private String telephone;

    @ApiModelProperty(required = true,position = 2,value = "密码")
    @Length(min = 64,max = 64,message = "密码输入错误")
    private String password;

}
