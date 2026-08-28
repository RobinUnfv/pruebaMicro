package com.robin.cloud_gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class FiltroGlobal implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("..::.. GLOBAL FILTER STARTED ..::..");
        log.info("Prefilter");
        System.out.println("Prefilter");
        Long startTime = System.currentTimeMillis();

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long endTime = System.currentTimeMillis();
            log.info("Postfilter: Time: {} ms", endTime - startTime);
            System.out.println("Postfilter: Time: " + (endTime - startTime) + " ms");

            exchange.getResponse().getHeaders().add("appCallerName", "Cloud-Gateway");
//            exchange.getResponse().getCookies().add("responseCookie", ResponseCookie.from("cloud-gateway",
//                    "response-value").build());

        }));

    }

}
