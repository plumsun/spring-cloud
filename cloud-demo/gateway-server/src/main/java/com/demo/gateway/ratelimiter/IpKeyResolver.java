package com.demo.gateway.ratelimiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * The type Ip key resolver.
 *
 * @author LiHaoHan Created on 2024-01-11
 */
@Component
public class IpKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        // 为每一个请求路径 准备一个令牌桶
        return Mono.just(exchange.getRequest().getURI().getPath());
    }
}
