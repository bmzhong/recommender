package back.seller.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BrandInfoVo {
    @ApiModelProperty(value = "品牌ID")
    private Integer id;

    @ApiModelProperty(value = "品牌名称")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "品牌logo URL")
    private String logo;

    @ApiModelProperty(value = "品牌描述")
    private String desc;
}
