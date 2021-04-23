package back.customer.controller;


import back.common_utils.R;
import back.customer.entity.OrderMaster;
import back.customer.service.OrderMasterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/customer/order-master")
@CrossOrigin
public class OrderMasterController {

    @Autowired
    OrderMasterService orderMasterService;

    //查询所有订单
    @ApiOperation(value = "查询该用户所有订单")
    @PostMapping("findAllOrderMaster/{id}")
    public R findAllAddr(@ApiParam(name = "id", value = "用户id", required = true)
                         @PathVariable Integer id) {
        QueryWrapper<OrderMaster> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", id);
        List<OrderMaster> list = orderMasterService.list(wrapper);
        return R.ok().data("list", list);
    }

    @ApiOperation(value = "通过订单号查询订单")
    @GetMapping("findOrderById/{orderId")
    public R findOrderById(@ApiParam(name = "orderId", value = "订单号", required = true)
                           @PathVariable Integer orderId) {
        OrderMaster orderMaster = orderMasterService.getById(orderId);
        return R.ok().data("orderMaster",orderMaster);
    }

    @ApiOperation(value = "添加订单")
    @PostMapping("addOrder/{customerId}/{productId}/{count}")
    public R addOrder(@ApiParam(name = "customerId", value = "用户Id", required = true)
                      @PathVariable Integer customerId,
                      @ApiParam(name = "productId", value = "商品Id", required = true)
                      @PathVariable Integer productId,
                      @ApiParam(name = "count", value = "商品数量", required = true)
                      @PathVariable Integer count) {

        return R.ok();
    }

}

