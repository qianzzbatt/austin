package com.java3y.austin.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EasySqlInjector extends DefaultSqlInjector {
	@Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        // 防止父类的方法不可使用
        final List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        // 增加自定义方法
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}