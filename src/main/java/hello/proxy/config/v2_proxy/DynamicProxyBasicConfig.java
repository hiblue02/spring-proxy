package hello.proxy.config.v2_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_proxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Slf4j
@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        return (OrderRepositoryV1) getProxy(orderRepository, logTrace);
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        return (OrderServiceV1) getProxy(orderServiceV1, logTrace);
    }

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
        return (OrderControllerV1) getProxy(orderControllerV1, logTrace);
    }

    private Object getProxy(Object target, LogTrace logTrace) {
        LogTraceBasicHandler handler = new LogTraceBasicHandler(target, logTrace);
        return Proxy.newProxyInstance(target.getClass().getInterfaces()[0].getClassLoader()
                , new Class[] {target.getClass().getInterfaces()[0]}
                , handler);
    }
}
