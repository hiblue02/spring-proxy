package hello.proxy.common.advice;

import hello.proxy.utils.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Time Proxy start");
        DateTime start = DateTime.now();
        Object result = invocation.proceed();
        DateTime end = DateTime.now();
        log.info("Time Proxy end = {}ms", start.termMillis(end));
        return result;
    }
}
