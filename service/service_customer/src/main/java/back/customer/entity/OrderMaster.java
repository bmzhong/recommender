package back.customer.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OrderMaster对象", description = "")
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "订单编号 yyyymmddnnnnnnnn")
    private Long orderSn;

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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "收货时间")
    private Date receiveTime;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单积分")
    private Integer orderPoint;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;


}
