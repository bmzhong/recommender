package back.login.service;

import back.login.entity.Vo.RegisterSellerVo;
import back.login.entity.Vo.RegisterVo;

public interface registerService {
    int registerAccount(RegisterVo registerVo);

    int registerSeller(RegisterSellerVo registerSellerVo);
}
