package back.customer.service.impl;

import back.customer.entity.CustomerInf;
import back.customer.entity.vo.CustomerInfoVo;
import back.customer.mapper.CustomerInfMapper;
import back.customer.service.CustomerInfService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    @Override
    public boolean updateById(CustomerInfoVo entity) {
        CustomerInf customerInf = this.getOne(new QueryWrapper<CustomerInf>().eq("customer_id", entity.getCustomerId()));
        if (customerInf==null){ //不存在
            CustomerInf newOne = new CustomerInf(entity);
            return this.save(newOne);
        } else {
            customerInf.setCustomerTrueName(entity.getCustomerTrueName());
            customerInf.setIdentityCardType(entity.getIdentityCardType());
            customerInf.setIdentityCardNo(entity.getIdentityCardNo());
            customerInf.setMobilePhone(entity.getMobilePhone());
            customerInf.setCustomerEmail(entity.getCustomerEmail());
            customerInf.setGender(entity.getGender());
            customerInf.setBirthday(new Date(entity.getBirthday()));
            return this.updateById(customerInf);
        }
    }
}
