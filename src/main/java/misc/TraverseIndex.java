package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TraverseIndex
{
    private static Logger logger = LoggerFactory.getLogger(TraverseIndex.class);

    public static int getNextIndex(int current, List<Integer> list)
    {
        current = (current < 0 || current >= list.size()) ? -1 : current;

        int nextIndex = IntStream
                .concat(
                        IntStream.range(current + 1, list.size()),
                        IntStream.rangeClosed(0, current))
                .filter(i -> list.get(i) >= 0)
                .findFirst()
                .orElse(-1);

        return nextIndex;
    }

    public static int[] getIntRangeSecondHalfFirst(int mid, int size)
    {
        return IntStream.concat(IntStream.range(mid + 1, size), IntStream.rangeClosed(0, mid)).toArray();
    }

    public static void testRange()
    {
        logger.debug("range break (9, 10) = {}", getIntRangeSecondHalfFirst(9, 10));
    }

    public static void main(String args[])
    {
        testRange();

        List<Integer> intList = IntStream.range(0, 10).boxed().collect(Collectors.toList());

        BigDecimal d = null;
        logger.debug("null BigDecimal is {} compare to zero", BigDecimal.ZERO.compareTo(d));

        IntWrap intWrap = new IntWrap();
        IntWrap2 intWrap2 = new TraverseIndex().new IntWrap2();
        logger.debug("i={} = {}", intWrap.i, intWrap2.i);
        logger.debug("Next of - 1 = {}", getNextIndex(-1, intList));
        logger.debug("Next of 2 = {}", getNextIndex(2, intList));
        logger.debug("Next of 10 = {}", getNextIndex(10, intList));
        logger.debug("Next of 9 = {}", getNextIndex(9, intList));
        logger.debug("Next of 8 = {}", getNextIndex(8, intList));
        logger.debug("Next of 11 = {}", getNextIndex(11, intList));
        logger.debug("Next of -2 = {}", getNextIndex(-2, intList));
    }

    public static class IntWrap
    {
        int i;
    }

    public class IntWrap2
    {
        int i;
    }
}
