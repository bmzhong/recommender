package back.seller.controller;


import back.common_utils.R;
import back.seller.entity.ProductComment;
import back.seller.entity.vo.ProductCommentVo;
import back.seller.service.ProductCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    @PostMapping("/add/comment/{content}/{customerId}/{orderId}/{productId}/{star}")
    public R addComment (@ApiParam(value = "商品评论内容", required = true)
                            @RequestParam("content") String content,
                         @ApiParam(value = "用户Id", required = true)
                            @RequestParam("customerId") Integer customerId,
                         @ApiParam(value = "订单Id", required = true)
                            @RequestParam("orderId") Integer orderId,
                         @ApiParam(value = "商品Id", required = true)
                            @RequestParam("productId") Integer productId,
                         @ApiParam(value = "评论的星级数", required = true)
                            @RequestParam("star") Integer star) {
        ProductComment productComment = new ProductComment()
                .setProductId(productId)
                .setOrderId(orderId)
                .setCustomerId(customerId)
                .setContent(content)
                .setAuditTime(new Date())
                .setStar(star);
        final boolean save = productCommentService.save(productComment);
        if (save) {
            return R.ok().data("commentId",productComment.getCommentId());
        } else {
            return R.error();
        }
    }

    /**
     * @param productId
     * @return 一个商品的所有评论
     * @author 钟保明
     */
    @ApiOperation("根据productId获取商品的所有评论")
    @GetMapping("/getCommentsByProductId/{productId}")
    public R getCommentsByProductId(@ApiParam(value = "商品id", required = true)
                                    @PathVariable Integer productId) {
        List<ProductCommentVo> commentVoList = productCommentService.getCommentsByProductId(productId);
        if (null == commentVoList) {
            return R.error();
        } else {
            return R.ok().data("commentVoList", commentVoList);
        }
    }
}

