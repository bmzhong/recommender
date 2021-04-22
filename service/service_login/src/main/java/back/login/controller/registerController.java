package back.login.controller;


import back.common_utils.R;
import back.login.entity.Vo.RegisterVo;
import back.login.entity.Vo.RegisterSellerVo;
import back.login.service.registerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login/customer-register")
@CrossOrigin
@Api("登录")
public class registerController {

    @Autowired
    private registerService registerService;


    /*
     * 功能描述:
     * 〈顾客注册〉
     *
     * @param registerVo 1
     * @param request 2
     * @return : back.common_utils.R
     * @author : xyc
     * @date : 2021/4/22 17:02
     */
    @PostMapping("register")
    @ApiOperation(value = "顾客账号注册")
    public R registerAccount
            (@ApiParam(name = "register", value = "顾客注册账号", required = true)
             @RequestBody RegisterVo registerVo){
        //注册账号返回账号id
        int id =registerService.registerAccount(registerVo);

        if (id>=0){
            return R.ok().data("id",id);
        }else {
            return R.error().data("id",-1);
        }
    }

    /*
     * 功能描述:
     * 〈卖家注册〉
     *
     * @param registerSellerVo 1
     * @return : back.common_utils.R
     * @author : xyc
     * @date : 2021/4/22 21:13
     */
    @PostMapping("registerSeller")
    @ApiOperation(value = "顾客账号注册")
    public R registerSeller
            (@ApiParam(name = "register", value = "卖家注册账号", required = true)
             @RequestBody RegisterSellerVo registerSellerVo){
        //注册账号返回账号id
        int id =registerService.registerSeller(registerSellerVo);

        if (id>=0){
            return R.ok().data("id",id);
        }else {
            return R.error().data("id",-1);
        }
    }
}
