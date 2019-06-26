package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelExecution<T>
{
    private static final Logger logger = LoggerFactory.getLogger(ParallelExecution.class);

    private final Collection<T> processingTargets;
    private final int processorCount;
    private final Map<String, Object> processingArguments = new ConcurrentHashMap<>();

    private final List<Object> responses = new ArrayList<>();

    public ParallelExecution(Collection<T> processingTargets)
    {
        this(processingTargets, 0);
    }

    public ParallelExecution(Collection<T> processingTargets, int processorCount)
    {
        this.processingTargets = processingTargets;
        this.processorCount = processorCount;
    }

    public ParallelExecution<T> addProcessingArgument(String argName, Object arg)
    {
        processingArguments.put(argName, arg);

        return this;
    }

    public ParallelExecution<T> execute(ParallelExecutor<T> executor)
    {
        final int max = Runtime.getRuntime().availableProcessors() + 1;

        int threadCount = (processorCount > 0 && processorCount <= max) ? processorCount : max;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        Observable<T> ouris = Observable.from(processingTargets);

        ouris.flatMap(
                val -> Observable.just(val).subscribeOn(Schedulers.from(executorService))
                        .map(i -> executor.execute(i, processingArguments))
                        .doOnError(
                                throwable -> {
                                    logger.warn("ParallelExecution.execute(): error in executing the job for " + executor.getClass().getName(), throwable);
                                }
                        )
        )
                .toBlocking().forEach(response -> responses.add(response));

        return this;
    }

    public List<Object> getResponses()
    {
        return responses;
    }
}
