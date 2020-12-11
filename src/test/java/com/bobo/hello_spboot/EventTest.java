package com.bobo.hello_spboot;

import com.bobo.hello_spboot.other.event.MyApplicationEvent;
import com.bobo.hello_spboot.other.event.MyApplicationListener;
import com.bobo.hello_spboot.other.event.MyApplicationListener2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

public class EventTest extends BaseTest {


    @Test
    public void f1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册自定义的事件监听器
        context.addApplicationListener(new MyApplicationListener());
        context.register(MyApplicationListener2.class);

        // 启动上下文
        context.refresh();
        // 发布事件, 事件源为 context
        context.publishEvent(new MyApplicationEvent(context));
        // 结束
        context.close();

    }







}
