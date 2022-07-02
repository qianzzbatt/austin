package com.java3y.austin.config;

import cn.hutool.core.util.IdUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author huangquan
 * @date 2022/7/1
 **/
@Configuration
public class TencentSmsConfig {
    /**
     * 服务器地区
     */
    private static final String REGION = "ap-guangzhou";

    @Resource
    private TencentSmsProperties smsProperty;

    /**
     * 自定义配置SmsClient
     * @return SmsClient
     */
    @Bean
    public SmsClient smsClient() {
        Credential cred = new Credential(smsProperty.getSecretId(), smsProperty.getSecretKey());
        return new SmsClient(cred, REGION);
    }

    /**
     * 自定配置SendSmsRequest
     */
    @Bean
    public SendSmsRequest smsRequest() {
        SendSmsRequest smsRequest = new SendSmsRequest();
        smsRequest.setSignName(smsProperty.getSignName());
        smsRequest.setSmsSdkAppId(smsProperty.getSmsSdkAppId());
        smsRequest.setTemplateId(smsProperty.getTemplateId());
        smsRequest.setSessionContext(IdUtil.fastSimpleUUID());
        return smsRequest;
    }

}
