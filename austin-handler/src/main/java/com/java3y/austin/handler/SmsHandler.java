package com.java3y.austin.handler;

import cn.hutool.core.collection.CollUtil;
import com.java3y.austin.dao.SmsRecordDao;
import com.java3y.austin.domain.SmsRecord;
import com.java3y.austin.pojo.SmsParam;
import com.java3y.austin.pojo.TaskInfo;
import com.java3y.austin.script.SmsScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息发送处理器
 *
 * @author admin
 */
@Component
public class SmsHandler implements Handler {

    @Resource
    private SmsRecordDao smsRecordDao;

    @Resource
    private SmsScript smsScript;

    @Override
    public boolean doHandler(TaskInfo taskInfo) {

        SmsParam smsParam = SmsParam.builder()
                .phones(taskInfo.getReceiver())
                .content(taskInfo.getContent())
                .messageTemplateId(taskInfo.getMessageTemplateId())
                .supplierId(10)
                .supplierName("腾讯云通知类消息渠道").build();
        List<SmsRecord> recordList = smsScript.send(smsParam);

        if (CollUtil.isNotEmpty(recordList)) {
//            smsRecordDao.save(recordList);
            return true;
        }
        return false;
    }
}
