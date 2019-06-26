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

import java.io.*;
import java.util.*;

public class MaxStockProfit
{
    private static final String inputFilePattern = "src/main/resources/misc/hr/MaxStockProfit/in.txt";
    private static final String outputFilePattern = "src/main/resources/misc/hr/MaxStockProfit/out.txt";

    // Complete the stockmax function below.
/*
    static int stockmaxError(int[] prices)
    {
        int result = 0;
        int start = 0;
        int trailingSum = prices[start];
        for (int i = 1; i < prices.length; i++)
        {
            if (prices[i] < prices[start] || prices[i] < prices[i - 1])
            {
                int count = i - start - 1;
                if (count > 0)
                    result += (prices[i - 1] * (count + 1) - trailingSum);
                // need reset
                start = i;
                trailingSum = 0;
            } else if (i == prices.length - 1 && i > start)
            {
                result += (prices[i] * (i - start) - trailingSum);
            }
            trailingSum += prices[i];
        }
        return result;
    }
*/

    static int stockmax(int[] prices)
    {
        if (prices.length < 2) return 0;

        int end = prices.length - 1;
        int result = 0;
        for (int i = end - 1; i >= 0; i--)
        {
            if (prices[i] > prices[end])
            {
                // end of winning streak
                if (end - i - 1 > 0)
                {
                    for (int j = i + 1; j < end; j++)
                    {
                        result += (prices[end] - prices[j]);
                    }
                }
                end = i;
            } else if (i == 0) {
                for (int j = i; j < end; j++)
                    result += (prices[end] - prices[j]);
            }
        }
        return result;
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

    public static void main(String[] args) throws IOException
    {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePattern));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++)
        {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] prices = new int[n];

            String[] pricesItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++)
            {
                int pricesItem = Integer.parseInt(pricesItems[i]);
                prices[i] = pricesItem;
            }

            int result = stockmax(prices);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
