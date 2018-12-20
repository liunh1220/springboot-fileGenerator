package com.example.filegenerator.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES加密解密
 * Created by liulanhua on 2018/6/11.
 */
public class DesUtil {

    private static final Logger logger = LoggerFactory.getLogger(DesUtil.class);

    /**
     * 定义 加密算法,可用 DES,DESede,Blowfish
     */
    private static final String ALGORITHM_DESEDS = "DESede";

    /**
     * 转换成十六进制字符串
     */
    private static byte[] hex(String key) {
        byte[] bkeys = DigestUtils.md5Hex(key).getBytes();
        byte[] enk = new byte[24];
        for (int i = 0; i < 24; i++) {
            enk[i] = bkeys[i];
        }
        return enk;
    }

    /**
     * 3DES加密
     *
     * @param key    密钥，24位
     * @param srcStr 将加密的字符串
     * @return 加密后的字符串
     */
    public static String encode3Des(String key, String srcStr) {
        byte[] keybyte = hex(key);
        try {
            byte[] src = srcStr.getBytes("utf-8");
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM_DESEDS);
            //加密
            Cipher c1 = Cipher.getInstance(ALGORITHM_DESEDS);
            c1.init(Cipher.ENCRYPT_MODE, deskey);

            //return c1.doFinal(src);//在单一方面的加密或解密
            return Base64.encodeBase64String(c1.doFinal(src));
        } catch (java.lang.Exception e) {
            logger.error("3DES加密异常", e);
        }
        return null;
    }

    /**
     * 3DES解密
     *
     * @param key    加密密钥，长度为24字节
     * @param desStr 加密后的字符串
     * @return 解密后的字符串
     */
    public static String decode3Des(String key, String desStr) {
        Base64 base64 = new Base64();
        byte[] keybyte = hex(key);
        byte[] src = base64.decode(desStr);
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM_DESEDS);
            //解密
            Cipher c1 = Cipher.getInstance(ALGORITHM_DESEDS);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            //return c1.doFinal(src);
            return new String(c1.doFinal(src));
        } catch (java.lang.Exception e) {
            logger.error("3DES解密异常", e);
        }
        return null;
    }


    public static void main(String[] args) {
        String content = "<html><body><h2>停服站点测试页面</h2></body></html>";
        // 密码长度必须是8的倍数
        String password = "tdwACw!#3321!CG";

        String contentBytes = encode3Des(password, content);
        System.out.println(contentBytes);

        String ecode3Des = decode3Des(password, contentBytes);
        System.out.println(ecode3Des);
    }


}
