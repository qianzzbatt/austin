package com.java3y.austin.service;

import com.java3y.austin.domain.SendRequest;
import com.java3y.austin.domain.SendResponse;

/**
 * @author huangquan
 * @date 2023/1/28
 **/
public interface SendService {

    SendResponse send(SendRequest sendRequest);


    SendResponse batchSend(SendRequest sendRequest);

}
