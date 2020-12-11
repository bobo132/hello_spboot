package com.bobo.hello_spboot.other.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener2 {

    @EventListener(MyApplicationEvent.class)
    public void onEvent(MyApplicationEvent event) {
        System.out.println("[基于注解方式] 收到事件: " + event);
    }

}
