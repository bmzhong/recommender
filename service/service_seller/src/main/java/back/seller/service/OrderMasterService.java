package back.seller.service;

import back.seller.entity.OrderMaster;
import back.seller.entity.vo.SellerOrderVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
public interface OrderMasterService extends IService<OrderMaster> {
    public List<SellerOrderVo> getSellerOrder(Integer sellerId);
}
