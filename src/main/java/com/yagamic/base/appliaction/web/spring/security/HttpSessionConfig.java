package com.yagamic.base.appliaction.web.spring.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;




@Configuration
@EnableRedisHttpSession
@PropertySource("classpath:/application.properties")
public class HttpSessionConfig {

    //地址和端口写到配置文件里去 此处是调用consul的地址的
    @Value("${spring.redis.host}")
    String redis_address;//="localhost";

    @Value("${spring.redis.port}")
    Integer redis_port;//=6379;



    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redis_address);
        factory.setPort(redis_port);
        return factory;
    }

    @Bean
    public RedisOperationsSessionRepository sessionRepository() {
        RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(connectionFactory());
        sessionRepository.setDefaultMaxInactiveInterval(60 * 60);
        return sessionRepository;
    }


    //禁用session redis通知，性测时发现很耗性能,如果产生问题，删掉此段
    @Bean
    public InitializingBean enableRedisKeyspaceNotificationsInitializer(RedisConnectionFactory connectionFactory) {
        return new NoAction();
    }


    static class NoAction implements InitializingBean {
        @Override
        public void afterPropertiesSet() throws Exception {

        }
    }
}