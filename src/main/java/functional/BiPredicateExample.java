package functional;

import java.util.function.BiPredicate;

public class BiPredicateExample
{
    public static void main(String[] args)
    {
        BiPredicate<Integer, Integer> gt = (x, y) -> x > y;

        BiPredicate<Integer, Integer> eq = (x, y) -> x == y;

        System.out.println(gt.test(2, 3));  // false
        System.out.println(gt.or(eq).test(2, 3));   // false
        System.out.println(gt.or(eq).test(3, 3));   // true
    }
}

