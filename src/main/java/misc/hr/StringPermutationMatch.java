/*
Problem Description

Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc. (WOT) will be for the next number of days.

Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own, or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?

For example, if you know that prices for the next two days are , you should buy one share day one, and sell it day two for a profit of . If they are instead , no profit can be made so you don't buy or sell stock those days.

Function Description

Complete the stockmax function in the editor below. It must return an integer that represents the maximum profit achievable.

stockmax has the following parameter(s):

prices: an array of integers that represent predicted daily stock prices
Input Format

The first line contains the number of test cases .

Each of the next  pairs of lines contain:
- The first line contains an integer , the number of predicted prices for WOT.
- The next line contains n space-separated integers , each a predicted stock price for day .

Constraints

Output Format

Output  lines, each containing the maximum profit which can be obtained for the corresponding test case.

Sample Input

3
3
5 3 2
3
1 2 100
4
1 3 1 2
Sample Output

0
197
3
Explanation

For the first case, you cannot obtain any profit because the share price never rises.
For the second case, you can buy one share on the first two days and sell both of them on the third day.
For the third case, you can buy one share on day 1, sell one on day 2, buy one share on day 3, and sell one share on day 4.
 */

package misc.hr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StringPermutationMatch
{
    private static final Logger logger = LoggerFactory.getLogger(StringPermutationMatch.class);
    private static final String inputFilePattern = "src/main/resources/misc/hackerrank/StringPermutationMatch/in.txt";
    private static final String outputFilePattern = "src/main/resources/misc/hackerrank/StringPermutationMatch/out.txt";

    static String[] getPermutations(String[] words)
    {
        List<String> result = new ArrayList<>();
        if (words == null || words.length < 2)
            return result.toArray(new String[0]);

        int len = words.length;
        int[] primes = getPrimes(26, 200); // get 26 primes, corresponding a - z
        int[] wordHashNumber = new int[len];

        for (int i = 0; i < len; i++)
        {
            wordHashNumber[i] = getWordHashNumber(words[i], primes);
        }

        for (int i = 0; i < len; i++)
        {
            StringBuilder sb = new StringBuilder();
            for (int j = i + 1; j < len; j++)
            {
                if (wordHashNumber[i] == wordHashNumber[j])
                    sb.append(" ").append(words[j]);
            }
            if (sb.length() > 0)
                result.add(words[i] + sb.toString());
        }

        return result.toArray(new String[0]);
    }

    static String[] getPermutations2(String[] words)
    {
        List<String> result = new ArrayList<>();
        if (words == null || words.length < 2)
            return result.toArray(new String[0]);

        int len = words.length;
        int[] primes = getPrimes(26, 200); // get 26 primes, corresponding a - z

        Map<Integer, List<String>> wordPermutations = Arrays.stream(words).collect(Collectors.groupingBy(word -> getWordHashNumber(word, primes)));

        wordPermutations.forEach((k, v) -> {
            if (v != null && v.size() > 1)
            {
                result.add(v.stream().collect(Collectors.joining(" ")));
            }
        });

        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    private static int getWordHashNumber(String word, int[] primes)
    {
        char[] chars = word.toCharArray();
        int rv = 1;
        for (int i = 0; i < chars.length; i++)
        {
            rv *= primes[chars[i] - 'a'];
        }
        return rv;
    }

    private static int[] getPrimes(int totalNumberToGenerate, int n)
    {
        int[] rv = new int[totalNumberToGenerate];
        int counter = 0;
        boolean[] isPrimes = new boolean[n];
        Arrays.fill(isPrimes, true);
        for (int i = 2; i < n; i++)
        {
            if (counter >= totalNumberToGenerate)
                break;
            if (isPrimes[i])
                for (int j = 2; j * j < i; j++)
                {
                    if (i % j == 0)
                    {
                        isPrimes[j] = false;
                        break;
                    }
                }
            if (isPrimes[i])
                rv[counter++] = i;
            for (int k = i * 2; k < n; k += i)
            {
                isPrimes[k] = false;
            }
        }

        if (counter < totalNumberToGenerate)
        {
            logger.error("Only {} number of primes generated: {}", counter, rv);
        }

        return rv;
    }

    private static Scanner scanner = null;

    static
    {
        try
        {
            scanner = new Scanner(new File(inputFilePattern));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    static final String DIVIDER = "/";

    public static List<String> ReduceFraction(List<String> fractions)
    {
        List<String> results = new ArrayList<>();
        if (fractions == null || fractions.isEmpty())
        {
            return results;
        }

        for (String element : fractions)
        {
            String result;
            String[] numbers = element.split(DIVIDER);
            int first = Integer.parseInt(numbers[0]);
            int second = Integer.parseInt(numbers[1]);
            int bound = Math.max(first, second) / 2 + 1;

            for (int i = 2; i < bound; i++)
            {
                if (first % i == 0 && second % i == 0)
                {
                    first /= i;
                    second /= i;
                }
            }
            if (first == second)
                result = "1";
            else if (first % second == 0)
                result = Integer.toString(first / second);
            else
                result = "" + first + DIVIDER + second;

            results.add(result);
        }

        return results;
    }

    public static void main(String[] args) throws IOException
    {
/*
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePattern));

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String[] words = scanner.nextLine().split(" ");

        String[] result = getPermutations(words);
        String[] result2 = getPermutations2(words);

        for (int i = 0; i < result.length; i++)
        {
            assert(result[i].equals(result2[i]));
            bufferedWriter.write(result[i]);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
*/
    }

    public static List<Integer> removeShortest(List<Integer> lengths)
    {
        int min = lengths.stream().reduce(Integer::min).get();
        return lengths.stream().filter(i -> i == min).collect(Collectors.toList());
    }

    public static List<Integer> cutSticks(List<Integer> lengths)
    {
        List<Integer> counts = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        numbers.addAll(lengths);
        int count = numbers.size();
        counts.add(count);

        while (count > 1)
        {
            int min = numbers.stream().reduce(Integer::min).get();
            numbers = numbers.stream().filter(i -> i > min).collect(Collectors.toList());
            count = numbers.size();
            counts.add(count);
        }


        return counts;
    }

}
