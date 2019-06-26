package encode;

import feign.RequestTemplate;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EncoderPeTest
{
    private static final String jsonData = "{\n" +
            "\"itemNames\" :[\"ACRZITEM1\",\"ACRZITEM2\",\"ACRZITEM3\"],\n" +
            "\"companies\" :[\"76\"],\n" +
            "\"facilityId\":[\"DC1\"],\n" +
            "\"page\":0,\n" +
            "\"size\":10\n" +
            "}";

    private static Map<String, String[]> arrayMap = new LinkedHashMap<>();
    static
    {
        arrayMap.put("itemNames", new String[] {"ACRZITEM1", "ACRZITEM2", "ACRZITEM3"});
        arrayMap.put("companies", new String[] {"76"});
        arrayMap.put("facilityId", new String[] {"DC1"});
    }

    @Test
    public void convertToJson()
    {
        String json = EncoderPe.convertToJson(arrayMap);
        System.out.println("Json=" + json);
    }

    @Test
    public void testEncode()
    {
        EncoderPe encoderPe = new EncoderPe();
        RequestTemplate rt1 = encoderPe.jacksonEncode(arrayMap);
        RequestTemplate rt2 = encoderPe.jacksonEncode(arrayMap);
        System.out.println("r1=" + rt1.body());
        System.out.println("r2=" + rt2.body());
//        assertEquals(rt1.body(), rt2.body());
        String json = EncoderPe.convertToJson(arrayMap);
        RequestTemplate rt3 = encoderPe.jacksonEncode(json);
        RequestTemplate rt4 = encoderPe.jacksonEncode(json);
//        assertEquals(rt3.body(), rt4.body());
        System.out.println("r3=" + rt3.body());
        System.out.println("r4=" + rt4.body());
        RequestTemplate rt5 = encoderPe.springEncode((Object)json);
        RequestTemplate rt6 = encoderPe.jacksonEncode((Object)json);
        System.out.println("r5=" + rt5.body());
        System.out.println("r6=" + rt6.body());
        RequestTemplate rt7 = encoderPe.springEncode(jsonData);
        RequestTemplate rt8 = encoderPe.jacksonEncode(jsonData);
        System.out.println("r5=" + rt7.body());
        System.out.println("r6=" + rt8.body());
    }
}