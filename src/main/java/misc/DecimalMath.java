package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class DecimalMath
{
    static final Logger logger = LoggerFactory.getLogger(DecimalMath.class);

    public static void main(String[] args) {
        BigDecimal d = new BigDecimal(5);
        BigDecimal percentage = d.divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP);
        logger.debug("percentage: {}", percentage);
    }
}
