package back.customer.service;

import back.customer.entity.OrderDetail;
import back.customer.entity.vo.OrderDetailQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
public interface OrderDetailService extends IService<OrderDetail> {
    // 查询订单所有信息，包含order_master和order_detail
    OrderDetailQuery getAllInfo(Integer orderId);
}
