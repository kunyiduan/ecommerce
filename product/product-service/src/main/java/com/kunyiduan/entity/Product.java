package com.kunyiduan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 品牌编号
     */
    private Integer brandId;

    /**
     * 分类编号
     */
    private Integer categoryId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 促销价格
     */
    private BigDecimal promotionPrice;

    /**
     * 购买是否有积分
     */
    @TableField(value = "is_send_points")
    private Boolean SendPoints;

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
    private Integer status;

    /**
     * 图片地址1
     */
    private String picture1;

    /**
     * 图片地址1伪hash索引
     */
    private Integer pic1Crc;

    /**
     * 图片地址2
     */
    private String picture2;

    /**
     * 图片地址3
     */
    private String picture3;

    /**
     * 图片地址4
     */
    private String picture4;

    /**
     * 图片地址5
     */
    private String picture5;

    /**
     * 图片地址6
     */
    private String picture6;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
