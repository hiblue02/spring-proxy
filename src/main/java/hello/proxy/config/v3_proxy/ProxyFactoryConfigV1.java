package hello.proxy.config.v3_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v3_proxy.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
        ProxyFactory proxyFactory = getProxyFactory(logTrace, orderControllerV1);
        return (OrderControllerV1) proxyFactory.getProxy();
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        ProxyFactory proxyFactory = getProxyFactory(logTrace, orderServiceV1);
        return (OrderServiceV1) proxyFactory.getProxy();
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();
        ProxyFactory proxyFactory = getProxyFactory(logTrace, orderRepositoryV1);
        return (OrderRepositoryV1) proxyFactory.getProxy();
    }


    private static ProxyFactory getProxyFactory(LogTrace logTrace, Object object) {
        ProxyFactory proxyFactory = new ProxyFactory(object);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        return proxyFactory;
    }

    private static PointcutAdvisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
    }

}
