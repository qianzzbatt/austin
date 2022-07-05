package com.java3y.austin.handler;

import com.java3y.austin.pojo.TaskInfo;

/**
 * 统一处理接口
 * @author huangquan
 * @date 2022/7/4
 **/
public interface Handler {

    boolean doHandler(TaskInfo taskInfo);

}
