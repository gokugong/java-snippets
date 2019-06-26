package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class KthOfN
{
    public static final Logger logger = LoggerFactory.getLogger(KthOfN.class);

    public static PriorityQueue<Integer> getTopK(int[] arr, int k)
    {
        //PriorityQueue<Integer> minQ = new PriorityQueue<>(k);
        PriorityQueue<Integer> minQ = new PriorityQueue<>(k, Comparator.reverseOrder());
        IntStream.range(0, k).forEach(i -> minQ.add(new Integer(arr[i])));
        for (int i = k; i < arr.length; i++) {
            Integer topK = minQ.peek();
            if (topK > arr[i])
            {
                minQ.poll();
                minQ.add(new Integer(arr[i]));
            }
        }
        return minQ;
    }

    public static Comparator<Integer> ic = Integer::compareTo;

    public static void main(String[] args)
    {
        Random random = new Random();
        int[] arr = new int[100];
        IntStream.range(0, 100).forEach(i -> arr[i] = random.nextInt(100));

        List<Integer> minK = getTopK(arr, 10).stream().sorted().collect(Collectors.toList());


        logger.debug("max k integers: {}", minK);
        List<Integer> ints = Arrays.stream(arr).boxed().collect(Collectors.toList());
        //Collections.sort(ints, Collections.reverseOrder());
        Collections.sort(ints);
        logger.debug("all integers: {}", ints);
    }
}
