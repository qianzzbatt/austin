package com.java3y.austin.controller;

import com.java3y.austin.domain.MsgTemplate;
import com.java3y.austin.domain.MsgTemplateParam;
import com.java3y.austin.service.MsgTemplateService;
import com.java3y.austin.vo.BasicResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangquan
 * @date 2022/8/19
 **/
@RestController
@RequestMapping("/austin/msgTemplate")
public class MsgTemplateController {

    @Resource
    private MsgTemplateService msgTemplateService;

    @PostMapping("/add")
    public BasicResultVO add(@RequestBody MsgTemplate msgTemplate){
       return BasicResultVO.success();
    }

    @PostMapping("/list")
    public BasicResultVO add(@RequestBody MsgTemplateParam param){
        return BasicResultVO.success();
    }

    @GetMapping("/delete/{id}")
    public BasicResultVO<Void> add(@PathVariable(value = "id") Long id){
        return null;
    }

}
