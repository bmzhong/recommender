/*
 *  Order.java, 2021-04-23
 *
 *  Copyright 2021  PENG L.Y.N, Inc. All rights reserved.
 */

package back.customer.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Order对象", description = "vo对象，传递订单信息")
public class Order {

    @ApiModelProperty(value = "订单编号 yyyymmddnnnnnnnn")
    private Long orderSn;

    @ApiModelProperty(value = "用户ID")
    private Integer customerId;

    @ApiModelProperty(value = "用户地址表中的地址ID")
    private Integer customerAddrId;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderMoney;

    @ApiModelProperty(value = "订单积分")
    private Integer orderPoint;

    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    @ApiModelProperty(value = "购买商品数量")
    private Integer productCnt;
}
