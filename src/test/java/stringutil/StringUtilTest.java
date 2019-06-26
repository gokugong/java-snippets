package stringutil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generateHelperTableName() {
        String tableName = "This_exactly_length_of_30_char";
        String generatedTableName = StringUtil.generateHelperTableName(tableName, "_H");
        assertEquals("successful","This_exactly_length_of_30_c_H", generatedTableName);
        tableName = "This_exactly_leng_of_28_char";
        generatedTableName = StringUtil.generateHelperTableName(tableName, "_H");
        assertEquals("successful","This_exactly_leng_of_28_char_H", generatedTableName);
        tableName = "This_exactly_leng_of_29_chars";
        generatedTableName = StringUtil.generateHelperTableName(tableName, "_H");
        assertEquals("successful","This_exactly_leng_of_29_cha_H", generatedTableName);
        String s = "AsnDTO.ASNId";
        String[] ss =s.split(Pattern.quote("."));
        assertEquals("AsnDTO", ss[0]);
        assertEquals("ASNId", ss[1]);
    }
}