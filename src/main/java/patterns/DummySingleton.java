package patterns;

import java.util.concurrent.atomic.AtomicBoolean;

public class DummySingleton
{
    private static DummySingleton ourInstance = null;

    public static AtomicBoolean lock = new AtomicBoolean(false);

    public static DummySingleton getInstance()
    {
        if (ourInstance == null)
        {
            if (lock.compareAndSet(false, true))
            {
                if (ourInstance == null)
                    ourInstance = new DummySingleton();
            }
        }
        return ourInstance;
    }

    private String greeting = "nope";

    private DummySingleton()
    {
        greeting = "Hello!";
    }

    public String getGreeting() { return greeting; }
}
