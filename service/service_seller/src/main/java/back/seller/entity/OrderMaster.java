package back.seller.entity;

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
 * @author testjava
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderMaster对象", description="")
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "用户ID")
    private Integer customerId;

    @ApiModelProperty(value = "用户地址表中的地址ID")
    private Integer customerAddrId;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderMoney;

    @ApiModelProperty(value = "快递公司名称")
    private String shippingCompName;

    @ApiModelProperty(value = "快递单号")
    private String shippingSn;

    @ApiModelProperty(value = "下单时间")
    private Date createTime;

    @ApiModelProperty(value = "收货时间")
    private Date receiveTime;

    @ApiModelProperty(value = "订单状态，用户未支付0，用户已支付但商家未发货1，用户已确认商家已发货2，订单已完成3")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单积分")
    private Integer orderPoint;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
