package com.java3y.austin.domain;

import com.java3y.austin.vo.BaseQueryPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huangquan
 * @date 2022/8/19
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MsgTemplateParam extends BaseQueryPage {

    /**
     * 模板名称
     */
    private String name;

    /**
     * 消息类型
     */
    private Integer msgType;

}
