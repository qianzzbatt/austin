package com.java3y.austin.script;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.java3y.austin.domain.SmsRecord;
import com.java3y.austin.enums.SmsStatus;
import com.java3y.austin.pojo.SmsParam;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 3y
 * @date 2021/11/6
 * 1. 发送短信接入文档：https://cloud.tencent.com/document/api/382/55981
 * 2. 推荐直接使用SDK调用
 * 3. 推荐使用API Explorer 生成代码
 */
@Service
@Slf4j
public class TencentSmsScript implements SmsScript{
    private static final Integer PHONE_NUM = 11;

    @Resource
    private SmsClient smsClient;

    @Resource
    private SendSmsRequest smsRequest;

    @Override
    public List<SmsRecord> send(SmsParam smsParam) {
        try {
            assembleReq(smsParam);
            SendSmsResponse response = smsClient.SendSms(smsRequest);
            return assembleSmsRecord(smsParam,response);

        } catch (TencentCloudSDKException e) {
            log.error("send tencent sms fail!{},params:{}",
                    Throwables.getStackTraceAsString(e), JSON.toJSONString(smsParam));
            return null;
        }

    }


    /**
     * 组装发送短信参数
     */
    private void assembleReq(SmsParam smsParam) {
        String[] phoneNumberSet1 = smsParam.getPhones().toArray(new String[smsParam.getPhones().size() - 1]);
        smsRequest.setPhoneNumberSet(phoneNumberSet1);
        String[] templateParamSet1 = {smsParam.getContent()};
        smsRequest.setTemplateParamSet(templateParamSet1);
    }

    /**
     * 封装返回结果
     * @param smsParam
     * @param response
     * @return
     */
    private List<SmsRecord> assembleSmsRecord(SmsParam smsParam, SendSmsResponse response) {
        if (response == null || ArrayUtil.isEmpty(response.getSendStatusSet())) {
            return null;
        }

        List<SmsRecord> smsRecordList = new ArrayList<>();

        for (SendStatus sendStatus : response.getSendStatusSet()) {
            //返回的手机号是+86格式的，所以要切割一下
            String phone = new StringBuilder(new StringBuilder(sendStatus.getPhoneNumber())
                    .reverse().substring(0, PHONE_NUM)).reverse().toString();

            SmsRecord smsRecord = SmsRecord.builder()
                    .sendDate(Integer.valueOf(DateUtil.format(new Date(), "yyyyMMdd")))
                    .messageTemplateId(smsParam.getMessageTemplateId())
                    .phone(Long.valueOf(phone))
                    .supplierId(smsParam.getSupplierId())
                    .supplierName(smsParam.getSupplierName())
                    .seriesId(sendStatus.getSerialNo())
                    .chargingNum(Math.toIntExact(sendStatus.getFee()))
                    .status(SmsStatus.SEND_SUCCESS.getCode())
                    .reportContent(sendStatus.getCode())
                    .created(Math.toIntExact(DateUtil.currentSeconds()))
                    .updated(Math.toIntExact(DateUtil.currentSeconds()))
                    .build();

            smsRecordList.add(smsRecord);
        }
        return smsRecordList;
    }

}
