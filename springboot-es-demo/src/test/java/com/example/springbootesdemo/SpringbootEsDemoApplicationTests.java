package com.example.springbootesdemo;

import com.example.springbootesdemo.dao.ProductDao;
import com.example.springbootesdemo.entity.Product;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootEsDemoApplicationTests {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private ProductDao                productDao;

    /**
     * 会自动识别实体类上的注解
     *
     * @Document(indexName = "product")
     * 创建product索引
     * get查看所有索引：http://localhost:9200/_cat/indices?v
     */
    @Test
    void createIndex() {
        System.out.println("创建索引");
    }

    @Test
    void deleteIndex() {
        //elasticsearchRestTemplate.deleteIndex(Product.class)
    }

    /**
     * 像保存对象一样地保存json数据
     * get根据文档id查看索引中的文档数据
     * http://localhost:9200/product/_doc/2
     */
    @Test
    void save() {
        final Product product = new Product();
        product.setId(2L);
        product.setTitle("华为手机");
        product.setCategory("手机");
        product.setPrice(2999);
        product.setImages("http://www.atguigu/hw.jpg");
        productDao.save(product);
    }

    /**
     * 修改
     * get根据文档id查看索引中的文档数据
     * http://localhost:9200/product/_doc/2
     */
    @Test
    void update() {
        final Product product = new Product();
        product.setId(2L);
        product.setTitle("小米2手机");
        product.setCategory("手机");
        product.setPrice(9999);
        product.setImages("http://www.atguigu/xm.jpg");
        productDao.save(product);
    }

    /**
     * 根据id查询
     * get根据文档id查看索引中的文档数据
     * http://localhost:9200/product/_doc/2
     */
    @Test
    void findById() {
        System.out.println(productDao.findById(2L).get());
    }

    /**
     * 查询所有
     * get根据文档id查看索引中的文档数据
     * 查询全量：http://localhost:9200/product/_search
     */
    @Test
    void findAll() {
        final Iterable<Product> products = productDao.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    void saveAll() {
        final List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Product product = new Product();
            product.setId(Long.valueOf(i));
            product.setTitle(i + "小米手机");
            product.setCategory("手机");
            product.setPrice(1999 + i);
            product.setImages("http://www.atguigu/xm.jpg");
            products.add(product);
        }
        productDao.saveAll(products);
    }

    /**
     * 分页查询
     */
    @Test
    void findByPage() {
        final Sort sort = Sort.by(Sort.Direction.DESC, "id");
        int currentPage = 0;
        int pageSize = 5;
        final PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);
        final Page<Product> productPage = productDao.findAll(pageRequest);
        for (Product product : productPage.getContent()) {
            System.out.println(product);
        }
    }

    /**
     * 条件查询
     * 条件查询结合分页查询
     */
    @Test
    void termQuery() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
    }


}
