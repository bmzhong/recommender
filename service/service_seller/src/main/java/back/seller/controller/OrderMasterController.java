package back.seller.controller;


import back.common_utils.R;
import back.seller.entity.OrderMaster;
import back.seller.entity.vo.SellerOrderVo;
import back.seller.service.OrderMasterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/seller/order-master")
public class OrderMasterController {

    @Autowired
    OrderMasterService orderMasterService;

    /**
     * @param sellerId
     * @return
     * @author 钟保明
     */
    @ApiOperation(value = "查询商家的订单信息")
    @GetMapping("orderInfo/{sellerId}")
    public R getSellerOrder(@ApiParam(name = "sellerId", value = "商家id")
                            @PathVariable Integer sellerId) {
        List<SellerOrderVo> sellerOrderVos = orderMasterService.getSellerOrder(sellerId);
        if (null == sellerOrderVos) {
            return R.error();
        } else {
            return R.ok().data("sellerOrderVos", sellerOrderVos);
        }
    }


    /**
     * @author 钟保明
     * @param orderId
     * @param shipping_comp_name
     * @return
     */
    @GetMapping("delivery/{orderId}/{shipping_comp_name}")
    public R delivery(@ApiParam(name = "orderId", value = "订单号")
                      @PathVariable String orderId,
                      @ApiParam(name = "shipping_comp_name", value = "快递公司名称")
                      @PathVariable String shipping_comp_name) {
        OrderMaster orderMaster = orderMasterService.getById(orderId);
        if (null == orderMaster) {
            return R.error();
        }
        orderMaster.setShippingCompName(shipping_comp_name);
        orderMaster.setOrderStatus(1);
        String shipping_sn = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        orderMaster.setShippingSn(shipping_sn);
        orderMasterService.updateById(orderMaster);
        return R.ok().data("shipping_sn", shipping_sn);
    }
}

