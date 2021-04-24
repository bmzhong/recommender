package back.seller.entity.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ProductCategoryVo {

    @ApiModelProperty(value = "分类id")
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "父分类ID")
    private Integer parentId;

    @ApiModelProperty(value = "分类层级")
    private Integer categoryLevel;
}
