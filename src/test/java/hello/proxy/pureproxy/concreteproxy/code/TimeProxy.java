package hello.proxy.pureproxy.concreteproxy.code;

import hello.proxy.utils.DateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private final ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        DateTime startTime = DateTime.now();
        String result = concreteLogic.operation();
        DateTime endTime = DateTime.now();
        log.info("Time Decorator 실행 : {}ms, {} ~ {}"
                , startTime.termMillis(endTime)
                , startTime
                , endTime);
        return result;
    }
}
