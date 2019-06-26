package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OptionalExample
{
    private static final Logger logger = LoggerFactory.getLogger(OptionalExample.class);

    String s1;
    String s2;
    OptionalExample o2;

    public String getS1()
    {
        return s1;
    }

    public void setS1(String s1)
    {
        this.s1 = s1;
    }

    public String getS2()
    {
        return s2;
    }

    public void setS2(String s2)
    {
        this.s2 = s2;
    }

    public OptionalExample getO2()
    {
        return o2;
    }

    public void setO2(OptionalExample o2)
    {
        this.o2 = o2;
    }

    public static void main(String[] args)
    {
        List<String> list = Arrays.asList("this", "that");
        Optional<String> s = list.stream().filter(e -> "none".equals(e)).findFirst();
        String result = s.isPresent() ? s.get() : null;
        System.out.println(MessageFormat.format("arg1 {0}", result));

        result = s.orElse(null);
        System.out.println(MessageFormat.format("result is null: {0}", result));
        assert(result == null);

        Map<String, String> map = new HashMap<String, String>()
        {{
            put("1", "one");
            put("2", "two");
        }};

        System.out.println(map);
        map.put("2", "two changed");
        System.out.println(map);

        OptionalExample oe = null;
        // NPE Exception below: at java.util.Objects.requireNonNull(Objects.java:203)
        /*
        logger.debug("S1 = {}", Optional.of(oe).map(OptionalExample::getS1).orElse(null));
        */
        oe = new OptionalExample();
        OptionalExample oe2 = new OptionalExample();
        oe2.setS2("S2value");
        oe.setO2(oe2);
        logger.debug("S2 = {}", Optional.of(oe).map(OptionalExample::getO2).map(OptionalExample::getS2).orElse(Optional.ofNullable(oe2).map(OptionalExample::getS2).orElse(null)));

        logger.debug("null is String = {}", (null instanceof String));
        logger.debug("S2.equals(null) = {}", oe2.getS2().equals(null));

        logger.debug("Null has = {}", has(null, Arrays.asList("one")));
        oe.setS1("one");
        logger.debug("true has = {}", has(oe, Arrays.asList("one")));
        logger.debug("false has = {}", has(oe, Arrays.asList("two")));
        oe.setS1(null);
        logger.debug("false has = {}", has(oe, Arrays.asList("two")));
    }

    public static boolean has(OptionalExample oe, List<String> stringList) {
        return Optional.ofNullable(oe).filter(e -> stringList.contains(e.getS1())).isPresent();
    }
}