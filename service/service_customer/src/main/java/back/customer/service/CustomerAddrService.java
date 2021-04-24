package back.customer.service;

import back.customer.entity.CustomerAddr;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类,用户地址管理
 * </p>
 *
 * @author plyn
 * @since 2021-04-08
 */
public interface CustomerAddrService extends IService<CustomerAddr> {
    //设置默认地址
    boolean setDefault(Integer customerId, Integer addrId);
}
