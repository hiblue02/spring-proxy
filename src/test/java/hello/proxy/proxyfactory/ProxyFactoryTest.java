package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceInterface;
import hello.proxy.common.service.ServiceInterfaceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Slf4j
class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        //given
        ServiceInterface target = new ServiceInterfaceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //when
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        //then
        log.info("target.getClass={}", target.getClass());
        log.info("proxy.getClass={}", proxy.getClass());

        proxy.save();

        assertAll(
                () -> assertThat(AopUtils.isAopProxy(proxy)).isTrue()
                , () -> assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue()
                , () -> assertThat(AopUtils.isCglibProxy(proxy)).isFalse()
        );
    }

    @Test
    @DisplayName("구체 클래스가 있으면 CGLIB 프록시 사용")
    void concreteProxy() {
        //given
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //when
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        //then
        log.info("target.getClass={}", target.getClass());
        log.info("proxy.getClass={}", proxy.getClass());

        proxy.call();

        assertAll(
                () -> assertThat(AopUtils.isAopProxy(proxy)).isTrue()
                , () -> assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse()
                , () -> assertThat(AopUtils.isCglibProxy(proxy)).isTrue()
        );
    }

    @Test
    @DisplayName("proxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고 클래스 기반 프록시를 사용한다.")
    void proxyTargetClass() {
        //given
        ServiceInterface target = new ServiceInterfaceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //when
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        //then
        log.info("target.getClass={}", target.getClass());
        log.info("proxy.getClass={}", proxy.getClass());

        proxy.save();

        assertAll(
                () -> assertThat(AopUtils.isAopProxy(proxy)).isTrue()
                , () -> assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse()
                , () -> assertThat(AopUtils.isCglibProxy(proxy)).isTrue()
        );

    }
}
