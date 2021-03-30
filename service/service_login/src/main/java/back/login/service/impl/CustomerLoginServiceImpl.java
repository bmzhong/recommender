package back.login.service.impl;

import back.login.entity.CustomerLogin;
import back.login.mapper.CustomerLoginMapper;
import back.login.service.CustomerLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@Service
public class CustomerLoginServiceImpl extends ServiceImpl<CustomerLoginMapper, CustomerLogin> implements CustomerLoginService {

}
