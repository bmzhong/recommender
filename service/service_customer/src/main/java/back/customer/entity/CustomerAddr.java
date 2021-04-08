package back.customer.entity;

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
@ApiModel(value="CustomerAddr对象", description="")
public class CustomerAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键ID")
    @TableId(value = "customer_addr_id", type = IdType.AUTO)
    private Integer customerAddrId;

    @ApiModelProperty(value = "customer_login表的自增ID")
    private Integer customerId;

    @ApiModelProperty(value = "邮编")
    private Integer zip;

    @ApiModelProperty(value = "省")
    private Integer province;

    @ApiModelProperty(value = "市")
    private Integer city;

    @ApiModelProperty(value = "区")
    private Integer district;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "是否默认")
    private Integer isDefault;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
