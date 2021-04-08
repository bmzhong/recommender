package back.customer.service.impl;

import back.customer.entity.CustomerAddr;
import back.customer.mapper.CustomerAddrMapper;
import back.customer.service.CustomerAddrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author plyn
 * @since 2021-04-08
 */
@Service
public class CustomerAddrServiceImpl extends ServiceImpl<CustomerAddrMapper, CustomerAddr> implements CustomerAddrService {

}
