package back.customer.controller;


import back.common_utils.R;
import back.customer.entity.ProductInfo;
import back.customer.service.ProductInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author plyn
 * @since 2021-05-14
 */
@RestController
@RequestMapping("/customer/product-info")
@Api("Customer Search Controller")
@CrossOrigin
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;


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
    @GetMapping("SearchByPartName/{productName}")
    public R SearchByPartName(@ApiParam(name = "productName", value = "商品关键字")
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
    @GetMapping("SearchByTypeByPartName/{typeId}/{productName}")
    public R SearchByTypeByPartName(@ApiParam(name = "typeId", value = "分类Id", required = true)
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
}

