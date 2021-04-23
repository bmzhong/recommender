package back.customer.service.impl;

import back.customer.entity.ProductInfo;
import back.customer.mapper.ProductInfoMapper;
import back.customer.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author plyn
 * @since 2021-04-22
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

}
