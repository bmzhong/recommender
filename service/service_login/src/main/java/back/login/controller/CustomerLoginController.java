package back.login.controller;


import back.common_utils.R;
import back.login.entity.CustomerLogin;
import back.login.service.CustomerLoginService;
import io.swagger.annotations.Api;
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
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/login/customer-login")
@CrossOrigin
@Api("登录")

public class CustomerLoginController {
    @Autowired
    private CustomerLoginService customerLoginService;

    //添加账号接口的方法
    @PostMapping("addAccount")
    @ApiOperation(value = "新增账号")
    public R addAccount(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody CustomerLogin customerLogin){
        boolean save=customerLoginService.save(customerLogin);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

