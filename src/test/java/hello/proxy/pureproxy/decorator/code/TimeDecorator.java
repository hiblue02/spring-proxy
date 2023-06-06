package hello.proxy.pureproxy.decorator.code;

import hello.proxy.utils.DateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {
    private final Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("Time Decorator 실행");
        DateTime startTime = DateTime.now();
        String result = component.operation();
        DateTime endTime = DateTime.now();

        log.info("Time Decorator= 실행시간:{}ms ({}~{})"
                , startTime.termMillis(endTime)
                , startTime.defaultFormat()
                , endTime.defaultFormat());
        return result;
    }

}
