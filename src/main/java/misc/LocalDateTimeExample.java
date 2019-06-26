package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class LocalDateTimeExample
{
    public static final Logger logger = LoggerFactory.getLogger(LocalDateTimeExample.class);

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime startTime;

    public LocalDateTimeExample(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }

    public static void main(String[] args)
    {
        logger.debug("Local time: {}", LocalDateTime.now());
        LocalDateTimeExample example = new LocalDateTimeExample(LocalDateTime.now());
        logger.debug("Local time: {}", LocalDateTime.parse("2019-05-22T13:52:04"));
    }
}
