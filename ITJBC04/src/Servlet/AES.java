//package Servlet;
//
//
//import java.security.Key;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//
//import sun.misc.*;
//
//public class AES
//{
//private static String algorithm = "AES";
//private static byte[] keyValue=new byte[]
//{ 'A', 'S', 'e', 'c', 'u', 'r', 'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
//
//        // Performs Encryption
//        public static String encrypt99(String plainText) throws Exception
//        {
//                Key key = generateKey();
//                Cipher chiper = Cipher.getInstance(algorithm);
//                chiper.init(Cipher.ENCRYPT_MODE, key);
//                byte[] encVal = chiper.doFinal(plainText.getBytes());
//                String encryptedValue = new BASE64Encoder().encode(encVal);
//                return encryptedValue;
//        }
//
//        // Performs decryption
//        public static String decrypt(String encryptedText) throws Exception
//        {
//                // generate key
//                Key key = generateKey();
//                Cipher chiper = Cipher.getInstance(algorithm);
//                chiper.init(Cipher.DECRYPT_MODE, key);
//                byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
//                byte[] decValue = chiper.doFinal(decordedValue);
//                String decryptedValue = new String(decValue);
//                return decryptedValue;
//        }
//
////generateKey() is used to generate a secret key for AES algorithm
//        private static Key generateKey() throws Exception
//        {
//                Key key = new SecretKeySpec(keyValue, algorithm);
//                return key;
//        }
//
//
//}

package Servlet;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64; // IMPORTING THE MODERN LIBRARY

public class AES
{
    private static String algorithm = "AES";
    private static byte[] keyValue=new byte[]
            { 'A', 'S', 'e', 'c', 'u', 'r', 'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    // Performs Encryption
    public static String encrypt99(String plainText) throws Exception
    {
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = chiper.doFinal(plainText.getBytes());

        // UPDATED: Using java.util.Base64
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    // Performs decryption
    public static String decrypt(String encryptedText) throws Exception
    {
        // generate key
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);

        // UPDATED: Using java.util.Base64
        byte[] decordedValue = Base64.getDecoder().decode(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    //generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(keyValue, algorithm);
        return key;
    }
}