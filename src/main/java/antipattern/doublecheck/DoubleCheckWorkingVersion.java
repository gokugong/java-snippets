package antipattern.doublecheck;

/**
 * @see <a href="http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html">The "Double-Checked Locking is Broken" Declaration</a>
 * <p>
 * JDK5 and later extends the semantics for volatile so that the system will not allow a write of a volatile
 * to be reordered with respect to any previous read or write, and a read of a volatile cannot be reordered
 * with respect to any following read or write. See this entry in Jeremy Manson's blog for more details.
 */
public class DoubleCheckWorkingVersion
{
    // Works with acquire/release semantics for volatile
    private volatile Helper helper = null;

    public Helper getHelper()
    {
        if (helper == null)
        {
            synchronized (this)
            {
                if (helper == null)
                    helper = new Helper();
            }
        }
        return helper;
    }
}

class Helper {
    String version = "1.0";
}
