package jsonmap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonToMapUtil
{
    public static Map<?, ?> jsonToMap(String json)
    {
        Map<?, ?> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            map = mapper.readValue(json, new TypeReference<Map<?, ?>>()
            {
            });
        } catch (JsonGenerationException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args)
    {
        String json = "{\"id\":1,\"name\":\"Lokesh Gupta\",\"age\":34,\"location\":\"India\"}";
        Map<?, ?> map = jsonToMap(json);
        System.out.println("Converted map=" + map);
    }
}
