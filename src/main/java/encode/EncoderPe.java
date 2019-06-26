package encode;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import javafx.util.Pair;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static feign.Util.UTF_8;

public class EncoderPe
{
    private static final Logger logger = LoggerFactory.getLogger(EncoderPe.class);

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric(int count)
    {
        StringBuilder builder = new StringBuilder();
        while (count-- > 0)
        {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    //private static ObjectMapper objectMapper = new ObjectMapper();
    static ObjectMapper objectMapper = JacksonObjectMapperConfig.objectMapperBuilder().build();

    static Map<String, Map<String, String>> generateData()
    {
        Map<String, Map<String, String>> rv = new HashMap<>();
        Map<String, String> map = Stream.of(randomAlphaNumeric(5)).collect(Collectors.toMap(s -> "a-string", s -> s));
        String json = convertToJson(map);

        rv.put(json, map);
        return rv;
    }

    static String convertToJson(Map<?, ?> map)
    {
        String json = null;
        try
        {
            json = objectMapper.writeValueAsString(map);
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
        return json;
    }

    public RequestTemplate jacksonEncode(Map<?, ?> map)
    {
        return mapEncode(getJacksonEncoder(), map);
    }

    public RequestTemplate springEncode(Map<?, ?> map)
    {
        return mapEncode(getSpringEncoder(), map);
    }

    public RequestTemplate jacksonEncode(String s)
    {
        return stringEncode(getJacksonEncoder(), s);
    }

    public RequestTemplate springEncode(String s)
    {
        return stringEncode(getJacksonEncoder(), s);
    }

    public RequestTemplate mapEncode(Encoder encoder, Map<?, ?> map)
    {
        RequestTemplate rt = new RequestTemplate();
        encoder.encode(map, map.getClass(), rt);
        return rt;
    }

    public RequestTemplate stringEncode(Encoder encoder, String s)
    {
        RequestTemplate rt = new RequestTemplate();
        encoder.encode(s, s.getClass(), rt);
        return rt;
    }

    public RequestTemplate encode(Encoder encoder, Object o)
    {
        RequestTemplate rt = new RequestTemplate();
        encoder.encode(o, o.getClass(), rt);
        return rt;
    }

    public RequestTemplate jacksonEncode(Object o)
    {
        return encode(getJacksonEncoder(), o);
    }

    public RequestTemplate springEncode(Object o)
    {
        return encode(getJacksonEncoder(), o);
    }

    static RequestTemplate runEncode(Encoder encoder)
    {
        Map.Entry<String, Map<String, String>> data = generateData().entrySet().stream().findAny().get();
        String json = data.getKey();
        Map<String, String> map = data.getValue();
        Decoder decoder = new JacksonDecoder();
        RequestTemplate template = new RequestTemplate();
/*
        encoder.encode(map, new TypeReference<Map<String, ?>>()
        {
        }.getType(), template);
*/
        encoder.encode(map, map.getClass(), template);
        System.out.println("encoded=" + template.body());
        Response response = Response.create(200, "OK", Collections.<String, Collection<String>>emptyMap(), json, UTF_8);
        Object decoded = null;
        try
        {
//            decoded = decoder.decode(response, Map.class);
            decoded = decoder.decode(response, map.getClass());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("decoded=" + decoded);
        assert (map.get("a-string").equals(((Map<String, String>) decoded).get("a-string")));
        return template;
    }

    static RequestTemplate runJacksonEncode()
    {
        return runEncode(getJacksonEncoder());
    }

    static RequestTemplate runSpringEncode()
    {
        return runEncode(getSpringEncoder());
    }

    static Encoder getJacksonEncoder()
    {
        return new JacksonEncoder();
    }

    static Encoder getSpringEncoder()
    {
/*
        return new SpringEncoder(new ObjectFactory<HttpMessageConverters>()
        {
            @Override
            public HttpMessageConverters getObject() throws BeansException
            {
                return new HttpMessageConverters();
            }
        });
*/
        return getJacksonEncoder();
    }

    public static void main(String[] args) throws JsonProcessingException
    {
        StopWatch watch = StopWatch.createStarted();
        List<RequestTemplate> rt1 = new ArrayList<>();
        IntStream.range(1, 2).forEach(i -> rt1.add(runJacksonEncode()));
        System.out.println("Jackson time=" + watch.getTime());
        watch.stop();
        watch.reset();
        watch.start();
        List<RequestTemplate> rt2 = new ArrayList<>();
        IntStream.range(1, 2).forEach(i -> rt2.add(runSpringEncode()));
        assert (rt1.containsAll(rt2));
        System.out.println("Spring time=" + watch.getTime());


        List<Pair<String, String>> pairs =
        IntStream.range(0, 5).boxed().map(i -> new Pair<>("pair" + i, "value" + i)).collect(Collectors.toList());
        logger.debug("pairs={}", objectMapper.writeValueAsString(pairs));

        List<Integer> aList = null;

/*
        for (int i : aList)     // NPE
            logger.debug("i={}", i);
*/

        String object = null;
        if (object instanceof String)
            logger.debug("null is string");

        logger.debug(Optional.ofNullable(object).orElse("A null object"));
        object = "A string";
        logger.debug(Optional.ofNullable(object).orElse("A null object"));

        List<Integer> integerList = Arrays.asList(10, 5, 3, 8, 1, 2);
        integerList.sort(Integer::compareTo);
        logger.debug("List: {}", integerList);

        BigDecimal a = new BigDecimal(9);
        BigDecimal b = new BigDecimal(4);
        String c = Double.toString(Math.ceil(a.divide(b).doubleValue()));
        logger.debug("9 / 4 = {}", c);

        EncoderPe pe = null;
        logger.debug("Pe = {}", Optional.ofNullable(pe).map(o -> o.toString()).orElseGet(() -> {
            return "empty";
        }));

    }
}
