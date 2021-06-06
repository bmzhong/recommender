package back.seller.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SellerOrderVo {

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "购买商品数量")
    private Integer productCnt;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "邮编")
    private String zip;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
}
