//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package security;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.MessageFormat;
import java.util.stream.Collectors;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptDecryptTest implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(EncryptDecryptTest.class);

  public EncryptDecryptTest() {
  }

  public void run() {
    try {
      this.EncryptionTest4("Hello, World jjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkllllllllllllllllllllllllllllllllllllABCDEFGHIJKLMNOPQRSTHello, World jjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkllllllllllllllllllllllllllllllllllllABCDEFGHIJKLMNOPQRST");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void EncryptionTest4(String message) throws Exception {
    String clearText = "";
    String hexText = "";
    String original = "";
    if (message.length() > 0) {
      original = message;
    } else {
      original = "This is just an example.";
    }

    hexText = asHex(original.getBytes());
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(128);
    SecretKey sKey = keyGen.generateKey();
    byte[] rawSecretKey = sKey.getEncoded();
    new String(rawSecretKey);
    hexText = asHex(rawSecretKey);
    SecretKeySpec sKeySpec = new SecretKeySpec(rawSecretKey, "AES");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(1, sKeySpec);
    byte[] encrypted = cipher.doFinal(original.getBytes());
    new String(encrypted);
    hexText = asHex(encrypted);
    cipher.init(2, sKeySpec);
    byte[] decrypted = cipher.doFinal(encrypted);
    new String(decrypted);
    hexText = asHex(decrypted);
  }

  public static String asHex(byte[] buf) throws InterruptedException {
    StringBuffer strBuf = new StringBuffer(buf.length * 2);

    for (int i = 0; i < buf.length; ++i) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }

      if ((buf[i] & 255) < 16) {
        strBuf.append("0");
      }

      strBuf.append(Long.toString((long) (buf[i] & 255), 16));
    }

    return strBuf.toString();
  }

  public void EncryptionTest1(String clearText) {
    try {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
      keyGen.initialize(1024, new SecureRandom());
      KeyPair pair = keyGen.generateKeyPair();
      KeyFactory keyFactory = KeyFactory.getInstance("DSA");
      String encodedPublicKey = Base64.encode(pair.getPublic().getEncoded());
      String encodedPrivateKey = Base64.encode(pair.getPrivate().getEncoded());
      EncodedKeySpec encodedPrivateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(encodedPrivateKey));
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(1, pair.getPublic());
      byte[] encodedText = cipher.doFinal(clearText.getBytes());
      String toBase64 = Base64.encode(encodedText);
      Cipher deCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      deCipher.init(2, keyFactory.generatePrivate(encodedPrivateKeySpec));
      byte[] fromBase64 = Base64.decode(toBase64);
      deCipher.doFinal(fromBase64);
    } catch (Exception var15) {
      logger.error("Exception = {}", var15);
    }

  }

  public void EncryptionTest2(String publicKeyPath, String privateKeyPath) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    String encodedPubKey = Files.readAllLines(Paths.get(publicKeyPath)).stream().collect(Collectors.joining());
    byte[] pubKeyBytes = Base64.decode(encodedPubKey.trim());
    String encodedPriKey = Files.readAllLines(Paths.get(privateKeyPath)).stream().collect(Collectors.joining());
    byte[] priKeyBytes = Base64.decode(encodedPriKey.trim());
    EncodedKeySpec encodedPubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
    EncodedKeySpec encodedPriKeySpec = new PKCS8EncodedKeySpec(priKeyBytes);
    PrivateKey privateKey = keyFactory.generatePrivate(encodedPriKeySpec);
    PublicKey publicKey = keyFactory.generatePublic(encodedPubKeySpec);
    Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
    String startTest = "Hello, World";
    cipher.init(1, publicKey);
    byte[] encodedText = cipher.doFinal(startTest.getBytes());
    String base64 = Base64.encode(encodedText);
    Cipher deCipher = Cipher.getInstance(privateKey.getAlgorithm());
    deCipher.init(2, privateKey);
    byte[] from64 = Base64.decode(base64);
    deCipher.doFinal(from64);
  }

  public static String encodeAuth(String username, String password) {
    String up = MessageFormat.format("{0}:{1}", username, password);
    String enc = Base64.encode(up.getBytes());
    String auth = "Basic " + enc;
    return auth;
  }

  public static void main(String[] args) {
    //EncryptDecryptTest encryptDecryptTest = new EncryptDecryptTest();
    //encryptDecryptTest.run();

    logger.debug("Authorization: {}", encodeAuth("johndoeIVR1", "password1"));
    logger.debug("Authorization: {}", encodeAuth("developer", "]1)o-xRuJ'w_ifVG"));
  }
}
