package com.kunyiduan.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author achilles
 * @since 2020-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 分类编号
     */
    private Integer categoryId;

    /**
     * 购买是否有积分
     */
    private Boolean isPoints;

    /**
     * 可购买数量
     */
    private Integer quantity;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 状态：0-未上架，1-预售，2-上架，3-下架
     */
    private Boolean status;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
