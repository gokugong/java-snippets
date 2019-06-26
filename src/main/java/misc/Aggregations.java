package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class Aggregations
{
    static final Logger logger = LoggerFactory.getLogger(Aggregations.class);

    public static void main(String[] args) {
        int weird = IntStream.range(0, 10).reduce(0, (x, y) -> x - y);
        logger.debug("(x - y).reduce = {}", weird);
        logger.debug("(x * y).reduce = {}", IntStream.range(0, 10).reduce(0, (x, y) -> x * y));
        //logger.debug("(x / y).reduce = {}", IntStream.range(0, 10).reduce(0, (x, y) -> x / y));
        logger.debug("(x + y).reduce = {}", IntStream.range(0, 10).mapToDouble(Double::valueOf).reduce(0, Double::sum));
        Integer i = 0;
    }
}
