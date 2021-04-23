package back.customer.service.impl;

import back.customer.entity.OrderCart;
import back.customer.mapper.OrderCartMapper;
import back.customer.service.OrderCartService;
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
public class OrderCartServiceImpl extends ServiceImpl<OrderCartMapper, OrderCart> implements OrderCartService {

}
