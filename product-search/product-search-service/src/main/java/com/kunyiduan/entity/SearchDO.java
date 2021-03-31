package com.kunyiduan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author achilles
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020/09/10 09:57:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(indexName = "product_search")
public class SearchDO implements Serializable {

    private static final long serialVersionUID = 6320548148250372657L;

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String productName;

}
