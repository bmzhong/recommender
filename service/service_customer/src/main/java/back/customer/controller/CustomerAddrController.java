package back.customer.controller;


import back.common_utils.R;
import back.customer.entity.CustomerAddr;
import back.customer.service.CustomerAddrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器:用户地址管理
 * </p>
 *
 * @author plyn
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/customer/customer-addr")
@CrossOrigin
public class CustomerAddrController {

    @Autowired
    CustomerAddrService addrService;

    //查询所有地址信息
    @ApiOperation(value = "查询该用户所有地址信息")
    @GetMapping("findAllAddr/{id}")
    public R findAllAddr(@ApiParam(name = "id", value = "用户id", required = true)
                         @PathVariable Integer id) {
        QueryWrapper<CustomerAddr> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id",id);
        List<CustomerAddr> list = addrService.list(wrapper);
        return R.ok().data("list", list);
    }

    //新增地址
    @ApiOperation(value = "新增地址")
    @PostMapping("addAddr")
    public R addAddr(@RequestBody CustomerAddr customerAddr) {
        boolean save = addrService.save(customerAddr);
        if (save)
            return R.ok();
        else
            return R.error();
    }

    //删除地址
    @ApiOperation(value = "删除地址")
    @GetMapping("removeAddr/{id}")
    public R removeAddr(@ApiParam(name = "id",value = "地址id", required = true)
                        @PathVariable Integer id) {
        boolean remove = addrService.removeById(id);
        if (remove)
            return R.ok();
        else
            return R.error();
    }

    //更新地址
    @ApiOperation(value = "更新地址")
    @PostMapping("updateAddr")
    public R updateAddr(@RequestBody CustomerAddr customerAddr) {
        boolean update = addrService.updateById(customerAddr);
        if (update)
            return R.ok();
        else
            return R.error();
    }

    /**
     * 设置默认地址
     *      1.若有默认地址，取消该默认地址，设置该addrId对应地址为默认
     *      2.无默认地址，设置该addrId对应地址为默认
     */
    @ApiOperation(value = "设置默认地址")
    @PostMapping("setDefault/{customerId}/{addrId}")
    public R setDefault(@ApiParam(name = "customerId",value = "用户id",required = true)
                        @PathVariable Integer customerId,
                        @ApiParam(name = "addrId",value = "选中的地址信息id",required = true)
                        @PathVariable Integer addrId) {
        boolean setDefault = addrService.setDefault(customerId, addrId);
        if (setDefault)
            return R.ok();
        else
            return R.error();
    }
}

