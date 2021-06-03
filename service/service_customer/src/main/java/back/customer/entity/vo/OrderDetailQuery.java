package back.customer.entity.vo;

import back.customer.entity.OrderDetail;
import back.customer.entity.OrderMaster;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OrderDetailQuery对象", description = "vo对象，向前端传递订单所有信息（detail+master）")
public class OrderDetailQuery {
    @ApiModelProperty(value = "订单ID")
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

    @ApiModelProperty(value = "订单状态，用户未确认0，用户已确认但商家未发货1，用户已确认商家已发货2，订单已完成3")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单积分")
    private Integer orderPoint;

    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    @ApiModelProperty(value = "购买商品数量")
    private Integer productCnt;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    public OrderDetailQuery(OrderMaster master, OrderDetail detail) {
        this.orderId = master.getOrderId();
        this.orderSn = master.getOrderSn();
        this.customerId = master.getCustomerId();
        this.customerAddrId = master.getCustomerAddrId();
        this.orderMoney = master.getOrderMoney();
        this.shippingCompName = master.getShippingCompName();
        this.shippingSn = master.getShippingSn();
        this.createTime = master.getCreateTime();
        this.receiveTime = master.getReceiveTime();
        this.orderStatus = master.getOrderStatus();
        this.orderPoint = master.getOrderPoint();
        this.productId = detail.getProductId();
        this.productCnt = detail.getProductCnt();
        this.productName = detail.getProductName();
    }

}
