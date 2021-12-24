package com.example.springbootesdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * type决定字段是否可以被分词:FieldType.Keyword可以被分词
 * index决定字段是否可以被检索：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "product")
public class Product {
    @Id
    private Long id;
    @Field(type= FieldType.Text)
    private String title;
    @Field(type= FieldType.Keyword)
    private String category;
    @Field(type= FieldType.Double)
    private double price;
    @Field(type= FieldType.Keyword,index = false)
    private String images;
}
