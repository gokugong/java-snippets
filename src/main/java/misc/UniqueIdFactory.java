package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.dgc.VMID;
import java.util.concurrent.ThreadLocalRandom;

public class UniqueIdFactory
{
    private static Logger logger = LoggerFactory.getLogger(UniqueIdFactory.class);

    public static void main(String[] args) {
        VMID vmid = new VMID();
        logger.debug("VMID={}", vmid);
        logger.debug("System.time(nano)={}", System.nanoTime());
        logger.debug("System.time={}", System.currentTimeMillis());
    }
}
