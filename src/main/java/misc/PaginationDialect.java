package misc;

public class PaginationDialect
{
    String oraclePagination = "SELECT * FROM (\n" +
            "    SELECT\n" +
            "      ord.*,\n" +
            "      row_number() over (ORDER BY ord.order_id ASC) line_number\n" +
            "    FROM Orders ord\n" +
            "  ) WHERE line_number BETWEEN 0 AND 5  ORDER BY line_number;";

    String mysqlPagination = "SELECT date, id \n" +
            "FROM events \n" +
            "ORDER BY date, id \n" +
            "LIMIT 1 OFFSET 39999;";

    String mysqlSeek = "SELECT    *\n" +
            "FROM\n" +
            "    events\n" +
            "WHERE\n" +
            "    (date,id) > ('2010-07-12T10:29:47-07:00',111866)\n" +
            "        AND event = 'editstart'\n" +
            "ORDER BY date, id\n" +
            "LIMIT 10";
}
