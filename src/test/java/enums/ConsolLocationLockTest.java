package enums;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConsolLocationLockTest
{
    @Test
    public void codes()
    {
        System.out.println(ConsolLocationLock.SyncStatus.getCodes());
        assertFalse(ConsolLocationLock.SyncStatus.isSynced(null));
        assertFalse(ConsolLocationLock.SyncStatus.isSynced("notsynced"));
        assertTrue(ConsolLocationLock.SyncStatus.isSynced("synced"));
        System.out.println(ConsolLocationLock.SyncStatus.values());
    }
}