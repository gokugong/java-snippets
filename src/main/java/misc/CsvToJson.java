package misc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import encode.JacksonObjectMapperConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvToJson
{
    static final Logger logger = LoggerFactory.getLogger(CsvToJson.class);

    static CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
    static CsvMapper csvMapper = new CsvMapper();
    static ObjectMapper objectMapper = JacksonObjectMapperConfig.objectMapperBuilder().build();

    public static String convertCsvToJson(String csv)
    {
        String rv = "";
        return rv;
    }

    public static String convertCsvToJson(InputStream csv)
    {
        JsonNode json;
        try
        {
            json = csvMapper.readerWithTypedSchemaFor(Map.class).readTree(csv);
            logger.debug("Readed csv={}", json);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertCsvToJson2(File csvFile) throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile)));
        List<String> list = reader.lines().collect(Collectors.toList());
        String[] header = list.get(0).split(",");
        List<Map<String, String>> jsonMap = list.stream().skip(1).map(line -> {
            String[] values = line.split(",");
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < values.length; i++)
            {
                map.put(header[i], values[i]);
            }
            return map;
        }).collect(Collectors.toList());
        logger.debug("read csv, convert to map: {}", jsonMap);
        String rv = objectMapper.writeValueAsString(jsonMap);
        logger.debug("covert to json: {}", rv);
        return rv;
    }

    public static void writeToFile(String fileName, String text) throws IOException
    {
        File file = new File(fileName);
        OutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(text.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
   }

    public static void main(String[] args) throws Exception
    {
        String inputFileName = "src/main/resources/misc/csv-to-json/input.csv";
        String outputFileName = "src/main/resources/misc/csv-to-json/output.json";

        File file = new File(inputFileName);
        //InputStream inputStream2 = new ClassPathResource("misc/csv-to-json/input.csv").getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        //BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2));

        logger.debug("load input file content: {}", reader.lines().collect(Collectors.toList()));
        //logger.debug("load2 input file content: {}", reader.lines().collect(Collectors.toList()));

        convertCsvToJson(new FileInputStream(file));
        String json = convertCsvToJson2(file);
        writeToFile(outputFileName, json);
    }
}
