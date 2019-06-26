package misc;

import multithreading.ExecutorMisc;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class StopWatchOverhead {

    int count = 0;
    long totalTime = 0;

    void countWithWatch() {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.reset();
            stopWatch.start();
            plainCount();
        } finally {
            totalTime += stopWatch.getTime();
            stopWatch.stop();
        }
    }

    void plainCount() {
        try {
            ++count;
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void runOverheadTest() {
        Long startTime = System.currentTimeMillis();
        IntStream.range(0, 1000).forEach(i -> countWithWatch());

        System.out.println("StopWatch overhead test. Total time by Watch: " + totalTime);
        System.out.println("StopWatch overhead test. Total time spent: " + (System.currentTimeMillis() - startTime));
    }

    void runPlainTest() {
        Long startTime = System.currentTimeMillis();
        IntStream.range(0, 1000).forEach(i -> plainCount());

        System.out.println("Comparison test without StopWatch. Total time spent: " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args) {
        new StopWatchOverhead().runOverheadTest();
        new StopWatchOverhead().runPlainTest();
    }

}
