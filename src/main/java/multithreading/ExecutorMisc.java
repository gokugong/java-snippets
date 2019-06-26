package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;


/**
 * ideas from: @see
 * <a href="http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/">
 * http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/</a>
 */

public class ExecutorMisc
{
    int count = 0;

    void increment()
    {
        ++count;
    }

    void plainLoopCount()
    {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::increment));
        stop(executor);
        System.out.println("plain loop count: " + count);  // 9997
    }

    void stop(ExecutorService executorService)
    {
        executorService.shutdown();
        try
        {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS))
            {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e)
        {
            executorService.shutdownNow();
        }
    }

    ReentrantLock reentrantLock = new ReentrantLock();

    void reentrantLockIncrement() {
        reentrantLock.lock();
        try {
            ++count;
        } finally {
            reentrantLock.unlock();
        }
    }
    public static void main(String[] args)
    {
        new ExecutorMisc().plainLoopCount();
    }
}
