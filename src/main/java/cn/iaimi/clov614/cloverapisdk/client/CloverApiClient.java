package cn.iaimi.clov614.cloverapisdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.iaimi.clov614.cloverapisdk.model.User;
import cn.iaimi.clov614.cloverapisdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 *
 * @author clov614
 * {@code @date} 2024/1/16 19:14
 */
public class CloverApiClient {

    private String accessKey;
    private String secretKey;

    private String serverUrl;

    public CloverApiClient(String accessKey, String secretKey, String serverUrl) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.serverUrl = serverUrl;
    }

    // 构造请求头
    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // secret 一定不能发送
//        hashMap.put("secretKey", secretKey);
        // 4位 随机数 字符串
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        // currentTimeMillis() 返回当前毫秒数 除 1000 得到时间戳 的 秒
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.genSign(body, secretKey));

        return hashMap;
    }

    public String getNameByGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get( serverUrl + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result = HttpUtil.post(serverUrl + "/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post( serverUrl + "/api/name/user")
                // 添加TOKEN请求头
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }
}
