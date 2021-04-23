package back.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value = "OrderCart对象", description = "")
public class OrderCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车ID")
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    @ApiModelProperty(value = "用户ID")
    private Integer customerId;

    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    @ApiModelProperty(value = "商品数量")
    private Integer productAmount;


}
