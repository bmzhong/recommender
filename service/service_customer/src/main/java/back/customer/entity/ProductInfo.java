package back.customer.entity;

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
 * @author plyn
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductInfo对象", description="")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "品牌表中的ID")
    private Integer brandId;

    @ApiModelProperty(value = "分类信息表的ID")
    private Integer categoryId;

    @ApiModelProperty(value = "商家ID")
    private Integer sellerId;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "上下架状态：0下架1上架")
    private Integer publishStatus;

    @ApiModelProperty(value = "审核状态：0未审核，1已审核")
    private Integer auditStatus;

    @ApiModelProperty(value = "生产日期")
    private Date productionDate;

    @ApiModelProperty(value = "商品有效期")
    private Integer shelfLife;

    @ApiModelProperty(value = "商品描述")
    private String descript;

    @ApiModelProperty(value = "商品录入时间")
    private Date indate;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
