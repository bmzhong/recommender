package back.customer.controller;

import back.common_utils.R;
import back.customer.entity.OrderCart;
import back.customer.service.OrderCartService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/customer/order-cart")

public class OrderCartController {

    @Autowired
    private OrderCartService orderCartService;

    /*
     * 功能描述:
     * 〈增加购物车的商品〉
     *
     * @author : yzh
     * @date : 2021-5-29
     */
    //增加购物车的商品
    @ApiOperation(value = "增加购物车商品")
    @PostMapping("addCart")
    public R addCart(@ApiParam(value = "商品信息",required = true )
                     @RequestBody OrderCart ordercart){
        boolean save = orderCartService.save(ordercart);
        if (save)
            return R.ok().data("cartID",ordercart.getCartId());
        else
            return R.error();
    }

    /*
     * 功能描述:
     * 〈删除购物车的商品〉
     *
     * @author : yzh
     * @date : 2021-5-29
     */
    //删除购物车的商品
    @ApiOperation(value = "删除购物车商品")
    @DeleteMapping("/removeCart/{productId}")
    public R removeCart(@ApiParam(name = "productId", value = "商品id", required = true )
                        @PathVariable Integer productId){
        boolean remove = orderCartService.removeById(productId);
        if (remove)
            return R.ok();
        else
            return R.error();
    }

    /*
     * 功能描述:
     * 〈查看购物车〉
     *
     * @author : yzh
     * @date : 2021-5-29
     */
    //根据用户id查看购物车
    @ApiOperation(value = "查看购物车")
    @GetMapping("/findCart/{id}")
    public R findCart(@ApiParam(name = "id" , value = "用户id" , required = true )
                      @PathVariable Integer id){
        QueryWrapper<OrderCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id", id);
        OrderCart orderCart = orderCartService.getOne(queryWrapper);
        if ( orderCart == null)
            return R.error().data("msg", "no data found");
        else
            return R.ok().data("orderCart", orderCart);
    }
}

