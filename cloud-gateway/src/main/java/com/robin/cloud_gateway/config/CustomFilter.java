package com.robin.cloud_gateway.config;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.CustomConfiguration> {

    public CustomFilter() {
        super(CustomConfiguration.class);
    }
//    @Override
//    public List<String> shortcutFieldOrder() {
//        return super.shortcutFieldOrder();
//    }

    @Override
    public GatewayFilter apply(CustomConfiguration config) {
        return ((exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable( () -> {
              exchange.getResponse().getHeaders().add(config.getHeaderKey(), config.getHeaderValue());
              //exchange.getResponse().getCookies().add("responseCookie", ResponseCookie.from("responseCookie", "Mitocode-Cliente-Service").build());
        } )) );
    }

//    @Override
//    public String name() {
//        return super.name();
//    }

    @Data
    public static class CustomConfiguration {
        private String headerKey;
        private String headerValue;
    }
}
