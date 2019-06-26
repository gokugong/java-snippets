package misc.stereambug;

import java.util.stream.LongStream;

public class BugReport {

    static final int NUM_DIMENSIONS = 3;
    static final int TOP_DIGIT = 3;

    public static void main(String[] args) {
        LongStream permutations = LongStream.of(0L);
        for (int dimension = 0; dimension < NUM_DIMENSIONS; dimension++) {
            permutations = permutations.flatMap(tens -> LongStream
                    .rangeClosed(1, TOP_DIGIT).map(ones-> 10*tens + ones)
                    .peek(v->System.out.println("+ "+v)));
        }
        long first = permutations.findFirst().getAsLong();
        System.out.println("First is " + first);
    }

}
