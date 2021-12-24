package com.example.springbootesdemo.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@ConfigurationProperties(prefix = "elasticsearch")
@Configuration
@Data
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
    private String host;
    private Integer port;
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final RestClientBuilder builder = RestClient.builder(new HttpHost(host, port));
        final RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        return restHighLevelClient;
    }
}
