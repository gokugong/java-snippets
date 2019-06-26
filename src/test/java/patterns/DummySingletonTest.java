package patterns;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DummySingletonTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void getInstanceBlocking()
    {
        DummySingleton.lock.set(true);
        DummySingleton blocking = DummySingleton.getInstance();
        System.out.println("Cannot reach here");
    }

    @Test
    public void getGreeting()
    {
    }
}