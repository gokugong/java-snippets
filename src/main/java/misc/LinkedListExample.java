package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinkedListExample
{
    private static Logger logger = LoggerFactory.getLogger(LinkedListExample.class);

    private static LinkedListExample ourInstance = new LinkedListExample();

    public static LinkedListExample getInstance()
    {
        return ourInstance;
    }

    private LinkedListExample()
    {
    }

    public static void main(String[] args)
    {
        List<Integer> integerList = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        List<Integer> linkedList = new LinkedList<>(integerList);
        List<Integer> linkedListfiltered = linkedList.stream().filter(i -> i < 5).collect(Collectors.toList());
        logger.debug("filterlist = {}", linkedListfiltered);
        linkedListfiltered = linkedList.stream().filter(i -> i % 3 == 1).collect(Collectors.toList());
        Integer[] linkListToArray = linkedListfiltered.toArray(new Integer[linkedListfiltered.size()]);
        logger.debug("filterlist = {}", linkedListfiltered);
    }
}
