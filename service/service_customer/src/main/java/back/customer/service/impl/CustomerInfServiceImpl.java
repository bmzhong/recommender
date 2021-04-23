package back.customer.service.impl;

import back.customer.entity.CustomerInf;
import back.customer.mapper.CustomerInfMapper;
import back.customer.service.CustomerInfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author plyn
 * @since 2021-04-08
 */
@Service
public class CustomerInfServiceImpl extends ServiceImpl<CustomerInfMapper, CustomerInf> implements CustomerInfService {

}
