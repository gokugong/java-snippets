package jsonmap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import encode.JacksonObjectMapperConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JsonToTree
{
    private final static Logger logger = LoggerFactory.getLogger(JsonToTree.class);
    private final static ObjectMapper objectMapper = JacksonObjectMapperConfig.objectMapperBuilder().build();

    public static JsonNode readTree(String fileName) throws IOException
    {
        InputStream is = new FileInputStream(new File(fileName));
        JsonNode jsonNode = objectMapper.readTree(is);
        return jsonNode;
    }

    public static void main(String[] args) throws IOException
    {
        String testFile = "src/main/resources/jsonmap/receivingParameters_1.json";
        JsonNode jsonNode = readTree(testFile);
        logger.debug("read json= {}", jsonNode);
        JsonNode relatedProfiles = jsonNode.get("RelatedProfiles");
        if (relatedProfiles != null && relatedProfiles.isArray()) {
            relatedProfiles.forEach(p -> {
                JsonNode profilePurpose = p.get("profilePurpose");
                JsonNode profileID = p.get("profileId");
                logger.debug("profilePurpose: {}, profileId: {}", profilePurpose, profileID);
            });
        }

        String toReplaceByNull = "to replace $replace by NULL";
        String afterReplace = toReplaceByNull.replaceAll("\\$replace", "null");
        logger.debug("after replace: {}", afterReplace);
    }

}
