package back.login.service.impl;

import back.login.entity.CustomerInf;
import back.login.entity.CustomerLogin;
import back.login.entity.SellerLogin;
import back.login.entity.Vo.RegisterSellerVo;
import back.login.entity.Vo.RegisterVo;
import back.login.service.CustomerInfService;
import back.login.service.CustomerLoginService;
import back.login.service.SellerLoginService;
import back.login.service.registerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class registerServiceImpl implements registerService {
    @Autowired
    CustomerLoginService loginService;

    @Autowired
    CustomerInfService infService;

    @Autowired
    SellerLoginService sellerLoginService;



    @Override
    public int registerAccount(RegisterVo registerVo) {
        //1、在customerlogin表中存储password、loginname
        CustomerLogin customerLogin=new CustomerLogin();

        //检查用户名是否重复
        QueryWrapper<CustomerLogin>wrapper=new QueryWrapper<>();
        wrapper.eq("login_name",registerVo.getLoginName());
        int id;
        if (loginService.getOne(wrapper)!=null){
            return id=0;
        }

        //存储账号密码
        customerLogin.setLoginName(registerVo.getLoginName());
        customerLogin.setPassword(registerVo.getPassword());
        loginService.save(customerLogin);

        //获得账号id


        wrapper.eq("login_name",customerLogin.getLoginName());
        wrapper.eq("password",customerLogin.getPassword());

        customerLogin=loginService.getOne(wrapper);
        id=customerLogin.getCustomerId();


        //2、在customerinfo表中存储phonenumber
        CustomerInf customerInf=new CustomerInf();
        //存储登录id以及电话号码
        customerInf.setMobilePhone(registerVo.getMobilePhone());
        customerInf.setCustomerId(customerLogin.getCustomerId());
        String time="2000-01-01 00:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date Default = format.parse(time);
            customerInf.setBirthday(Default);
        }catch (Exception e){
            e.printStackTrace();
        }
        infService.save(customerInf);

        return id;
    }

    @Override
    public int registerSeller(RegisterSellerVo registerSellerVo) {
        //1、在sellerlogin表中存储password、loginname
        SellerLogin sellerLogin=new SellerLogin();

        //检查用户名是否重复
        QueryWrapper<SellerLogin>wrapper=new QueryWrapper<>();
        wrapper.eq("login_name",registerSellerVo.getLoginName());
        int id;
        if (sellerLoginService.getOne(wrapper)!=null){
            return id=0;
        }
        //存储信息
        sellerLogin.setLoginName(registerSellerVo.getLoginName());
        sellerLogin.setPassword(registerSellerVo.getPassword());
        sellerLogin.setSellerTrueName(registerSellerVo.getTrueName());
        sellerLogin.setSellerEmail(registerSellerVo.getEmail());
        sellerLogin.setMobilePhone(registerSellerVo.getMobilePhone());

        sellerLoginService.save(sellerLogin);

        //获得账号id


        wrapper.eq("login_name",sellerLogin.getLoginName());
        wrapper.eq("password",sellerLogin.getPassword());

        sellerLogin=sellerLoginService.getOne(wrapper);
        id=sellerLogin.getSellerId();

        return id;
    }
}
