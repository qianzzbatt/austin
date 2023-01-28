package com.java3y.austin.kafkatest;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huangquan
 * @date 2023/1/28
 **/

@Data
@Accessors(chain = true)
public class UserLog {

    private String username;
    private String userid;
    private String state;
}
