package security;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration
{
  private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

  private static final String[] keyPaths = {
      "./config/keys/Key1-1enc.key",
      "./config/keys/Key1-2enc.key",
      "./config/keys/Key2-1.key",
      "./config/keys/Key2-2.key"
  };

  private static final byte[][] keys = new byte[4][];

  static {
    for (int i = 0; i < keyPaths.length; i++) {
      try {
        keys[i] = Files.readAllBytes(Paths.get(keyPaths[i]));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static byte[] getKey(int index) throws IOException {
    return keys[index];
  }

  public static void main(String[] args) {
    logger.debug("all keys = ");
    IntStream.range(0, 4).forEach(i -> logger.debug("key {} = {}", i, Base64.encode(keys[i])));
  }
}
