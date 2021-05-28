package back.seller.controller;


import back.common_utils.R;
import back.seller.entity.ProductComment;
import back.seller.service.ProductCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-01
 */
@RestController
@RequestMapping("/seller/product-comment")
public class ProductCommentController {

    @Autowired
    private ProductCommentService productCommentService;

    /**
     * @param productComment 传入的商品评论信息封装的对象
     * @return 返回增加操作成功与否
     * @author :张琦
     */
    @ApiOperation("增加商品评论")
    @RequestMapping("/add/comment")
    public R addComment (@ApiParam(value = "商品评论信息", required = true)
                                @RequestBody ProductComment productComment) {
        final boolean save = productCommentService.save(productComment);
        if (save) {
            return R.ok().data("commentId",productComment.getCommentId());
        } else {
            return R.error();
        }
    }

}

