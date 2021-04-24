package back.seller.controller;


import back.common_utils.R;
import back.seller.entity.*;
import back.seller.entity.Vo.BrandInfoVo;
import back.seller.entity.Vo.ProductCategoryVo;
import back.seller.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/data")
@Api("Product Info Controller")
@CrossOrigin
public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    ProductPicInfoService productPicInfoService;

    @Autowired
    BrandInfoService brandInfoService;

    @Autowired
    SellerLoginService sellerLoginService;

    @Autowired
    ProductCategoryService productCategoryService;

    @ApiOperation("根据id查询商品信息")
    @GetMapping("/product/{id}")
    R getProductInfoById(@ApiParam(name = "id", value = "商品的id", required = true)
                         @PathVariable Integer id) {
        ProductInfo product = productInfoService.getById(id);
        if (null == product) {
            return R.error();
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
            return R.ok().data("name", product.getProductName())
                    .data("pictures", urls)
                    .data("brandName", brandInfo.getBrandName())
                    .data("brandLogo", brandInfo.getBrandLogo())
                    .data("brandDesc", brandInfo.getBrandDesc())
                    .data("brandTelephone", brandInfo.getTelephone())
                    .data("categoryName", category.getCategoryName())
                    .data("sellerId", sellerLogin.getSellerId())
                    .data("price", product.getPrice())
                    .data("productionDate", product.getProductionDate())
                    .data("description", product.getDescript())
                    .data("shelfLife", product.getShelfLife())
                    ;
        }
    }


    @ApiOperation("查询所有分类")
    @GetMapping("/brand")
    R getAllCategory() {
        List<ProductCategory> productCategory = productCategoryService.list(null);
        if (productCategory.size() <= 0) {
            return R.error();
        } else {
            List<ProductCategoryVo> categoryVos = new ArrayList<>();
            for (ProductCategory category : productCategory) {
                ProductCategoryVo categoryVo = new ProductCategoryVo();
                categoryVo.setId(category.getCategoryId());
                categoryVo.setName(category.getCategoryName());
                categoryVo.setParentId(category.getParentId());
                categoryVo.setCategoryLevel(category.getCategoryLevel());
                categoryVos.add(categoryVo);
            }
            return R.ok().data("category", categoryVos);
        }
    }

    @ApiOperation("获取所有品牌信息")
    @GetMapping("/brand")
    R getAllBrands() {
        List<BrandInfo> brandInfos = brandInfoService.list(null);
        if (brandInfos.size() <= 0) {
            return R.error();
        } else {
            List<BrandInfoVo> brandInfoVos = new ArrayList<>();
            for (BrandInfo brandInfo : brandInfos) {
                BrandInfoVo brandInfoVo = new BrandInfoVo();
                brandInfoVo.setId(brandInfo.getBrandId());
                brandInfoVo.setName(brandInfo.getBrandName());
                brandInfoVo.setTelephone(brandInfo.getTelephone());
                brandInfoVo.setLogo(brandInfo.getBrandLogo());
                brandInfoVo.setDesc(brandInfo.getBrandDesc());
                brandInfoVos.add(brandInfoVo);
            }
            return R.ok().data("brand",brandInfoVos);
        }

    }
}

