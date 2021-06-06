package back.seller.controller;


import back.common_utils.R;
import back.seller.entity.ProductPicInfo;
import back.seller.entity.vo.ProductPicVo;
import back.seller.service.ProductPicInfoService;
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
 * @since 2021-04-23
 */
@RestController
@RequestMapping("/seller/product-pic-info")
@CrossOrigin
public class ProductPicInfoController {

    @Autowired
    private ProductPicInfoService productPicInfoService;

    @ApiOperation(value = "添加商品图片，一次一张")
    @PostMapping("addProductPic")
    public R addCart(@ApiParam(value = "商品图片",required = true )
                     @RequestBody ProductPicVo productPicVo){
        ProductPicInfo productPicInfo = new ProductPicInfo(productPicVo);
        boolean save = productPicInfoService.save(productPicInfo);
        if (save) {
            return R.ok();
        } else
            return R.error();
    }

}

