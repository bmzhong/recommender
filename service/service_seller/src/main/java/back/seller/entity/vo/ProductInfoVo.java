package back.seller.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductInfoVo {

    private String name;

    private List<String> pictures;

    private String brandName;

    private String brandLogo;

    private String brandDesc;

    private String brandTelephone;

    private String categoryName;

    private Integer sellerId;

    private BigDecimal price;

    private Date productionDate;

    private String description;

    private Integer shelfLife;

}
