package com.example.demofour.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetSocketAddress;


public class EsConfig {
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String EsHost;

    @Value("${spring.data.elasticsearch.clustername}")
    private String EsClusterName;


    public Client client() throws Exception {
        TransportClient client;
        Settings settings = Settings.builder().put("cluster.name", EsClusterName) // 设置ES实例的名称
                .put("client.transport.sniff", true) // 自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .build();
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(new InetSocketAddress("192.168.74.1", 9300)));
        client.addTransportAddress(new TransportAddress(new InetSocketAddress("192.168.74.1", 9500)));
        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}
