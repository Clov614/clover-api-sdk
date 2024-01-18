package cn.iaimi.clov614.cloverapisdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.Map;

/**
 * 签名工具
 * @author clov614
 * {@code @date} 2024/1/18 11:14
 */
public class SignUtils {

    /**
     * 生成签名
     * @param hashMap 包含需要签名的参数的哈希映射
     * @param secretKey 密钥
     * @return 生成的签名字符串
     */
    public static String genSign(Map<String, String> hashMap, String secretKey) {
        // 使用 SHA256
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = hashMap.toString() + "." + secretKey;
        return md5.digestHex(content);
    }

    /**
     * 生成签名
     * @param body 请求体内容
     * @param secretKey 密钥
     * @return 生成的签名字符串
     */
    public static String genSign(String body, String secretKey) {
        // 使用 SHA256
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;
        return md5.digestHex(content);
    }
}
