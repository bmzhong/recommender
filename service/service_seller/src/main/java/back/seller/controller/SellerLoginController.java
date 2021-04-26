package back.seller.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import back.common_utils.R;
import back.seller.entity.SellerLogin;
import back.seller.service.SellerLoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-23
 */
@RestController
@RequestMapping("/seller/seller-login")
@CrossOrigin
public class SellerLoginController {

    @Autowired
    private SellerLoginService sellerService;

    /**
     * @param id 卖家id
     * @return 返回查询到的卖家信息
     */
    @ApiOperation(value = "根据卖家id查询卖家的信息")
    @GetMapping("/queryInfo/{id}")
    public R queryInfo(@ApiParam(name = "id", value = "卖家id", required = true)
                       @PathVariable Integer id) {
        SellerLogin sellerInf = sellerService.getById(id);
        return R.ok().data("sellerInf", sellerInf);
    }

    /**
     * @param seller 要修改的卖家信息，包括用户名，手机号，邮箱等
     * @return 返回是否修改成功
     */
    @ApiOperation(value = "修改商家信息")
    @PostMapping("/updateInfo")
    public R updateInfo(@ApiParam(name = "sellerInfo", value = "卖家信息", required = true)
                        @RequestBody SellerLogin seller) {
        boolean res = sellerService.updateById(seller);
        if (res) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

