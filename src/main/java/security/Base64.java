package security;

public class Base64 {
  public static String encode(byte[] bytes) {
    return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
  }

  public static byte[] decode(String encoded) {
    return org.apache.commons.codec.binary.Base64.decodeBase64(encoded);
  }
}
