package misc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.UnaryOperator;

public class StringReverser
{
    private static Logger logger = LoggerFactory.getLogger(StringReverser.class);
    public static String reverse(String s)
    {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++)
        {
            char c = chars[i];
            int oppositeIndex = chars.length - i - 1;
            chars[i] = chars[oppositeIndex];
            chars[oppositeIndex] = c;
        }
        return String.valueOf(chars);
    }

    public static String reverse4(String s)
    {
        char[] chars = new char[s.length()];
        int len = chars.length;
        for (int i = 0; i < len; ++i)
        {
            chars[i] = s.charAt(len - 1 - i);
            chars[len - 1 - i] = s.charAt(i);
        }
        return String.valueOf(chars);
    }

    public static UnaryOperator<String> reverse2 = s -> StringUtils.reverse(s);

    public static UnaryOperator<String> reverse3 = s -> new StringBuilder(s).reverse().toString();

    public static void main(String[] args)
    {
        String s = "test this and that to reverse";
        System.out.println("Reverse of " + s + " = ");
        System.out.println("Reverse1 = " + reverse(s));
        System.out.println("Reverse2 = " + reverse2.apply(s));
        System.out.println("Reverse3 = " + reverse3.apply(s));
        logger.debug("Reverse4 = {}", reverse4(reverse(s)));

        logger.debug("0500 = {}", Integer.parseInt("0500"));
    }
}
