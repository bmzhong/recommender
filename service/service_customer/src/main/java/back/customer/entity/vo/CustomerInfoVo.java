package back.customer.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "customerInfo", description = "vo对象，获取用户编辑信息")
public class CustomerInfoVo {
    @ApiModelProperty(value = "customer_login表的自增ID")
    private Integer customerId;

    @ApiModelProperty(value = "用户真实姓名")
    private String customerTrueName;

    @ApiModelProperty(value = "证件类型：1 身份证，2 军官证，3 护照")
    private Integer identityCardType;

    @ApiModelProperty(value = "证件号码")
    private String identityCardNo;

    @ApiModelProperty(value = "手机号")
    private String mobilePhone;

    @ApiModelProperty(value = "邮箱")
    private String customerEmail;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "生日")
    private String birthday;
}
