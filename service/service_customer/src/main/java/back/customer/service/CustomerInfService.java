package back.customer.service;

import back.customer.entity.CustomerInf;
import back.customer.entity.vo.CustomerInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author plyn
 * @since 2021-04-08
 */
public interface CustomerInfService extends IService<CustomerInf> {
    boolean updateById(CustomerInfoVo entity);
}
