package com.wa.last;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.security.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author echidna
 * @date 2019/9/9 16:13
 */
@Slf4j
public class EncryTest extends BaseServiceTest {

    public final static String KEY_ALGORITHM = "RSA";

    @Test
    public void test() throws NoSuchAlgorithmException {

        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 初始化密钥对生成器，密钥大小为1024位
        keyPairGen.initialize(1024);
        // 生成一个密钥对
        KeyPair keyPair = keyPairGen.genKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        PublicKey publicKey = keyPair.getPublic();

        System.out.println(1);


        String regex = "yes|no";
        String content = "can";
        StringBuffer sb = new StringBuffer();

        System.out.println("content: " + content);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        System.out.println(m.matches());


    }
}
