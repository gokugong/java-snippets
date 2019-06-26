package context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Context
{
    private static final ThreadLocal<Context> threadLocal = new InheritableThreadLocal<>();

    private final Map<String, Object> managed = new ConcurrentHashMap<>();

    public static Context getContext()
    {
        return threadLocal.get();
    }

    public static Context settContext(Context context)
    {
        threadLocal.set(context);
        return context;
    }

    public static Context newContext()
    {
        Context ctx = new Context();
        settContext(ctx);
        return ctx;
    }

    public static void removeContext()
    {
        threadLocal.remove();
    }
}
