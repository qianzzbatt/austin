package com.java3y.austin.script;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.java3y.austin.pojo.SmsParam;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 3y
 * @date 2021/11/6
 * 1. 发送短信接入文档：https://cloud.tencent.com/document/api/382/55981
 * 2. 推荐直接使用SDK调用
 * 3. 推荐使用API Explorer 生成代码
 */
@Service
@Slf4j
public class TencentSmsScript {

    @Resource
    private SmsClient smsClient;

    @Resource
    private SendSmsRequest smsRequest;

    public String send(SmsParam smsParam) {
        try {

            /**
             * 组装发送短信参数
             */
            String[] phoneNumberSet = smsParam.getPhones().toArray(new String[smsParam.getPhones().size() - 1]);
            smsRequest.setPhoneNumberSet(phoneNumberSet);
            String[] templateParamSet = {smsParam.getContent()};
            smsRequest.setTemplateParamSet(templateParamSet);

            /**
             * 请求，返回结果
             */
            SendSmsResponse resp = smsClient.SendSms(smsRequest);
            return SendSmsResponse.toJsonString(resp);

        } catch (TencentCloudSDKException e) {
            log.error("send tencent sms fail!{},params:{}",
                    Throwables.getStackTraceAsString(e), JSON.toJSONString(smsParam));
            return null;
        }

    }
}
