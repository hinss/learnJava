package com.hins.redis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RedisProperties {

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle}")
    private Integer minIdle;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private Long maxWaitMillis;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Value("${spring.redis.database:0}")
    private Integer db;

    @Value("${spring.redis.sentinel.password:EMPTY}")
    private String sentinelPassword;

    @Value("${spring.redis.sentinel.master:EMPTY}")
    private String sentinelMaster;

    @Value("${spring.redis.sentinel.nodes:EMPTY}")
    private String sentinelNodes;

    @Value("${spring.redis.cluster.nodes:EMPTY}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects:-1}")
    private Integer clusterMaxRedirects;

    @Value("${spring.redis.cluster.password:EMPTY}")
    private String clusterPassword;

    public boolean sentinelMode(){
        return !"EMPTY".equals(sentinelPassword);
    }
}
