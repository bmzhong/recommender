package back.customer.controller;


import back.common_utils.R;
import back.customer.entity.OrderDetail;
import back.customer.service.OrderDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/customer/order-detail")
@CrossOrigin
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    //查询选中订单的详情信息
    @ApiOperation(value = "查询选中订单的详情信息")
    @PostMapping("findOrderDetailById/{orderId}")
    public R findOrderDetailById(@ApiParam(name = "orderId",value = "订单ID", required = true)
                                 @PathVariable Integer orderId) {
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        OrderDetail orderDetail = orderDetailService.getOne(queryWrapper);
        if (orderDetail==null)
            return R.error().data("msg","no data found");
        else
            return R.ok().data("orderDetail",orderDetail);
    }

    //通过商品名称模糊查询订单
    @ApiOperation(value = "通过商品关键字模糊查询订单")
    @GetMapping("findOrderByProductName/{productName}")
    public R findOrderByProductName(@ApiParam(name = "productName", value = "商品关键字", required = true)
                                    @PathVariable String productName) {
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("product_name",productName);
        List<Object> list = orderDetailService.listObjs(queryWrapper);
        if (list.isEmpty())
            return R.error().data("msg","no data found");
        else
            return R.ok().data("list", list);
    }

}

