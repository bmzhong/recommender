package back.seller.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ProductCommentVo {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "给这个商品几颗星，5星最好，0星最差")
    private Integer star;
}
