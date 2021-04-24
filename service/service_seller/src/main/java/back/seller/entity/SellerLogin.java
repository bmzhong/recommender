package back.seller.entity;

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
 * @author testjava
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SellerLogin对象", description="")
public class SellerLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商家ID")
    @TableId(value = "seller_id", type = IdType.AUTO)
    private Integer sellerId;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "商家名称")
    private String sellerName;

    @ApiModelProperty(value = "md5加密的密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String sellerTrueName;

    @ApiModelProperty(value = "手机号")
    private String mobilePhone;

    @ApiModelProperty(value = "邮箱")
    private String sellerEmail;

    @ApiModelProperty(value = "用户状态")
    private Integer sellerStats;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;

    @ApiModelProperty(value = "注册时间")
    private Date registerTime;


}
