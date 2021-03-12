package com.sjdemo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShardingDatasourceConfig {


    public DataSource dataSource1(){
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

// 配置第 1 个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://47.113.118.139:3306/ds0");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", dataSource1);

// 配置第 2 个数据源
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://47.113.118.139:3306/ds1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        dataSourceMap.put("ds1", dataSource2);

// 配置 t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order${0..1}");

// 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));

// 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));

// 省略配置 t_order_item 表规则...
// ...

// 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

// 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));
        SnowflakeKeyGenerateAlgorithm snowflakeKeyGenerateAlgorithm = new SnowflakeKeyGenerateAlgorithm();
// 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order${order_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

// 创建 ShardingSphereDataSource
        DataSource dataSource = null;
        try {
            dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public DataSource dataSource(){
        String yamlFile = "src/main/resources/application-shardingjdbc.yml";
        DataSource dataSource = null;
        try {
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(yamlFile));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return dataSource;
    }
}
