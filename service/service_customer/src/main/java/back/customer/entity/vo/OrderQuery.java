package back.customer.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OrderQuery对象", description = "vo对象，传递订单查询信息")
public class OrderQuery {
    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "用户地址表中的地址ID")
    private Integer customerAddrId;

    @ApiModelProperty(value = "下单时间")
    private Date createTime;

    @ApiModelProperty(value = "订单状态，用户未确认0，用户已确认但商家未发货1，用户已确认商家已发货2，订单已完成3")
    private Integer orderStatus;

    @ApiModelProperty(value = "商品名称")
    private String productName;
}
