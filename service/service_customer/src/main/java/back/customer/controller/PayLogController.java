package back.customer.controller;


import back.common_utils.R;
import back.customer.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author xyc
 * @since 2021-05-15
 */
@RestController
@Api("支付功能")
@RequestMapping("/customer/pay-log")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    /*
     * 功能描述:
     * 〈生成微信支付二维码接口〉
     *
     * @param orderNo 订单编号 order_sn
     * @return : back.common_utils.R
     * @author : xyc
     * @date : 2021/5/15 11:37
     */

    @ApiOperation("生成支付二维码")
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
        //返回信息，含二维码地址，还有其他需要信息
        Map map=payLogService.createNative(orderNo);
        System.out.println("**************返回二维码map集合"+map);
        return R.ok().data(map);
    }
    


    /*
     * 功能描述:
     * 〈查询订单支付状态〉
     *
     * @param orderNo 订单编号 order_sn
     * @return : back.common_utils.R
     * @author : xyc
     * @date : 2021/5/15 16:04
     */

    @ApiOperation("查询订单支付状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        Map<String,String>map=payLogService.queryPayStatus(orderNo);
        System.out.println("**************查询订单状态map集合"+map);
        if (map==null){
            return R.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if ("SUCCESS".equals(map.get("trade_state"))){
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }
}

