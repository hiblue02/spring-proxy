package hello.proxy.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private final LocalDateTime time;

    private DateTime(LocalDateTime localDateTime) {
        this.time = localDateTime;
    }

    public static DateTime now() {
        return new DateTime(LocalDateTime.now());
    }

    public long termMillis(DateTime timeIn) {
        return Math.abs(Duration.between(timeIn.time, time).toMillis());
    }

    public String defaultFormat() {
        return time.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public String format(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return time.format(formatter);
    }
}
