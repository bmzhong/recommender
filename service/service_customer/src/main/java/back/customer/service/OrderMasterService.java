package back.customer.service;

import back.customer.entity.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
public interface OrderMasterService extends IService<OrderMaster> {
    //添加订单
    boolean addOrder(Integer customer_id, Integer product_id, Integer count);
}
