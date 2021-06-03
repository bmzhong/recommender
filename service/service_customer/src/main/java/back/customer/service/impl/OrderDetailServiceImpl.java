package back.customer.service.impl;

import back.customer.entity.OrderDetail;
import back.customer.entity.OrderMaster;
import back.customer.entity.vo.OrderDetailQuery;
import back.customer.mapper.OrderDetailMapper;
import back.customer.service.OrderDetailService;
import back.customer.service.OrderMasterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderDetailQuery getAllInfo(Integer orderId) {
        // 获取order_master
        OrderMaster orderMaster = orderMasterService.getById(orderId);
        if (null ==  orderMaster)
            return null;

        // 获取order_detail
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        OrderDetail orderDetail = orderDetailService.getOne(queryWrapper);

        // 创建order_detail_query，返回
        OrderDetailQuery orderDetailQuery = new OrderDetailQuery(orderMaster, orderDetail);

        return orderDetailQuery;
    }
}
