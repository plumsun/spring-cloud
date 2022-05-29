package demo.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date: 2022/5/24 23:23
 * @author: LiHaoHan
 * @program: demo.config
 */
@Component
public class MyListen implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {//保证只执行一次
            System.out.println("初始化完毕");
        }
    }
}
