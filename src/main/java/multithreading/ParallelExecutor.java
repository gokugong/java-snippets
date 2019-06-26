package multithreading;

import java.util.Map;

public interface ParallelExecutor<T>
{
    Object execute(T target, Map<String, Object> arguments);
}
