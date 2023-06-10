package hello.proxy.jdkdynamic.code;

import hello.proxy.utils.DateTime;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("Time Proxy start");
        DateTime start = DateTime.now();
        Object result = method.invoke(target, args);
        DateTime end = DateTime.now();
        log.info("Time Proxy end = {}ms", start.termMillis(end) );
        return result;
    }
}
