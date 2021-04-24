package back.customer.controller;


import back.common_utils.R;
import back.customer.entity.OrderMaster;
import back.customer.entity.vo.Order;
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
    public R findAllOrderMaster(@ApiParam(name = "id", value = "用户id", required = true)
                         @PathVariable Integer id) {
        QueryWrapper<OrderMaster> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", id);
        List<OrderMaster> list = orderMasterService.list(wrapper);
        if (list.isEmpty())
            return R.error().data("msg", "no data found");
        else
            return R.ok().data("list", list);
    }

    @ApiOperation(value = "通过订单号查询订单")
    @GetMapping("findOrderById/{orderSn}")
    public R findOrderById(@ApiParam(name = "orderSn", value = "订单编号", required = true)
                           @PathVariable String orderSn) {
        QueryWrapper<OrderMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_sn", orderSn);
        OrderMaster orderMaster = orderMasterService.getOne(queryWrapper);
        if (orderMaster == null)
            return R.error().data("msg", "no data found");
        else
            return R.ok().data("orderMaster", orderMaster);
    }

    @ApiOperation(value = "添加订单")
    @PostMapping("addOrder")
    public R addOrder(@ApiParam(value = "订单信息", required = true)
                      @RequestBody Order order) {
        boolean add = orderMasterService.addOrder(order);
        if (add)
            return R.ok();
        else
            return R.error();
    }

}

