package palindrome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Palinedrome
{
    static final Logger logger = LoggerFactory.getLogger(Palinedrome.class);

    public static String reverse(String s)
    {
        return new StringBuilder(s).reverse().toString();
    }

    public static boolean palindrome(String s1, String s2)
    {
        if (s1 == null)
            return s2 == null;
        if (s2 == null)
            return false;
        if (s1.length() != s2.length())
            return false;

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Map<String, Integer> map1 = new HashMap<>();

        for (char c1 : chars1)
        {
            String key = String.valueOf(c1);
            if (map1.containsKey(key))
                map1.put(key, map1.get(key) + 1);
            else
                map1.put(key, 1);
        }
        for (char c2 : chars2)
        {
            String key = String.valueOf(c2);
            if (map1.containsKey(key))
                map1.put(key, map1.get(key) - 1);
            else
                return false;
        }
        return map1.entrySet().stream().filter(entry -> entry.getValue() != 0).count() == 0;
    }

    public static void main(String[] args)
    {
        logger.info("palindrome of .. {}", palindrome("dog", "dgo"));
        logger.info("palindrome of .. {}", palindrome("abcdde", "edcabd"));
    }
}
