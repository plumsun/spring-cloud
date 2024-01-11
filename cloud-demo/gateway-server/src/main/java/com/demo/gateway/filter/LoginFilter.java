package com.demo.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * The type Login filter.
 *
 * @author LiHaoHan Created on 2024-01-11
 */
//@Component
public class LoginFilter implements GlobalFilter {
    /**
     * 过滤器方法
     *
     * @param exchange 封装了 Request请求 Response响应信息
     * @param chain    过滤器链对象  用于放行过滤器请求
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取请求对象  获取响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 2. 根据请求对象，获取请求头信息， 查看请求头中是否包含 access-token
        String token = request.getHeaders().getFirst("access-token");
        // 3. 如果token不存在  或者 值 不为 admin  终止这个请求 返回401未认证状态
        if (StringUtils.isEmpty(token) || !"admin".equals(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 4. 如果有 并 为admin 放行请求
        return chain.filter(exchange);
    }
}
