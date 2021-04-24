package back.seller.controller;


import back.common_utils.R;
import back.seller.entity.ProductInfo;
import back.seller.service.ProductInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/seller/product-info")
public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    /**
     * @author :张琦
     * @param productInfo 传入的商品信息封装的对象
     * @return 返回增加操作成功与否
     */
    @ApiOperation(value = "增加商品信息")
    @PostMapping("/add/product")
    public R addProduct(@ApiParam(value = "商品信息", required = true)
                        @RequestBody ProductInfo productInfo) {
        productInfo.setProductId(0);
        final boolean save = productInfoService.save(productInfo);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * @author :张琦
     * @param id 要修改商品的id
     * @param productInfo 要修改商品的属性
     * @return 返回修改成功与否
     */
    @ApiOperation("编辑商品信息")
    @PostMapping("/edit/product")
    public R editProduct (@ApiParam(value = "商品信息", required = true)
                          @RequestBody ProductInfo productInfo) {
        final boolean updateById = productInfoService.updateById(productInfo);  //通过id来修改商品的信息
        if (updateById) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

