/*
 *  CustomerAddrServiceImpl.java, 2021-04-09
 *
 *  Copyright 2021  PENG L.Y.N, Inc. All rights reserved.
 */

package back.customer.service.impl;

import back.customer.entity.CustomerAddr;
import back.customer.mapper.CustomerAddrMapper;
import back.customer.service.CustomerAddrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * 用户地址管理
 * </p>
 *
 * @author plyn
 * @since 2021-04-08
 */
@Service
public class

CustomerAddrServiceImpl extends ServiceImpl<CustomerAddrMapper, CustomerAddr> implements CustomerAddrService {


    @Override
    public boolean setDefault(Integer customerId, Integer addrId) {

        //选中的地址项
        CustomerAddr addr = getById(addrId);

        //获取该用户的默认地址项
        QueryWrapper<CustomerAddr> wrapper = new QueryWrapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", customerId);
        params.put("is_default", 1);
        wrapper.allEq(params);
        CustomerAddr defaultOne = getOne(wrapper);

        if (defaultOne != null) { //该用户设置了默认地址，取消原默认
            defaultOne.setIsDefault(0);
            updateById(defaultOne);
        }

        //设置输入地址项为默认
        addr.setIsDefault(1);
        updateById(addr);

        return true;
    }


}
