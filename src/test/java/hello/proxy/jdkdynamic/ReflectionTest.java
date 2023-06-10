package hello.proxy.jdkdynamic;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    @DisplayName("reflection0")
    void reflection0() {
        //given
        Hello target = new Hello();
        //when
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);

        log.info("start");
        String result2 = target.callA();
        log.info("result={}", result2);

    }

    @Test
    @DisplayName("reflection1")
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello hello = new Hello();
        //when
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(hello);
        log.info("result={}", result1);

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(hello);
        log.info("result={}", result2);

        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
    }

    @Test
    @DisplayName("reflection2")
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello hello = new Hello();
        //when
        dynamicCall(classHello.getMethod("callA"), hello);
        dynamicCall(classHello.getMethod("callB"), hello);
    }

    private void dynamicCall(Method method, Object object) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(object);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("call A");
            return "A";
        }

        public String callB() {
            log.info("call B");
            return "B";
        }
    }
}
