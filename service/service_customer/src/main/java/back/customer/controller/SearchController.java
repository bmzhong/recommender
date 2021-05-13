package back.customer.controller;


import back.common_utils.R;
import back.customer.entity.OrderDetail;
import back.customer.entity.ProductInfo;
import back.customer.service.ProductInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer/search")
@CrossOrigin
public class SearchController {

    @Autowired
    private ProductInfoService productInfoService;

    //通过商品名称模糊查找相应商品
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

    //在分类情况下，通过商品名称模糊查找相应商品
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

