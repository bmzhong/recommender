package back.customer.service.impl;

import back.customer.entity.OrderDetail;
import back.customer.entity.OrderMaster;
import back.customer.entity.ProductInfo;
import back.customer.mapper.OrderMasterMapper;
import back.customer.service.OrderDetailService;
import back.customer.service.OrderMasterService;
import back.customer.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
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
    public boolean addOrder(Integer customer_id, Integer product_id, Integer count) {
        //查询product_info,获取商品信息
        ProductInfo productInfo = productInfoService.getById(product_id);

        //插入order_master
        OrderMaster orderMaster = new OrderMaster();
        save(orderMaster);

        //插入order_detail

        return false;
    }
}
