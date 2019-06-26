package functional;

import java.math.BigDecimal;

public class BigDecimalConsumerExample
{
    static BigDecimal sum = BigDecimal.ZERO;

    public static void main(String[] args)
    {
        BigDecimalConsumer<BigDecimal> addition = b -> sum = sum.add(b);

        addition.accept(BigDecimal.ONE);
        System.out.println("sum = " + sum);
    }
}
