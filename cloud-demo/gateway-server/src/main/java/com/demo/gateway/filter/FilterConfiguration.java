package com.demo.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * The type Filter configuration.
 *
 * @author LiHaoHan Created on 2024-01-11
 */
@Slf4j
@Configuration
public class FilterConfiguration {

    /**
     * Global filter 1 global filter.
     *
     * @return the global filter
     */
    @Bean
    @Order(-2)
    public GlobalFilter globalFilter1() {
        return ((exchange, chain) -> {
            log.info("过滤器1的pre阶段！");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("过滤器1的post阶段！");
            }));
        });
    }

    /**
     * Global filter 2 global filter.
     *
     * @return the global filter
     */
    @Bean
    @Order(-1)
    public GlobalFilter globalFilter2() {
        return ((exchange, chain) -> {
            log.info("过滤器2的pre阶段！");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("过滤器2的post阶段！");
            }));
        });
    }

    /**
     * Global filter 3 global filter.
     *
     * @return the global filter
     */
    @Bean
    @Order(0)
    public GlobalFilter globalFilter3() {
        return ((exchange, chain) -> {
            log.info("过滤器3的pre阶段！");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("过滤器3的post阶段！");
            }));
        });
    }
}
