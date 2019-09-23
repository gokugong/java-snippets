package security;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encryption {
  private static final Logger logger = LoggerFactory.getLogger(Encryption.class);
  private static BlockingQueue<Cipher> ciphers = new LinkedBlockingQueue();
  private static ConcurrentMap<Integer, ConcurrentMap<SecretKeySpec, SecretKeySpec>> key1Specs = new ConcurrentHashMap();

  protected static Cipher getCipher()
      throws NoSuchAlgorithmException, NoSuchPaddingException {
    Cipher cipher = (Cipher) ciphers.poll();
    if (cipher == null) {
      cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }
    return cipher;
  }

  protected static SecretKeySpec getKey1Spec(SecretKeySpec key2Spec, Cipher cipher, Integer keyId)
      throws FileNotFoundException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    byte[] Key1enc = Configuration.getKey(keyId);
    cipher.init(2, key2Spec);
    byte[] Key1 = cipher.doFinal(Key1enc);
    SecretKeySpec key1Spec = new SecretKeySpec(Key1, "AES");
    return key1Spec;
  }

  public static String decrypt(SecretKeySpec key2Spec, String encCC, boolean b64encoded, Integer keyId)
      throws Exception {
    Cipher cipher = getCipher();
    try {
      SecretKeySpec key1Spec = getKey1Spec(key2Spec, cipher, keyId);

      byte[] bytes = Base64.decode(encCC);
      cipher.init(2, key1Spec);
      byte[] originalCCbytes = cipher.doFinal(bytes);
      String originalCC = "";
      if (b64encoded) {
        originalCC = Base64.encode(originalCCbytes);
      } else {
        originalCC = new String(originalCCbytes);
      }
      return originalCC;
    } finally {
      ciphers.offer(cipher);
    }
  }

  public static String encrypt(SecretKeySpec key2Spec, String CC, boolean b64encoded, Integer keyId)
      throws Exception {
    Cipher cipher = getCipher();
    try {
      SecretKeySpec key1Spec = getKey1Spec(key2Spec, cipher, keyId);

      cipher.init(1, key1Spec);
      byte[] encryptedCCbytes = null;
      if (b64encoded) {
        encryptedCCbytes = cipher.doFinal(Base64.decode(CC));
      } else {
        encryptedCCbytes = cipher.doFinal(CC.getBytes());
      }
      String encryptedCC = Base64.encode(encryptedCCbytes);

      return encryptedCC;
    } finally {
      ciphers.offer(cipher);
    }
  }

  public static void main(String[] args) throws Exception {
    String key = "ACCOUNT";
    byte[] bytes = Base64.decode(key);
    SecretKeySpec key2Spec = new SecretKeySpec(bytes, "AES");
    logger.debug("decoded = {}, SecretKeySpec = {}", bytes, key2Spec);
    String test = "Hello world!";
    String encrypted = encrypt(key2Spec, test, false, 0);
    String decrypted = decrypt(key2Spec, encrypted, false, 0);
    logger.debug("encrypted = {}", encrypted);
    logger.debug("decrypted = {}", decrypted);
    assert (test.equals(decrypted));
  }
}
