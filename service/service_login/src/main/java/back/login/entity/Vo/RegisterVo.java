package back.login.entity.Vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


//注册vo
@Data
public class RegisterVo {

    @ApiModelProperty(value = "用户登录名")
    private String loginName;

    @ApiModelProperty(value = "md5加密的密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String mobilePhone;
}
