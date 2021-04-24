package back.seller.entity;

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
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductPicInfo对象", description="")
public class ProductPicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品图片ID")
    @TableId(value = "product_pic_id", type = IdType.AUTO)
    private Integer productPicId;

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

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
