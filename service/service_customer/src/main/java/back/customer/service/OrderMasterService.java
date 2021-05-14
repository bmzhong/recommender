package back.customer.service;

import back.customer.entity.OrderMaster;
import back.customer.entity.vo.Order;
import back.customer.entity.vo.OrderQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
public interface OrderMasterService extends IService<OrderMaster> {
    //添加订单
    String addOrder(Order order);

    /**
     * 取消订单：
     *      1. 若order_status = 0，即订单未确认，允许取消
     *      2. 若order_status = 1，即订单已确认但未发货，允许取消
     *      3. 若order_status = 2, 即订单已发货，拒绝取消
     * @param orderId 订单ID
     * @return order_status
     */
    int cancelOrder(Integer orderId);

    //查询相应用户所有订单信息
    List<OrderQuery> queryAllOrders(Integer customerId);
}
