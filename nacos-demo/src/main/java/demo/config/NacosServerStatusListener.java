package demo.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.FeignLoadBalancer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * nacos服务列表实现刷新
 *
 * @author Lihaohan
 */
@Slf4j
@Component
public class NacosServerStatusListener {

    /**
     * 应用服务使用feign时使用该对象刷新ribbon中的服务地址信息,该对象会在FeignRibbonClientAutoConfiguration中被加载
     */
    @Resource
    private CachingSpringLoadBalancerFactory cachingSpringLoadBalancerFactory;
    /**
     * Nacos注册中心配置信息 包括我们需要的NamingService也能在里面获取到
     */
    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    /**
     * 需要订阅的服务名称集合 可以放在Nacos的配置中心
     */
    private static final List<String> SERVICE_LIST = Collections.singletonList("spring-system");

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        try {
            // 获取 NamingService
            NamingService namingService = NamingFactory.createNamingService(nacosDiscoveryProperties.getNacosProperties());
            // 订阅服务，服务状态刷新时，更新ribbon
            SERVICE_LIST.forEach(service -> {
                // 订阅服务状态发生改变时，刷新 ribbon 服务实例
                try {
                    namingService.subscribe(service,"DEFAULT_GROUP", (event -> {
                        // 创建feign负载均衡器，如果已经创建过会直接获取
                        FeignLoadBalancer feignLoadBalancer = cachingSpringLoadBalancerFactory.create(service);
                        if (feignLoadBalancer != null) {
                            log.info("刷新 ribbon 服务实例：{}", service);
                            ILoadBalancer iLoadBalancer = feignLoadBalancer.getLoadBalancer();
                            if (iLoadBalancer != null) {
                                ZoneAwareLoadBalancer loadBalancer = (ZoneAwareLoadBalancer) iLoadBalancer;
                                loadBalancer.updateListOfServers();
                                log.info("刷新 ribbon 服务实例成功：{}", service);
                            }
                        }
                    }));
                } catch (NacosException e) {
                    log.error("订阅 nacos 服务失败,error:{}", e.getErrMsg());
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            log.error("获取 nacos 服务信息失败,error:{}", e.getMessage());
            e.printStackTrace();
        }
    }
}