package com.github.clov614.cloverapisdk;

import com.github.clov614.cloverapisdk.client.CloverApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author clov614
 * {@code @date} 2024/1/18 13:38
 */
// 通过注解标记位配置类
@Configuration
// 能够读取的application.yml中的配置
@ConfigurationProperties("cloverapi.client")
@Data
// 自动扫描注册组件，使得 Spring 能够自动注册相应的 Bean
@ComponentScan
public class CloverApiClientConfig {
    /**
     * Spring会自动注册并生成这个CloverApiClient对象
     */
    private String serverUrl = "http://localhost:8123";

    private String accessKey;

    private String secretKey;

    @Bean
    public CloverApiClient cloverApiClient() {
        // 使用读取到的 accessKey secretKey 创建一个客户端实例
        return new CloverApiClient(accessKey, secretKey, serverUrl);
    }
}
