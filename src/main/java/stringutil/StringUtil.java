package stringutil;

public class StringUtil {
    public static String generateHelperTableName(String tableName, String suffix)
    {
        String result = null;
        // We splice together as much of the table name as we can, adding the given suffix, still staying
        // at 30 chars or less for the sake of Oracle.
        tableName = (tableName.length() > (30 - suffix.length())) ?
                tableName.substring(0, (29 - suffix.length())) : tableName;
        result = tableName + suffix;
        return result;
    }
}
