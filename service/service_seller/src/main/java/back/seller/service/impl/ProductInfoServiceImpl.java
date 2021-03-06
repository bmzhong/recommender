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

    @Autowired
    private ProductCommentService productCommentService;

    @Override
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
            productInfoVo.setShelfLife(product.getShelfLife());
            productInfoVo.setProductId(product.getProductId());
            productInfoVo.setCategoryId(category.getCategoryId());
            return productInfoVo;
        }
    }

    /**
     * @author 彭陆亚宁
     */
    @Override
    public boolean removeProductById(Integer productId) {
        //删除商品评论表
        boolean removeComment = productCommentService.remove(
                new QueryWrapper<ProductComment>().eq("product_id", productId)
        );
        if (!removeComment)
            return false;

        //删除商品图片信息表
        boolean removePic = productPicInfoService.remove(
                new QueryWrapper<ProductPicInfo>().eq("product_id",productId)
        );
        if (!removePic)
            return false;

        //删除商品信息表
        boolean removeProduct = removeById(productId);
        if (removeProduct)
            return true;

        return false;
    }
}
