package functional;

import java.math.BigDecimal;
import java.util.function.Consumer;

@FunctionalInterface
public interface BigDecimalConsumer<T extends BigDecimal> extends Consumer<T>
{
}
