package back.seller.service;

import back.seller.entity.ProductComment;
import back.seller.entity.vo.ProductCommentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-01
 */
public interface ProductCommentService extends IService<ProductComment> {
    public List<ProductCommentVo> getCommentsByProductId(Integer productId);
}
