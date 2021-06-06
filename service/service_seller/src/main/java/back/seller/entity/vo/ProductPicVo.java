package back.seller.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductPicVo对象", description="前端向后端传递的商品图片VO类")
public class ProductPicVo {

    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    @ApiModelProperty(value = "图片描述")
    private String picDesc;

    @ApiModelProperty(value = "图片URL")
    private String picUrl;

    @ApiModelProperty(value = "是否主图：0.非主图1.主图")
    private Integer isMaster;

    @ApiModelProperty(value = "重要性")
    private Integer picOrder;

    @ApiModelProperty(value = "图片是否有效：0无效 1有效")
    private Integer picStatus;
}
