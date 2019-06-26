package enums;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TLightTest {
    private TLight tlight;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void next() {
        tlight = TLight.GREEN;
        assertEquals("TLight next of Green is YELLOW", tlight.next(), TLight.AMBER);
        tlight = TLight.RED;
        assertEquals("TLight next of RED is GREEN", tlight.next(), TLight.GREEN);
        tlight = TLight.AMBER;
        assertEquals("TLight next of AMBER is RED", tlight.next(), TLight.RED);

    }

    @Test
    public void getSeconds() {
        tlight = TLight.GREEN;
        assertEquals("TLight YELLOW stays 30 seconds", tlight.getSeconds(), 30);
        tlight = TLight.RED;
        assertEquals("TLight RED stays 30 seconds", tlight.getSeconds(), 30);
        tlight = TLight.AMBER;
        assertEquals("TLight AMBER stays 10 seconds", tlight.getSeconds(), 10);
    }

    @Test
    public void testToString() {
        assertEquals("TLight GREEN is color Green", TLight.GREEN.toString(), "GREEN");
    }
}