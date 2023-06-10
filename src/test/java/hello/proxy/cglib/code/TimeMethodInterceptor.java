package hello.proxy.cglib.code;

import hello.proxy.utils.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy execute");

        DateTime start = DateTime.now();
        Object result = methodProxy.invoke(target, objects);
        DateTime end = DateTime.now();

        log.info("result:{}ms", start.termMillis(end));

        return result;
    }
}
