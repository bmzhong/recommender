package back.seller.service.impl;

import back.seller.entity.CustomerAddr;
import back.seller.entity.OrderDetail;
import back.seller.entity.OrderMaster;
import back.seller.entity.ProductInfo;
import back.seller.entity.vo.SellerOrderVo;
import back.seller.mapper.OrderMasterMapper;
import back.seller.service.CustomerAddrService;
import back.seller.service.OrderDetailService;
import back.seller.service.OrderMasterService;
import back.seller.service.ProductInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService {

    @Autowired
    OrderDetailService detailService;

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    OrderMasterService orderMasterService;

    @Autowired
    CustomerAddrService customerAddrService;

    public List<SellerOrderVo> getSellerOrder(Integer sellerId) {
        List<SellerOrderVo> sellerOrderVos = new ArrayList<>();
        QueryWrapper<ProductInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("seller_id", sellerId);
        List<ProductInfo> productInfos = productInfoService.list(wrapper);
        for (ProductInfo info : productInfos) {
            Integer productId = info.getProductId();
            QueryWrapper<OrderDetail> detailQueryWrapper = new QueryWrapper<>();
            detailQueryWrapper.eq("product_id", productId);
            List<OrderDetail> orderDetails = detailService.list(detailQueryWrapper);
            for (OrderDetail detail : orderDetails) {
                Integer orderId = detail.getOrderId();
                QueryWrapper<OrderMaster> orderMasterQueryWrapper = new QueryWrapper<>();
                orderMasterQueryWrapper.eq("order_id", orderId);
                List<OrderMaster> orderMasters = orderMasterService.list(orderMasterQueryWrapper);
                if (orderDetails.isEmpty()) {
                    return null;
                }
                OrderMaster orderMaster = orderMasters.get(0);
                Integer customerAddressId = orderMaster.getCustomerAddrId();
                QueryWrapper<CustomerAddr> customerAddrQueryWrapper = new QueryWrapper<>();
                customerAddrQueryWrapper.eq("customer_addr_id", customerAddressId);
                List<CustomerAddr> customerAddrs = customerAddrService.list(customerAddrQueryWrapper);
                if (customerAddrs.isEmpty()) {
                    return null;
                }
                CustomerAddr customerAddr = customerAddrs.get(0);
                SellerOrderVo sellerOrderVo = new SellerOrderVo();
                sellerOrderVo.setOrderId(orderMaster.getOrderId());
                sellerOrderVo.setProductName(info.getProductName());
                sellerOrderVo.setProductCnt(detail.getProductCnt());
                sellerOrderVo.setOrderStatus(orderMaster.getOrderStatus());
                sellerOrderVo.setProvince(customerAddr.getProvince());
                sellerOrderVo.setCity(customerAddr.getCity());
                sellerOrderVo.setDistrict(customerAddr.getDistrict());
                sellerOrderVo.setAddress(customerAddr.getAddress());
                sellerOrderVo.setZip(customerAddr.getZip());
                sellerOrderVos.add(sellerOrderVo);
            }
        }
        return sellerOrderVos;
    }
}
