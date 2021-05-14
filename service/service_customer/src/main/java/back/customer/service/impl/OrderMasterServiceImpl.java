package back.customer.service.impl;

import back.customer.entity.OrderDetail;
import back.customer.entity.OrderMaster;
import back.customer.entity.ProductInfo;
import back.customer.entity.vo.Order;
import back.customer.entity.vo.OrderQuery;
import back.customer.mapper.OrderMasterMapper;
import back.customer.service.OrderDetailService;
import back.customer.service.OrderMasterService;
import back.customer.service.ProductInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public String addOrder(Order order) {
        OrderDetail orderDetail = new OrderDetail();
        OrderMaster orderMaster = new OrderMaster();

        //获取商品详情
        ProductInfo product = productInfoService.getById(order.getProductId());
        if (product == null)
            return null;

        //完善orderMaster信息
        orderMaster.setCustomerId(order.getCustomerId());
        orderMaster.setCustomerAddrId(order.getCustomerAddrId());
        orderMaster.setOrderMoney(order.getOrderMoney());
        orderMaster.setOrderPoint(order.getOrderPoint());

        //orderMaster入库
        boolean save = save(orderMaster);
        if (!save)
            return null;

        //完善orderDetail信息
        orderDetail.setOrderId(orderMaster.getOrderId());
        orderDetail.setProductId(order.getProductId());
        orderDetail.setProductCnt(order.getProductCnt());
        orderDetail.setProductName(product.getProductName());

        //orderDetail入库
        boolean save1 = orderDetailService.save(orderDetail);
        if (!save1)
            return null;

        return orderMaster.getOrderSn();
    }

    @Override
    public int cancelOrder(Integer orderId) {
        OrderMaster orderMaster = getById(orderId);
        int status = orderMaster.getOrderStatus();

        if (status==2 || status == 3) //3不可能出现，前端解决
            return status;

        //删除所有order_id=orderId的order_detail
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderMaster.getOrderId());
        orderDetailService.remove(queryWrapper);

        //删除orderMaster
        removeById(orderMaster.getOrderId());

        return status;
    }

    @Override
    public List<OrderQuery> queryAllOrders(Integer customerId) {
        List<OrderMaster> orderMasters = list(new QueryWrapper<OrderMaster>().eq("customer_id", customerId));

        if (orderMasters==null)
            return null;

        List<OrderQuery> res = new ArrayList<>();
        for (OrderMaster orderMaster: orderMasters) {
            OrderQuery orderQuery = new OrderQuery();
            orderQuery.setOrderId(orderMaster.getOrderId());
            orderQuery.setCustomerAddrId(orderMaster.getCustomerAddrId());
            orderQuery.setCreateTime(orderMaster.getCreateTime());
            orderQuery.setOrderStatus(orderMaster.getOrderStatus());
            orderQuery.setProductName(
                    orderDetailService.getOne(
                            new QueryWrapper<OrderDetail>()
                                    .eq("order_id",orderMaster.getOrderId()))
                                        .getProductName()
            );
            res.add(orderQuery);
        }

        return res;
    }
}
