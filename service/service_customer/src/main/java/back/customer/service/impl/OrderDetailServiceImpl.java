package back.customer.service.impl;

import back.customer.entity.OrderDetail;
import back.customer.mapper.OrderDetailMapper;
import back.customer.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
