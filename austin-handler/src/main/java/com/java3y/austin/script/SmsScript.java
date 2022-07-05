package com.java3y.austin.script;

import com.java3y.austin.domain.SmsRecord;
import com.java3y.austin.pojo.SmsParam;

import java.util.List;

/**
 * 发送短信运营商选择
 */
public interface SmsScript {


    /**
     * @param smsParam 发送短信参数
     * @return 渠道商接口返回值
     */
    List<SmsRecord> send(SmsParam smsParam);

}
