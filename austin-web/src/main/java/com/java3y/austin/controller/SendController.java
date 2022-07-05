package com.java3y.austin.controller;

import com.java3y.austin.handler.SmsHandler;
import com.java3y.austin.pojo.TaskInfo;
import com.java3y.austin.vo.BasicResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@RestController
public class SendController {


    @Autowired
    private SmsHandler smsHandler;

    /**
     * 测试发送短信
     * @param phone 手机号
     * @return
     */
    @GetMapping("/sendSms")
    public BasicResultVO<Void> sendSms(String phone, String content, Long messageTemplateId ) {

        TaskInfo taskInfo = TaskInfo.builder().receiver(new HashSet<>(Arrays.asList(phone.split(","))))
                .content(content).messageTemplateId(messageTemplateId).build();
        if (smsHandler.doHandler(taskInfo)) {
            return BasicResultVO.success("发送信息成功");
        }

        return BasicResultVO.fail();
    }

}
