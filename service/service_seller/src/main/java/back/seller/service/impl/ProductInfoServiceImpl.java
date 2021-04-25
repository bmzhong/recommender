package back.seller.service.impl;

import back.common_utils.R;
import back.seller.entity.*;
import back.seller.entity.vo.ProductCategoryVo;
import back.seller.entity.vo.ProductInfoVo;
import back.seller.mapper.ProductInfoMapper;
import back.seller.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2021-04-08
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductPicInfoService productPicInfoService;

    @Autowired
    private BrandInfoService brandInfoService;

    @Autowired
    private SellerLoginService sellerLoginService;

    @Autowired
    private ProductCategoryService productCategoryService;

    public ProductInfoVo getProductInfoById(Integer id) {
        ProductInfo product = productInfoService.getById(id);
        if (null == product) {
            return null;
        } else {
            QueryWrapper<ProductPicInfo> picInfoQueryWrapper = new QueryWrapper<>();
            picInfoQueryWrapper.eq("product_id", product.getProductId());
            List<ProductPicInfo> picInfoList = productPicInfoService.list(picInfoQueryWrapper);
            List<String> urls = new ArrayList<>();
            for (ProductPicInfo productPicInfo : picInfoList) {
                urls.add(productPicInfo.getPicUrl());
            }
            BrandInfo brandInfo = brandInfoService.getById(product.getBrandId());
            SellerLogin sellerLogin = sellerLoginService.getById(product.getSellerId());
            ProductCategory category = productCategoryService.getById(product.getCategoryId());
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setName(product.getProductName());
            productInfoVo.setPictures(urls);
            productInfoVo.setBrandName(brandInfo.getBrandName());
            productInfoVo.setBrandLogo(brandInfo.getBrandLogo());
            productInfoVo.setBrandDesc(brandInfo.getBrandDesc());
            productInfoVo.setBrandTelephone(brandInfo.getTelephone());
            productInfoVo.setCategoryName(category.getCategoryName());
            productInfoVo.setSellerId(sellerLogin.getSellerId());
            productInfoVo.setPrice(product.getPrice());
            productInfoVo.setProductionDate(product.getProductionDate());
            productInfoVo.setDescription(product.getDescript());
            productInfoVo.setSellerId(product.getShelfLife());
            return productInfoVo;
        }
    }
}
