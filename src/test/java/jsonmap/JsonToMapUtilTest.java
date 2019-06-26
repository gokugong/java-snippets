package jsonmap;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class JsonToMapUtilTest
{
    @Test
    public void jsonToMap()
    {
        String jsonData = "{\n" +
                "\"itemNames\" :[\"ACRZITEM1\",\"ACRZITEM2\",\"ACRZITEM3\"],\n" +
                "\"companies\" :[\"76\"],\n" +
                "\"facilityId\":[\"DC1\"],\n" +
                "\"page\":0,\n" +
                "\"size\":10\n" +
                "}";
        Map<?, ?> map = JsonToMapUtil.jsonToMap(jsonData);
        System.out.println("Converted map=" + map);
    }
}