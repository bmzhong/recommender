package back.customer.controller;


import back.common_utils.R;
import back.customer.entity.CustomerInf;
import back.customer.service.CustomerInfService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author plyn
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/customer/customer-inf")
@CrossOrigin
public class CustomerInfController {
    @Autowired
    private CustomerInfService customerInfService;

    /*
     * 功能描述:
     * 〈查询客户信息〉
     *
     * @author : yzh
     * @date : 2021-4-23
     */
    //根据id查询客户信息
    @ApiOperation(value = "查询客户信息")
    @GetMapping("/data/customer/{id}")
    public R findCustomer(@ApiParam(name = "id", value = "客户id", required = true)
                          @PathVariable Integer id) {
        QueryWrapper<CustomerInf> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id", id);
        CustomerInf customerInf = customerInfService.getOne(queryWrapper);
        if (customerInf == null)
            return R.error().data("msg", "no data found");
        else
            return R.ok().data("customerInf", customerInf);
    }

    /*
     * 功能描述:
     * 〈编辑客户信息〉
     *
     * @author : yzh
     * @date : 2021-4-23
     */
    //编辑客户信息
    @ApiOperation(value = "编辑客户信息")
    @PostMapping("/edit/customer/others")
    public R editCustomer(@RequestBody CustomerInf customerInf) {
        boolean result = customerInfService.updateById(customerInf);
        if (result) {
            return R.ok();
        }
        return R.error();
    }
}

