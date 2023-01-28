package com.java3y.austin.controller;

import com.java3y.austin.kafkatest.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangquan
 * @date 2023/1/28
 **/

@RestController
public class KafkaTestController {

    @Autowired
    private UserLogProducer userLogProducer;

    /**
     * test insert
     */
    @GetMapping("/kafka/insert")
    public String insert(String userId) {
        userLogProducer.sendLog(userId);

        return null;
    }

}
