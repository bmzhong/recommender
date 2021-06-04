package back.seller.service.impl;

import back.seller.entity.CustomerLogin;
import back.seller.entity.ProductComment;
import back.seller.entity.vo.ProductCommentVo;
import back.seller.mapper.ProductCommentMapper;
import back.seller.service.CustomerLoginService;
import back.seller.service.ProductCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-28
 */
@Service
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment> implements ProductCommentService {

    @Autowired
    ProductCommentService productCommentService;

    @Autowired
    CustomerLoginService customerLoginService;

    public List<ProductCommentVo> getCommentsByProductId(Integer productId) {
        QueryWrapper<ProductComment> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        List<ProductComment> comments = productCommentService.list(wrapper);
        List<ProductCommentVo> commentVos = new ArrayList<>();
        if (null == comments) {
            return null;
        } else {
            for (ProductComment comment : comments) {
                Integer customerId = comment.getCustomerId();
                CustomerLogin customerLogin = customerLoginService.getById(customerId);
                ProductCommentVo commentVo = new ProductCommentVo();
                commentVo.setUsername(customerLogin.getLoginName());
                commentVo.setStar(comment.getStar());
                commentVo.setContent(comment.getContent());
                commentVo.setAuditTime(comment.getAuditTime());
                commentVos.add(commentVo);
            }
            return commentVos;
        }
    }
}
