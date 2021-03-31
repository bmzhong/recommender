package back.login.service.impl;

import back.login.entity.CustomerInf;
import back.login.mapper.CustomerInfMapper;
import back.login.service.CustomerInfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-31
 */
@Service
public class CustomerInfServiceImpl extends ServiceImpl<CustomerInfMapper, CustomerInf> implements CustomerInfService {

}
