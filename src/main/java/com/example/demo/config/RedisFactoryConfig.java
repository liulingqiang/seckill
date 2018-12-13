package com.example.demo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liulq on 2018-11-22 .
 */
@Configuration
public class RedisFactoryConfig {
    @Resource
    RedisProperties redisProperties;

    /**
     * 配置连接池参数
     *
     * @return GenericObjectPool
     */
    @Bean
    public GenericObjectPoolConfig getRedisConfig() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        genericObjectPoolConfig.setMaxTotal(redisProperties.getMaxActive());
        genericObjectPoolConfig.setMinIdle(redisProperties.getMinIdle());
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        //genericObjectPoolConfig.setBlockWhenExhausted(isBlockWhenExhausted);
        genericObjectPoolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        //在borrow一个实例时，是否提前进行validate操作；如果为true，则得到的实例均是可用的
        genericObjectPoolConfig.setTestOnBorrow(redisProperties.isTestOnBorrow());
        //调用returnObject方法时，是否进行有效检查
        //genericObjectPoolConfig.setTestOnReturn(isTestOnReturn);
        //在空闲时检查有效性, 默认false
        //genericObjectPoolConfig.setTestWhileIdle(isTestWhileIdle);
        //表示idle object evitor两次扫描之间要sleep的毫秒数；
        //genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //表示一个对象至少停留在idle状态的最短时间，
        //然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
        //genericObjectPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);


        return genericObjectPoolConfig;
    }

    @Bean
    public LettuceConnectionFactory  lettuceConnectionFactory(GenericObjectPoolConfig genericObjectPoolConfig) {
        // 单机版配置
       /* RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));*/

        // 集群版配置
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
       String[] serverArray = redisProperties.getNodes().split(",");
       Set<RedisNode> nodes = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }
        //redisClusterConfiguration.setPassword(RedisPassword.of(password));
        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(redisProperties.getMaxRedirects());

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(redisProperties.getTimeout()))
                .poolConfig(genericObjectPoolConfig)
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisClusterConfiguration,clientConfig);

        return factory;
    }


   /* @Bean
    public RedisConnectionFactory myLettuceConnectionFactory() {
        Map<String, Object> source = new HashMap<String, Object>();

        source.put("spring.redis.cluster.nodes", redisProperties.getNodes());
        source.put("spring.redis.cluster.timeout", redisProperties.getTimeout());
        source.put("spring.redis.cluster.max-redirects", redisProperties.getMaxRedirects());

        RedisClusterConfiguration redisClusterConfiguration;
        redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        return new LettuceConnectionFactory(redisClusterConfiguration);

    }*/
}
