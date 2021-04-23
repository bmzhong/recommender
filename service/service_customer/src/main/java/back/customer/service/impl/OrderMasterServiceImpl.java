package back.customer.service.impl;

import back.customer.entity.OrderDetail;
import back.customer.entity.OrderMaster;
import back.customer.entity.ProductInfo;
import back.customer.entity.vo.Order;
import back.customer.mapper.OrderMasterMapper;
import back.customer.service.OrderDetailService;
import back.customer.service.OrderMasterService;
import back.customer.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    public boolean addOrder(Order order) {
        OrderDetail orderDetail = new OrderDetail();
        OrderMaster orderMaster = new OrderMaster();

        //获取商品详情
        ProductInfo product = productInfoService.getById(order.getProductId());
        if (product == null)
            return false;

        //完善orderMaster信息
        orderMaster.setOrderSn(order.getOrderSn());
        orderMaster.setCustomerId(order.getCustomerId());
        orderMaster.setCustomerAddrId(order.getCustomerAddrId());
        orderMaster.setOrderMoney(order.getOrderMoney());
        orderMaster.setOrderPoint(order.getOrderPoint());

        //orderMaster入库
        boolean save = save(orderMaster);
        if (!save)
            return false;

        //完善orderDetail信息
        orderDetail.setOrderId(orderMaster.getOrderId());
        orderDetail.setProductId(order.getProductId());
        orderDetail.setProductCnt(order.getProductCnt());
        orderDetail.setProductName(product.getProductName());

        //orderDetail入库
        boolean save1 = orderDetailService.save(orderDetail);
        if (!save1)
            return false;

        return true;
    }
}
