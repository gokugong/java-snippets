package misc;

import java.text.MessageFormat;
import java.util.Date;

public class MessageFormatSample
{
    public static void main(String[] args)
    {
        String message = "arg 1 is string: {0}, arg 2 is Date {1}, arg 3 is number {2}.";
        System.out.println(MessageFormat.format(message, "arg1", new Date(), 3));
    }
}