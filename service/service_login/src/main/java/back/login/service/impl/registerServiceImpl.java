package back.login.service.impl;

import back.login.entity.CustomerInf;
import back.login.entity.CustomerLogin;
import back.login.entity.Vo.RegisterVo;
import back.login.service.CustomerInfService;
import back.login.service.CustomerLoginService;
import back.login.service.registerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class registerServiceImpl implements registerService {
    @Autowired
    CustomerLoginService loginService;

    @Autowired
    CustomerInfService infService;



    @Override
    public int registerAccount(RegisterVo registerVo) {
        //1、在customerlogin表中存储password、loginname
        CustomerLogin customerLogin=new CustomerLogin();
        //存储账号密码
        customerLogin.setLoginName(registerVo.getLoginName());
        customerLogin.setPassword(registerVo.getPassword());
        loginService.save(customerLogin);

        //获得账号id
        QueryWrapper<CustomerLogin>wrapper=new QueryWrapper<>();

        wrapper.eq("login_name",customerLogin.getLoginName());
        wrapper.eq("password",customerLogin.getPassword());

        customerLogin=loginService.getOne(wrapper);
        int id=customerLogin.getCustomerId();


        //2、在customerinfo表中存储phonenumber
        CustomerInf customerInf=new CustomerInf();
        //存储登录id以及电话号码
        customerInf.setMobilePhone(registerVo.getMobilePhone());
        customerInf.setCustomerId(customerLogin.getCustomerId());
        infService.save(customerInf);

        return id;
    }
}
