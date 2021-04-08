package back.customer.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author plyn
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CustomerInf对象", description="")
public class CustomerInf implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键ID")
    @TableId(value = "customer_inf_id", type = IdType.AUTO)
    private Integer customerInfId;

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

    @ApiModelProperty(value = "注册时间")
    private Date registerTime;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "用户余额")
    private BigDecimal userMoney;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;

    private String customerInfcol;


}
