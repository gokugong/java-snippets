package misc.hr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class PrimeGenerator
{
    static final Logger logger = LoggerFactory.getLogger(PrimeGenerator.class);

    static int[] getPrimes(int totalNumberToGenerate, int n)
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
                isPrimes[k] = false;
        }

        if (counter < totalNumberToGenerate)
        {
            logger.error("Only {} number of primes generated: {}", counter, rv);
        }

        return rv;
    }

    public static void main(String[] args) {
        int[] primes = getPrimes(26, 200);
        String primeList = Arrays.stream(primes).boxed().map(i -> String.valueOf(i)).collect(joining(", "));
        String message = MessageFormat.format("Primes[{0}]={1}", primes.length, primeList);
        System.out.println(message);
    }
}
