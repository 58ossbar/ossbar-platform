package com.ossbar.modules.common.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
public class RedissonConfig {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Value("${spring.redis.host:}")
    private String host;
    @Value("${spring.redis.port:}")
    private String port;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.database:}")
    private String database;

    /**
     * 所有对redisson的使用都是通过RedissonClient对象
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws IOException {
        log.info("redis 配置");
        log.info("spring.redis.host => {}", host);
        log.info("spring.redis.port => {}", port);
        log.info("spring.redis.password => {}", password);
        log.info("spring.redis.database => {}", database);
        // 1 创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        if (StringUtils.isNotBlank(password)) {
            config.useSingleServer().setPassword(password);
        }
        config.useSingleServer().setDatabase(Integer.parseInt(database));
        // 2 根据Config创建出RedissonClient实例
        return Redisson.create(config);
    }

}
