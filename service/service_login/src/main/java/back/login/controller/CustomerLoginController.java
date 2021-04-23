package back.login.controller;


import back.common_utils.R;
import back.login.entity.CustomerLogin;
import back.login.entity.SellerLogin;
import back.login.service.CustomerLoginService;
import back.login.service.SellerLoginService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/login/customer-login")
@CrossOrigin
@Api("登录")

public class CustomerLoginController {
    @Autowired
    private CustomerLoginService customerLoginService;

    @Autowired
    private SellerLoginService sellerLoginService;

    /*
     * 功能描述:
     * 〈添加账号〉
     *
     * @param customerLogin 1
     * @return : back.common_utils.R
     * @author : xyc
     * @date : 2021/4/22 17:00
     */
    //添加账号接口的方法
    @PostMapping("addAccount")
    @ApiOperation(value = "新增账号")
    public R addAccount(
            @ApiParam(name = "account", value = "账号", required = true)
            @RequestBody CustomerLogin customerLogin){
        boolean save=customerLoginService.save(customerLogin);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    /*
     * 功能描述:
     * 〈顾客登录〉
     *
     * @param customerLogin 1
     * @return : back.common_utils.R
     * @author : xyc
     * @date : 2021/4/22 17:02
     */
    //实现登录功能
    @PostMapping("login")
    @ApiOperation(value = "登录功能")
    public R login(@ApiParam(name = "account", value = "账号", required = true)
                       @RequestBody CustomerLogin customerLogin){
        //登录并返回id
        QueryWrapper<CustomerLogin>wrapper=new QueryWrapper<>();
        wrapper.eq("login_name",customerLogin.getLoginName());
        wrapper.eq("password",customerLogin.getPassword());
        customerLogin=customerLoginService.getOne(wrapper);

        int id =customerLogin.getCustomerId();

        ;
        if (id>0){
//            session.setAttribute("login_name",customerLogin.getLoginName());
            return R.ok().data("id",id);
        }else if (id<0){
            return R.error().data("id",-1);
        }else {
            return R.error().data("id",0);
        }
    }

    @PostMapping("loginSeller")
    @ApiOperation(value = "卖家登录功能")
    public R login(@ApiParam(name = "account", value = "账号", required = true)
                   @RequestBody SellerLogin sellerLogin){
        //登录并返回id
        QueryWrapper<SellerLogin>wrapper=new QueryWrapper<>();
        wrapper.eq("login_name",sellerLogin.getLoginName());
        wrapper.eq("password",sellerLogin.getPassword());
        sellerLogin=sellerLoginService.getOne(wrapper);

        int id =sellerLogin.getSellerId();

        ;
        if (id>0){
            return R.ok().data("id",id);
        }else if (id<0){
            return R.error().data("id",-1);
        }else {
            return R.error().data("id",0);
        }
    }
}

