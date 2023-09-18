package demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date: 2022/5/24 23:23
 * @author: LiHaoHan
 * @program: demo.config
 */
@Slf4j
@Component
public class MyListen implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            // 保证只执行一次
            log.info("服务初始化完毕");
        }
    }
}
