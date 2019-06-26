package misc;

import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class MapInitializor
{
    private static void initMapBad(Map<Long, String> map)
    {
        map = Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>(new Long(1L), "element 1"),
                new AbstractMap.SimpleEntry<>(new Long(2L), "element 1"),
                new AbstractMap.SimpleEntry<>(new Long(3L), "element 1"),
                new AbstractMap.SimpleEntry<>(new Long(4L), "element 1"))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)));
        System.out.println(MessageFormat.format("map={0}", map));
    }

    private static void initMapOk(Map<Long, String> map)
    {
        map.put(1L, "element 1");
        map.put(2L, "element 12");
        System.out.println(MessageFormat.format("map={0}", map));
    }

    private static void doIntStream()
    {
        int sum = 0;
        sum = IntStream.range(1, 1000).filter(i -> i % 3 == 0).sum();
        System.out.println("Sum of 3 multiples from 1 to 1000: " + sum);

        sum = 0;
        for (int i = 1; i < 1001; ++i) {
            sum += (i % 3 == 0) ? i : 0;
        }
        System.out.println("Sum of 3 multiples from 1 to 1000: " + sum);
    }

    public static void main(String[] args)
    {
        Map<Long, String> badMap = new HashMap<>();
        initMapBad(badMap);
        System.out.println(MessageFormat.format("badMap map={0}", badMap));

        Map<Long, String> goodMap = new HashMap<>();
        initMapOk(goodMap);
        System.out.println(MessageFormat.format("goodMap map={0}", goodMap));

        doIntStream();

        // covert int to double
        List<Double> doubleList = IntStream.range(0, 10).asDoubleStream().map(d -> d / 10).boxed().collect(Collectors.toList());
        System.out.println("doubleList=" + doubleList);

        int arr[] = IntStream.range(0, 10).toArray();
        List<Double> doubleList2 = Arrays.stream(arr).asDoubleStream().map(d -> d / 10).boxed().collect(Collectors.toList());
        System.out.println("doubleList2=" + doubleList2);

        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        // throw NPE below, value cannot be null
        //System.out.println("add null value =" + concurrentMap.put("test", null));
        System.out.println("remove null value =" + concurrentMap.remove("test"));
        System.out.println("remove null value =" + concurrentMap.get("test"));
    }
}
