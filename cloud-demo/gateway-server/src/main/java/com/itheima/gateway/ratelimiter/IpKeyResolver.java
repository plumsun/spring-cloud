package com.itheima.gateway.ratelimiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @作者 itcast
 * @创建日期 2021/3/22 16:20
 **/
@Component
public class IpKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        // 为每一个请求路径 准备一个令牌桶
        return Mono.just(exchange.getRequest().getURI().getPath());
    }
}
