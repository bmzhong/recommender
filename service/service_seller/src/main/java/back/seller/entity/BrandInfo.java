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
@ApiModel(value="BrandInfo对象", description="")
public class BrandInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌ID")
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Integer brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "品牌logo URL")
    private String brandLogo;

    @ApiModelProperty(value = "品牌描述")
    private String brandDesc;

    @ApiModelProperty(value = "品牌状态,0禁用,1启用")
    private Integer brandStatus;

    @ApiModelProperty(value = "知名度")
    private Integer brandOrder;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
