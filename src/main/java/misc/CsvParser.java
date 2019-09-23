package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class CsvParser {
    private static final Logger logger = LoggerFactory.getLogger(CsvParser.class);

    private static final String[] ACCOUNT_READ_HEADER = new String[]{
            "id", "classId",
            null, "persistableId", "persistableClassId", null, null,
            "encAccountNumber", "uiAccountNumber"};
    private static final CellProcessor[] ACCOUNT_READ_PROCESSORS = new CellProcessor[]{
            new NotNull(new ParseInt()), new NotNull(new ParseInt()),
            null, null, null, null, null,
            new Optional()};


    public static void main(String[] args) {
        logger.debug("lengths {}, {}", ACCOUNT_READ_HEADER.length, ACCOUNT_READ_PROCESSORS.length);
        System.out.println("lengths: " + ACCOUNT_READ_HEADER.length + ", " + ACCOUNT_READ_PROCESSORS.length);
    }
}
