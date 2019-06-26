package functional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.stream.Stream;

public class FunctionalStream
{
    private static final Logger logger = LoggerFactory.getLogger(FunctionalStream.class);

    public static void main(String[] args) {
        Stream<UUID> randomUUIDs = Stream.generate(() -> UUID.randomUUID());
        randomUUIDs.limit(10).map(UUID::toString).forEach(s -> logger.debug("next UUID: {}", s));
    }
}
