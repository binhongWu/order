package com.ibeetl.utils.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AESUtil {

    private static String cipherAlgorithm = "AES/CBC/PKCS5Padding";
    private static String keyAlgorithm = "AES";
    private static String IV = "\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";
    /**
     * 生成指定长度的随机字符串
     *
     * @param length
     *            指定字符串长度
     * @return
     */
    public static String generateLenString(int length) {
        char[] cResult = new char[length];
        int[] flag = { 0, 0, 0 }; // A-Z, a-z, 0-9
        int i = 0;
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
            i = i % length;
            int f = (int) (Math.random() * 3 % 3);
            if (f == 0)
                cResult[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                cResult[i] = (char) ('a' + Math.random() * 26);
            else
                cResult[i] = (char) ('0' + Math.random() * 10);
            flag[f] = 1;
            i++;
        }
        return new String(cResult);
    }

    public static byte[] encryptAES(byte[] plainBytes, byte[] keyBytes, boolean useBase64Code, String charset) throws Exception {

        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
            IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

            byte[] encryptedBlock = cipher.doFinal(plainBytes);

            if (useBase64Code) {
                return Base64.encodeBase64String(encryptedBlock).getBytes(charset);
            } else {
                return encryptedBlock;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("AES加密失败");
        }
    }

    /**
     * AES解密
     *
     * @param cryptedBytes
     *            密文字节数组
     * @param keyBytes
     *            对称密钥字节数组
     * @param useBase64Code
     *            是否使用Base64编码
     * @param charset
     *            编码格式
     * @return byte[]
     */
    public static byte[] decryptAES(byte[] cryptedBytes, byte[] keyBytes, boolean useBase64Code, String charset) throws Exception {

        byte[] data = null;

        // 如果是Base64编码的话，则要Base64解码
        if (useBase64Code) {
            data = Base64.decodeBase64(new String(cryptedBytes, charset));
        } else {
            data = cryptedBytes;
        }

        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
            IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

            byte[] decryptedBlock = cipher.doFinal(data);

            return decryptedBlock;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("AES解密失败");
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException, Exception {
        String charset = "utf-8";
        String content = "hello";
        String aeskey = "X3EQXLQPZB79NJ84";
        byte[] encrypt = AESUtil.encryptAES(content.toString().getBytes(charset), aeskey .getBytes(charset), true, charset);
        String encryptData = new String(encrypt,charset);
        System.out.println("加密数据："+encryptData);
    }
}
