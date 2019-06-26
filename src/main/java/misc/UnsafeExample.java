package misc;

import java.util.HashMap;
import java.util.Map;

/**
 * {@url http://java-performance.info/memory-introspection-using-sun-misc-unsafe-and-reflection/}
 */
public class UnsafeExample
{
    private final Map<String, String> A_CONSTANT_MAP = new HashMap<>();

    public static void main(String[] args) {
        UnsafeExample unsafeExample = new UnsafeExample();
        assert(unsafeExample.A_CONSTANT_MAP != null);

    }
}
