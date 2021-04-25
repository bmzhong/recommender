package back.seller.service;

import back.seller.entity.ProductInfo;
import back.seller.entity.vo.ProductCategoryVo;
import back.seller.entity.vo.ProductInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-08
 */
public interface ProductInfoService extends IService<ProductInfo> {

    ProductInfoVo getProductInfoById(Integer id);

}
