package com.ibeetl.utils;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;


/**
 * RSA
 * 非对称加密算法RSA算法组件
 */
public class RSACoder {

    /**
     * 非对称密钥算法
     */
    private static final String KEY_ALGORITHM = "RSA";


    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";


    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密数据
     */
    public static String encryptByPrivateKey(String data, String key) throws Exception {
        return Base64.encodeBase64String(encryptByPrivateKey(data.getBytes(), Base64.decodeBase64(key)));
    }


    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {

        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] enBytes = null;
        for (int i = 0; i < data.length; i += 64) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * 公钥加密
     *
     * @param data 数据
     * @param key  公钥
     * @return 加密值
     */
    public static String encryptByPublicKey(String data, String key) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(data.getBytes(), Base64.decodeBase64(key)));
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] enBytes = null;
        for (int i = 0; i < data.length; i += 64) {
            // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }


    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return String 解密数据
     */
    public static String decryptByPrivateKey(String data, String key) throws Exception {
        return new String(decryptByPrivateKey(Base64.decodeBase64(data), Base64.decodeBase64(key)));
    }


    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] enBytes = null;
        // 解密时超过128字节就报错。为此采用分段解密的办法来解密
        for (int i = 0; i < data.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }


    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return String 解密数据
     */
    public static String decryptByPublicKey(String data, String key) throws Exception {
        return new String(decryptByPublicKey(Base64.decodeBase64(data), Base64.decodeBase64(key)));
    }


    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {

        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        byte[] enBytes = null;
        // 解密时超过128字节就报错。为此采用分段解密的办法来解密
        for (int i = 0; i < data.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }


    /**
     * 生成秘钥对
     */
    public static void createKeyGroup() throws Exception {
        //初始化密钥
        //生成密钥对
        Map<String, Object> keyMap = RSACoder.initKey();
        //公钥
        byte[] publicKey = RSACoder.getPublicKey(keyMap);

        //私钥
        byte[] privateKey = RSACoder.getPrivateKey(keyMap);
        System.out.println("公钥：\n" + Base64.encodeBase64String(publicKey));
        System.out.println("私钥：\n" + Base64.encodeBase64String(privateKey));
    }

    /**
     * 初始化密钥对
     *
     * @return Map 甲方密钥的Map
     */
    private static Map<String, Object> initKey() throws Exception {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //将密钥存储在map中
        Map<String, Object> keyMap = Maps.newHashMap();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;

    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥map
     * @return byte[] 私钥
     */
    private static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥map
     * @return byte[] 公钥
     */
    private static byte[] getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }


}