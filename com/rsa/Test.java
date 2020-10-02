package rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;

/**
 * @ClassName Test
 * @Description TODO
 * @Author boy
 * @Date 2019/8/5 6:00 PM
 */
public class Test {

    public static String PUBLCIKEY = "/Users/suboya/Downloads/publickey";
    public static String PRIVATEKEY = "/Users/suboya/Downloads/publickey";
    public static String TAG = "RSA";
    public static int MAX_ENCRYPT_BLOCK = 1024;
    public static int MAX_DECRYPT_BLOCK = 1024;

    /**
     * 生成公私密钥对
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static void generateKeys() throws NoSuchAlgorithmException, IOException {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom secureRandom = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        keyPairGenerator.initialize(1024, secureRandom);
        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        /** 得到公钥 */
        java.security.Key publicKey = keyPair.getPublic();
        System.out.println("publicKey: " + publicKey.toString());
        /** 得到私钥 */
        java.security.Key privateKey = keyPair.getPrivate();
        System.out.println("privateKey " + privateKey.toString());

//对象流形式写入公钥
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("/Users/suboya/Downloads/publickey"));
        outputStream.writeObject(publicKey);
        outputStream.flush();
        outputStream.close();
        //对象流形式写入私钥
        ObjectOutputStream outputStream1 = new ObjectOutputStream(new FileOutputStream("/Users/suboya/Downloads/publickey"));
        outputStream1.writeObject(privateKey);
        outputStream1.flush();
        outputStream1.close();


    }

    /**
     * 公钥加密
     *
     * @param str 待加密数据
     * @return 加密后的数据
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(String str) throws ClassNotFoundException, IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PUBLCIKEY));
        java.security.Key publicKey2 = (java.security.Key) objectInputStream.readObject();
        objectInputStream.close();
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(TAG);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey2);


        byte[] encryptedData = str.getBytes();
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密  doFinal
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedDatas = out.toByteArray();
        out.close();

        return encryptedDatas;
    }

    /**
     * 私钥解密
     *
     * @param encryString 公钥加密后的数据
     * @return 解密后数据
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] decrypt(byte[] encryString) throws FileNotFoundException, IOException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PRIVATEKEY));
        java.security.Key privatekey = (java.security.Key) objectInputStream.readObject();
        objectInputStream.close();

        Cipher cipher = Cipher.getInstance(TAG);
        cipher.init(Cipher.DECRYPT_MODE, privatekey);


        byte[] decryptedData = encryString;
        int inputLen = decryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(decryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(decryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedDatas = out.toByteArray();
        out.close();

        return decryptedDatas;
    }

    /**
     * 获取私钥加密后的数据
     *
     * @param data 待加密数据
     * @return 私钥加密后的数据
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encryptByPrivateKey(String data) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PRIVATEKEY));
        java.security.Key privatekey = (java.security.Key) objectInputStream.readObject();
        objectInputStream.close();
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(TAG);
        cipher.init(Cipher.ENCRYPT_MODE, privatekey);


        byte[] encryptedData = data.getBytes();
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密  doFinal
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedDatas = out.toByteArray();
        out.close();

        return encryptedDatas;
    }

    /**
     * 用公钥解密数据
     *
     * @param
     * @return 公钥解密后的数据
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeyException
     * @throws ClassNotFoundException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PUBLCIKEY));
        java.security.Key publicKey2 = (java.security.Key) objectInputStream.readObject();
        objectInputStream.close();

        Cipher cipher = Cipher.getInstance(TAG);
        cipher.init(Cipher.DECRYPT_MODE, publicKey2);

        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密  doFinal
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedDatas = out.toByteArray();
        out.close();

        return decryptedDatas;

    }

    public static void main(String[] args) {
        try {
//            generateKeys();
//            System.out.println(encrypt("你好！"));

            byte[] b = encryptByPrivateKey("你好");
            byte[] c = decryptByPublicKey(b);
            String s = new String(c);
            System.out.println(s);
        } catch (Exception e) {

        }

    }
}
