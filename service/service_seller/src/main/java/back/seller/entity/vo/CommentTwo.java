package back.seller.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CommentVo对象", description="")
/**
 * 解决前端传送问题，避免修改ProductCommentVo类
 */
public class CommentTwo {
    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "用户ID")
    private Integer customerId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "给这个商品几颗星，5星最好，0星最差")
    private Integer star;
}
