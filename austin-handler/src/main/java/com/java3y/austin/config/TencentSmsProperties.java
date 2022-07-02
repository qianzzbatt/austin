package com.java3y.austin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangquan
 * @date 2022/7/1
 **/
@Configuration
@ConfigurationProperties(prefix = "tencent.sms.account")
@Data
public class TencentSmsProperties {


    private String secretId;

    private String secretKey;

    private String smsSdkAppId;

    private String signName;

    private String templateId;

}
