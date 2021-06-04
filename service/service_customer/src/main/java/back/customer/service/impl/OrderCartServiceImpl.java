package back.customer.service.impl;

import back.customer.entity.OrderCart;
import back.customer.mapper.OrderCartMapper;
import back.customer.service.OrderCartService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    @Override
    public Integer addOne(OrderCart entity) {
        Integer cartId = null;

        Map<String, Integer> eqMap = new HashMap<>();
        eqMap.put("product_id", entity.getProductId());
        eqMap.put("customer_id", entity.getCustomerId());
        OrderCart orderCart = this.getOne(new QueryWrapper<OrderCart>().allEq(eqMap));

        if (orderCart == null){
            this.save(entity);
            cartId = entity.getCartId();
        }else {
            orderCart.setProductAmount(orderCart.getProductAmount()+entity.getProductAmount());
            this.updateById(orderCart);
            cartId = orderCart.getCartId();
        }

        return cartId;
    }
}
