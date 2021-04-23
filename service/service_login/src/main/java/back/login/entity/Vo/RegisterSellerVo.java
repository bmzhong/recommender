package back.login.entity.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterSellerVo {

    @ApiModelProperty(value = "用户登录名")
    private String loginName;

    @ApiModelProperty(value = "md5加密的密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String mobilePhone;

    @ApiModelProperty(value = "真实姓名")
    private String trueName;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
