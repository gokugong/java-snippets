package enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ConsolLocationLock
{
    enum SyncStatus
    {
        SYNCED("synced"),
        NOT_SYNCED("notSynced");

        private final String code;

        private SyncStatus(String code)
        {
            this.code = code;
        }

        public String getCode()
        {
            return this.code;
        }

        public static boolean isSynced(String status)
        {
            return SYNCED.getCode().equalsIgnoreCase(status);
        }

        public static final List<String> getCodes()
        {
            return Stream.of(values()).map(SyncStatus::getCode).collect(Collectors.toList());
        }
    }

    String CONSOL_LOCATION_NULL_VALUE = "XXXXXXXXXXXX";
    String PERM_LOCATION = "P";
}