package back.seller.controller;


import back.common_utils.R;
import back.seller.entity.*;
import back.seller.entity.vo.BrandInfoVo;
import back.seller.entity.vo.ProductCategoryVo;
import back.seller.entity.vo.ProductInfoVo;
import back.seller.service.*;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.Asn1Exception;

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
@Api("Product Info Controller")
@CrossOrigin
@RequestMapping("/seller/product-info")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private BrandInfoService brandInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductPicInfoService productPicInfoService;

    /**
     * @param categoryId
     * @return R
     * @author 钟保明
     */
    @ApiOperation(value = "根据categoryid查询商品")
    @GetMapping("/searchByCategoryId/{categoryId}")
    public R searchByCategoryId(@ApiParam(name = "categoryid", value = "类别id")
                                @PathVariable Integer categoryId) {
        QueryWrapper<ProductInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        List<Object> productInfos = productInfoService.listObjs(wrapper);
        if (productInfos.isEmpty()) {
            return R.error();
        } else {
            return R.ok().data("productInfos", productInfos);
        }
    }


    /**
     * @param productName
     * @return R
     * @author 王智
     */
    @ApiOperation(value = "通过商品名称模糊查找相应商品")
    @GetMapping("searchByPartName/{productName}")
    public R searchByPartName(@ApiParam(name = "productName", value = "商品关键字")
                              @PathVariable String productName) {
        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("product_name", productName);
        List<Object> list = productInfoService.listObjs(queryWrapper);
        if (list.isEmpty())
            return R.error().data("msg", "no data found");
        else
            return R.ok().data("list", list);
    }

    /**
     * @param typeId
     * @param productName
     * @return R
     * @author 王智
     */
    @ApiOperation(value = "通过商品名称模糊查找相应商品")
    @GetMapping("searchByTypeByPartName/{typeId}/{productName}")
    public R searchByTypeByPartName(@ApiParam(name = "typeId", value = "分类Id", required = true)
                                    @PathVariable String typeId,
                                    @ApiParam(name = "productName", value = "商品关键字")
                                    @PathVariable String productName) {
        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", typeId);
        queryWrapper.like("product_name", productName);
        List<Object> list = productInfoService.listObjs(queryWrapper);
        if (list.isEmpty())
            return R.error().data("msg", "no data found");
        else
            return R.ok().data("list", list);
    }

    /**
     * @param id
     * @return R
     * @author 钟保明
     */
    @ApiOperation("根据id查询商品信息")
    @GetMapping("/data/product/{id}")
    public R getProductInfoById(@ApiParam(name = "id", value = "商品的id", required = true)
                                @PathVariable Integer id) {
        ProductInfoVo productInfoVo = productInfoService.getProductInfoById(id);
        if (null == productInfoVo) {
            return R.error();
        } else {
            return R.ok().data("productInfo", productInfoVo);
        }
    }

    /**
     * @return R
     * @author 钟保明
     */
    @ApiOperation("查询所有分类")
    @GetMapping("/data/category")
    public R getAllCategory() {
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

    /**
     * @return R
     * @author 钟保明
     */
    @ApiOperation("获取所有品牌信息")
    @GetMapping("/data/brand")
    public R getAllBrands() {
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
            return R.ok().data("brand", brandInfoVos);
        }
    }

    /**
     * @param productInfo 传入的商品信息封装的对象
     * @return 返回增加操作成功与否
     * @author :张琦
     */
    @ApiOperation(value = "增加商品信息")
    @PostMapping("/add/product")
    public R addProduct(@ApiParam(value = "商品信息", required = true)
                        @RequestBody ProductInfo productInfo) {
        final boolean save = productInfoService.save(productInfo);
        if (save) {
            return R.ok().data("productId",productInfo.getProductId());
        } else {
            return R.error();
        }
    }

    /**
     * @param productInfo 要修改商品的属性
     * @return 返回修改成功与否
     * @author :张琦
     */
    @ApiOperation("编辑商品信息")
    @PostMapping("/edit/product")
    public R editProduct(@ApiParam(value = "商品信息", required = true)
                         @RequestBody ProductInfo productInfo) {
        final boolean updateById = productInfoService.updateById(productInfo);  //通过id来修改商品的信息
        if (updateById) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @param id 被查询的商家的id
     * @return 返回所有的商品信息
     * @author :张琦
     */
    @ApiOperation(value = "查询该商家所有商品信息")
    @PostMapping("findAllProductInfo/{id}")
    public R findAllProductInfo(@ApiParam(name = "id", value = "商家id", required = true)
                                @PathVariable Integer id) {
        QueryWrapper<ProductInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("seller_id", id);
        List<ProductInfo> list = productInfoService.list(wrapper);
        List<ProductInfoVo> ans = new ArrayList<>();
        for(ProductInfo p : list){
            Integer i = p.getProductId();
            ProductInfoVo productInfoVo = productInfoService.getProductInfoById(id);
            if (null == productInfoVo) {
                return R.error();
            } else {
                ans.add(productInfoVo);
            }
        }
        if (ans.isEmpty())
            return R.error().data("msg", "no data found");
        else
            return R.ok().data("list", ans);
    }

    /**
     * @author 彭陆亚宁
     */
    @ApiOperation(value = "删除商品相关信息")
    @PostMapping("removeProductById/{productId}")
    public R removeProductById(@ApiParam(name = "productId", value = "商品id", required = true)
                               @PathVariable Integer productId) {
        boolean removeProductById = productInfoService.removeProductById(productId);
        if (removeProductById)
            return R.ok();
        else
            return R.error();
    }

    /**
     * @author 张琦
     * @return 返回所有商品信息
     */
    @ApiOperation(value = "返回所有商品信息")
    @PostMapping("/getAllProductInfos")
    public R getAllProductInfos () {
        final List<ProductInfo> productInfos = productInfoService.list(new QueryWrapper<>());
        return R.ok().data("allProductInfos", productInfos);
    }
}

