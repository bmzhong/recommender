package back.login.controller;


import back.common_utils.R;
import back.login.entity.Vo.RegisterVo;
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

    @PostMapping("register")
    @ApiOperation(value = "账号注册")
    public R registerAccount
            (@ApiParam(name = "register", value = "注册账号", required = true)
             @RequestBody RegisterVo registerVo, HttpServletRequest request){
        //注册账号返回账号id
        int id =registerService.registerAccount(registerVo);

        if (id>=0){
            return R.ok().data("id",id);
        }else {
            return R.error().data("id",-1);
        }


    }
}
